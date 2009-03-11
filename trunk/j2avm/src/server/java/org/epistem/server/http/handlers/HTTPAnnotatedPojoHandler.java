package org.epistem.server.http.handlers;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.epistem.server.Server;
import org.epistem.server.http.HTTPMethod;
import org.epistem.server.http.HTTPRequest;
import org.epistem.server.http.HTTPRequestHandler;
import org.epistem.server.http.HTTPResponse;
import org.epistem.server.http.MimeType;
import org.epistem.server.http.handlers.annotations.Default;
import org.epistem.server.http.handlers.annotations.ParamName;
import org.epistem.server.http.handlers.annotations.PathRegex;

public class HTTPAnnotatedPojoHandler implements HTTPRequestHandler {

    private Object pojo;
    private Map<Pattern, Method> methods = new HashMap<Pattern, Method>();
    
    /**
     * @param pojo the object to forward requests to
     */
    public HTTPAnnotatedPojoHandler( Object pojo ) {
        this.pojo = pojo;
        if( pojo == null ) return;
        
        //gather the mapped methods
        Class clazz = pojo.getClass();
        for( Method m : clazz.getMethods() ) {
            PathRegex path = m.getAnnotation( PathRegex.class );
            if( path == null ) continue;
            
            Pattern urlRegex = Pattern.compile( path.value() );
            methods.put( urlRegex, m );
        }
    }
    
    /** @see org.epistem.server.http.HTTPRequestHandler#handleRequest(org.epistem.server.http.HTTPRequest) */
    public boolean handleRequest( HTTPRequest request ) throws Exception {
        
        //find the method that matches the path and dispatch to it
        String path = request.remainingPath;
        for( Map.Entry<Pattern, Method> e : methods.entrySet() ) {
            Pattern p = e.getKey();
            Method  m = e.getValue();
            
            if( p.matcher( path ).matches() ) {
                dispatchMethod( request, m );
                return false;
            }            
        }
        
        request.response.sendNotFound();
        return false;
    }

    private void dispatchMethod( HTTPRequest request, Method m ) {
        try {        
            Annotation[][] paramAnnos = m.getParameterAnnotations();
            Class[]        paramTypes = m.getParameterTypes();
            
            Object[] args = new Object[ paramTypes.length ];
            for( int i = 0; i < args.length; i++ ) {
                
                Class  type      = paramTypes[i];
                String paramName = null; //http request param name
                String defValue  = null;
                Object arg = null;
                for( Annotation a : paramAnnos[i] ) {
                    if( a instanceof ParamName ) {
                        paramName = ((ParamName) a).value();
                        continue;
                    }            
                    
                    if( a instanceof Default ) {
                        defValue = ((Default) a).value();
                        continue;
                    }
                }    
                
                if( paramName != null ) {
                    String param = request.getParameter( paramName );
                    if( param == null ) param = defValue;
                    
                    arg = coerceType( param, type, paramName );
                } 
                else if( type == HTTPRequest.class  ) arg = request;
                else if( type == HTTPMethod.class   ) arg = request.method;
                else if( type == InputStream.class  ) arg = request.postData;
                else if( type == String.class       ) arg = request.queryString;
                else if( type == Server.class       ) arg = request.server;
                else if( type == int.class          ) arg = request.contentLength;
                else if( type == HTTPResponse.class ) arg = request.response;
                
                args[i] = arg;
            }
        
            Object result = m.invoke( pojo, args );
            if( result != null ) {
                request.response.writeContent( result.toString(), MimeType.TEXT );
            }
        } catch( Exception ex ) {
            request.response.sendException( "While calling " + m, ex );
        }
    }
        
    private Object coerceType( String param, Class type, String paramName ) throws Exception {
        if( type == int.class ) {
            if( param == null ) throw new Exception( "missing numeric parameter " + paramName );
            try {
                return Integer.parseInt( param );
            } catch( NumberFormatException nfe ) {
                throw new NumberFormatException( "invalide numeric parameter: " + paramName );
            }
        }
        
        if( type == String.class ) return param;
        
        throw new Exception( "Unknown named parameter type: " + type );
    }
    
    /** @see org.epistem.server.http.HTTPRequestHandler#init(java.lang.String, java.util.Properties) */
    public void init(String url, Properties props) throws Exception {
        // nada        
    }
}
