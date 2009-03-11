package org.epistem.server.http.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.epistem.server.http.HTTPRequest;

/**
 * Handler to serve up files beneath a given root directory
 *
 * @author nickmain
 */
public class HTTPDirectoryHandler extends HTTPResourceHandler
{
    protected final File mDirectory;

	public void init( String url, Properties props ) {
		super.init( url, props );
	}

    public HTTPDirectoryHandler( File directory ) {
        mDirectory = directory;
    }

    public HTTPDirectoryHandler( String dirname ) {
        this( new File( dirname ) );
    }

    protected HTTPResourceHandler.Resource getResource( HTTPRequest request, String path ) throws IOException {
        
        File file = new File( mDirectory, path );
        if( ! file.exists() ) return null;

        int length = (int)file.length();

        InputStream filein = new FileInputStream( file );

        return new Resource( filein, length );
    }
}
