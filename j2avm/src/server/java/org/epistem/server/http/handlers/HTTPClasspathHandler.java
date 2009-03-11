package org.epistem.server.http.handlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.epistem.server.http.HTTPRequest;

/**
 * Resource handler that finds resources within the current classloader.
 *
 * @author nickmain
 */
public class HTTPClasspathHandler extends HTTPResourceHandler
{
    protected String mPrefix;

    /**
     * No path prefix.
     */
	public HTTPClasspathHandler() {
	    mPrefix = "";
	}

    /**
     * @see org.epistem.server.http.handlers.HTTPResourceHandler#init(java.lang.String, java.util.Properties)
     */
	public void init( String url, Properties props ) {
		super.init( url, props );
	}

    /**
     * With path prefix.
     * @param prefix the prefix for the path before looking up the resource
     */
    public HTTPClasspathHandler( String prefix ) {
        mPrefix = prefix;
    }
    
    /**
     * @see org.epistem.server.http.handlers.HTTPResourceHandler#getResource(org.epistem.server.http.HTTPRequest, java.lang.String)
     */
    protected HTTPResourceHandler.Resource getResource( HTTPRequest request, String path ) throws IOException {
        
        InputStream in = getClass().getClassLoader().getResourceAsStream( mPrefix + path );
        
        if( in == null ) return null;

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buff = new byte[ 1000 ];
        int read = 0;
        while((read = in.read( buff )) >= 0 ) {
            bout.write( buff, 0, read );
        }
        in.close();
        
        byte[] data = bout.toByteArray();
        int length = data.length;

        in = new ByteArrayInputStream( data );

        return new Resource( in, length );
    }
}
