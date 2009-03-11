package org.epistem.server.http.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.epistem.server.http.HTTPRequest;
import org.epistem.server.http.HTTPRequestHandler;
import org.epistem.server.http.MimeType;

/**
 * Base for handlers that serve up resources. 
 *
 * @author nickmain
 */
public abstract class HTTPResourceHandler implements HTTPRequestHandler
{
	public HTTPResourceHandler() {
	}

    /**
     * @see org.epistem.server.http.HTTPRequestHandler#init(java.lang.String, java.util.Properties)
     */
	public void init( String url, Properties props ) {
		//nada
	}

    /**
     * The resource abstraction
     */
	protected static class Resource {
	    public final InputStream stream;
	    public final int         length;
	    public Resource( InputStream in, int size ) { stream = in; length = size; }
	}
	
	/**
     * Get the resource for the given path
     * 
	 * @return null if resource not found
	 */
	protected abstract Resource getResource( HTTPRequest request, String path ) throws IOException;

    /**
     * @see org.epistem.server.http.HTTPRequestHandler#handleRequest(org.epistem.server.http.HTTPRequest)
     */
    public boolean handleRequest( HTTPRequest request ) {
    	
    	String path = request.remainingPath;
    	
        try {
            //default to index.html
            if( path.equals("/") || path.length() == 0  ) {
                String newURL = "http://" 
                              + request.host
                              + request.handlerPath;
                if( ! newURL.endsWith( "/" ) ) newURL += "/";
                newURL += "index.html";
                
                request.response.sendTempRedirect( newURL );
                return false;
            }
    
    		if( path.startsWith( "/" ) && path.length() > 1 ) {
                path = path.substring(1);
    		}
    
            //--Filter out bad paths
            if( path.indexOf( ".." ) >= 0 ) {
                request.response.sendNotFound();
                return false;
            }

            Resource resource = getResource( request, path );
            
            if( resource == null ) {
                request.response.sendNotFound();
                return false;
            }

            int         length = resource.length;
            InputStream filein = resource.stream;

            int buffSize = (length < 10000) ? length : 10000;
            byte[] buff = new byte[ buffSize ];

            MimeType mimetype = MimeType.TEXT;            
            int period = path.lastIndexOf(".");
            if( period > 0 && period < path.length() - 1 ) {
                String   suffix   = path.substring( period + 1 );
                mimetype = MimeType.forSuffix( suffix );
            }

            OutputStream out = request.response.writeContent( length, mimetype );

            int read = 0;
            while(( read = filein.read( buff )) > 0) {
                out.write( buff, 0, read );
            }

            filein.close();
            
        } catch( Exception ex ) {
            request.response.sendException( "Exception while serving resource " + path, ex );
        }
        
        return false;
    }
}
