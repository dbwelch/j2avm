package org.epistem.server.http.handlers;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.epistem.io.StreamCopier;
import org.epistem.server.http.HTTPProtocol;
import org.epistem.server.http.HTTPRequest;
import org.epistem.server.http.HTTPRequestHandler;
import org.epistem.server.http.MimeType;

/**
 * Handler that executes commands on the underlying OS and sends the output
 * back to the client.
 *
 * @author nickmain
 */
public class HTTPShellHandler implements HTTPRequestHandler {

    private final String command;
    
    /**
     * @param command the command to execute - null if command is from the querystring
     */
    public HTTPShellHandler( String command ) {
        this.command = command;
    }
    
    /** @see org.epistem.server.http.HTTPRequestHandler#handleRequest(org.epistem.server.http.HTTPRequest) */
    public boolean handleRequest(HTTPRequest request) throws Exception {
        
        String cmdString = command;
        if( cmdString == null ) {
            cmdString = HTTPProtocol.unencode( request.queryString );
        }
        
        Runtime runtime = Runtime.getRuntime();
        Process shell = runtime.exec( cmdString );
        InputStream shellIn  = shell.getInputStream();
        InputStream shellErr = shell.getErrorStream();
        OutputStream out = request.response.writeContent( -1, MimeType.TEXT );
        
        StreamCopier inCopier  = new StreamCopier( shellIn,  out );
        StreamCopier errCopier = new StreamCopier( shellErr, out );
                
        errCopier.startThread(); //run on separate thread
        inCopier .startThread(); //run on separate thread   
        
        PrintWriter writer = new PrintWriter( shell.getOutputStream(), true );      
                
        writer.flush();
        writer.close();
        
        errCopier.waitUntilDone();
        inCopier .waitUntilDone();
        
        //System.out.println( "Waiting for Shell..." );
        try{ shell.waitFor(); } catch( Exception ex ) {}
        //System.out.println( "Shell Ended" );      
        
        return false;
    }

    /** @see org.epistem.server.http.HTTPRequestHandler#init(java.lang.String, java.util.Properties) */
    public void init(String url, Properties props) throws Exception {
        // TODO Auto-generated method stub
    }
}
