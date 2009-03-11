/*
 * Created on Sep 16, 2003
 */
package org.epistem.server.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.epistem.io.SubsetInputStream;
import org.epistem.server.Request;
import org.epistem.server.Server;
import org.epistem.util.Base64;

/**
 * An HTTP request.
 * 
 * @author nmain
 */
public class HTTPRequest extends Request {

    private BufferedInputStream inputStream;
    
    /** The request headers */
    public final Map<HTTPHeader, String> headers = new EnumMap<HTTPHeader, String>(HTTPHeader.class);
    
    /**
     * The server
     */
    public final Server server;
    
    /** The HTTP protocol instance */
	public final HTTPProtocol protocol;
    
    /** The HTTP context instance */
	public final HTTPContext context;
    
    /** The response for writing back to the client */
    public final HTTPResponse response;
    
    /** The query string (may be empty) */
	public final String queryString;

    /** The full request path */
	public final String path;
    
    /** The method */
	public final HTTPMethod method;
    
    /** The HTTP version string */
	public final String httpVer;
    
    /** The path segment that mapped to the handler */    
	public final String handlerPath;
    
    /** The path segment remaining after the handler path */
    public final String remainingPath;
	
    /** The content length - zero if not specified */
    public final int contentLength;
	
    /** The post data - null if none or content length is not known */
    public final InputStream postData;
	
    /** The host header value */
    public final String host;
    
    private Map<String,String> queryParams;    
    private Map<String,String> postParams;
	private final HTTPRequestHandler handler;
    
	/*pkg*/ HTTPRequest( Socket socket, Server server, HTTPProtocol protocol, HTTPContext context ) 
        throws IOException {
        
		super( socket, server, protocol );
		this.protocol = protocol;
		this.context  = context;
        this.server   = server;
        this.response = new HTTPResponse( this, out );                             

        String      _queryString   = null;
        String      _path          = null;
        HTTPMethod  _method        = null;
        String      _httpVer       = null;
        String      _handlerPath   = null;
        String      _remainingPath = null;
        String      _host          = null;
        int         _contentLength = 0;
        HTTPRequestHandler _handler = null;
        
        detailParser:
        try{
            inputStream = new BufferedInputStream( in );
    
            String line = HTTPProtocol.readLine( inputStream );
    
            if( line == null ) {
                response.sendError( HTTPResponseCode.CLIENT_ERROR_BAD_REQUEST );
                break detailParser;
            }
                
            StringTokenizer tok = new StringTokenizer( line );
            if( tok.countTokens() != 3 ) {
                response.sendError( HTTPResponseCode.CLIENT_ERROR_BAD_REQUEST );
                break detailParser;
            }
    
            _method  = HTTPMethod.valueOf( tok.nextToken() );
            _path    = tok.nextToken();
            _httpVer = tok.nextToken();
    
            int q_mark = _path.indexOf( "?" );
            if( q_mark < 0 ) q_mark = _path.length();
            
            _queryString = _path.substring( q_mark );
            _path        = _path.substring( 0, q_mark );
            _path = HTTPProtocol.unencode( _path );
            
            if( _queryString.length() > 0 ) _queryString  = _queryString.substring( 1 );
    
            while( (line = HTTPProtocol.readLine( inputStream )) != null ) {
                line = line.trim();
    
                if( line.length() == 0 ) break;
    
                int colon = line.indexOf( ":" );
    
                if( colon > 0 ) {
                    String name = line.substring( 0, colon ).trim();
                    String value = line.substring( colon + 1 ).trim();

                    HTTPHeader header = HTTPHeader.fromString( name );
                    
                    if( header != null ) headers.put( header, value );
                    
                    if( header == HTTPHeader.Content_Length ) {
                        _contentLength = Integer.parseInt( value.trim() );
                    }
                    else if( header == HTTPHeader.Host ) {
                        _host = value;
                    } 
                }
            }

            String[] paths = new String[2];
            _handler = context.getHandler( _path, paths );
            
            if( _handler == null ) {
                response.sendNotFound( );  
                break detailParser;     
            } 
            
            _handlerPath   = paths[0];
            _remainingPath = paths[1];
            
        } catch( IOException ioe ) {
            response.sendException( ioe.toString(), ioe );
            throw ioe;
        }
        
        this.queryString   = _queryString  ;
        this.path          = _path         ;
        this.method        = _method       ;
        this.httpVer       = _httpVer      ;
        this.host          = _host         ;
        this.handlerPath   = _handlerPath  ;
        this.remainingPath = _remainingPath;
        this.contentLength = _contentLength;
        this.postData      = (contentLength > 0 && method == HTTPMethod.POST ) ?
                                 new SubsetInputStream( in, contentLength ) :
                                 null;    
        this.handler = _handler;
	}
    
	/**
     * Get the basic-auth user and password 
     * @return {user,password}, null if not found
	 */
    public String[] getUserPassword() { 
        try {
            String auth = headers.get( HTTPHeader.Authorization );
    
            if( auth == null ) return null;
            if( ! auth.startsWith( "Basic " ) ) return null;
    
            auth = auth.substring(5).trim();
    
            String userpw = new String( Base64.decode( auth ), "US-ASCII" );
    
            String[] ss = new String[]{ "", "" };
    
            int colon = userpw.indexOf(":");

            ss[0] = userpw.substring( 0, colon );
            ss[1] = userpw.substring( colon + 1 );
            return ss;
        }
        catch( Exception ignored ) { return null; }
    }
    
    /**
     * Get a querystring parameter
     */
	public String getQueryParam( String name ) {
	    if( queryParams == null ) {
	        queryParams = new HashMap<String,String>();
	        parseParams( queryString, queryParams );
	    }
	    
	    return queryParams.get( name );
	}

    /**
     * Get a post parameter
     */
    public String getPostParam( String name ) throws IOException {
        if( method != HTTPMethod.POST ) return null;
        
        if( postParams == null ) {
            postParams = new HashMap<String,String>();            
            parsePostData( postData, contentLength, postParams );
        }
        
        return postParams.get( name );
    }

    /**
     * Get a parameter from the querystring or from the post-data.
     */
    public String getParameter( String name ) {

        String value = getQueryParam( name );
        if( value != null ) return value;
        
        try {
            value = getPostParam( name );
        } catch( Exception ignored ) {}
        
        return value;
    }
	
	/**
	 * @see org.epistem.server.Request#processRequest()
	 */
	public void processRequest( ) {
 
        if( handler == null ) return;
        
		try{
			if( ! handler.handleRequest( this ) ) {			
				close();
			}
		} catch( Exception ex ) {
			response.sendException( "Exception thrown while handling request:", ex );
		} 
	}
	
	/**
	 * @see org.epistem.server.Request#getInputStream()
	 */
	public InputStream getInputStream() throws IOException {
		return inputStream;
	}

    private void parsePostData( InputStream in, int length, Map<String,String> map )
        throws IOException {
        if( length <= 0 ) return;

        byte[] data = getPostData( in, length );
        String dataS = new String( data, "US-ASCII" );
        parseParams( dataS, map );
    }
    
    private byte[] getPostData( InputStream in, int length ) 
        throws IOException {
        
        byte[] data = new byte[ length ];

        int read = 0;
        int r = 0;
        
        while( (r = in.read( data, read, length - read )) >= 0
              && read < length ) {
            read += r;
        }

        return data;
    }
    
    private void parseParams( String params, Map<String,String> map ) {
        if( params == null ) return;

        StringTokenizer tok = new StringTokenizer( params, "&" );

        while( tok.hasMoreTokens() ) {
            String param = tok.nextToken();

            int equals = param.indexOf("=");

            if( equals > 0 ) {
                String name = HTTPProtocol.unencode(param.substring(0,equals));
                String value = "";

                if( equals < param.length() - 1 ) {
                    value = HTTPProtocol.unencode( param.substring( equals+1 ) );
                }

                map.put( name, value );
            }
        }
    }
}
