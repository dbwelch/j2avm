package org.epistem.server.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Wrapper around an HTTPRequest to allow various responses to be sent.
 *
 * @author nickmain
 */
public class HTTPResponse {

    private final OutputStream out;
    private final Writer       writer;
    private final HTTPRequest  request;
    private boolean codeHasBeenSent = false; //whether the code has been sent yet
    
    /*pkg*/ public HTTPResponse( HTTPRequest request, OutputStream out ) {
        this.out     = out;
        this.request = request;
        
        try {
            this.writer  = new OutputStreamWriter( out, "US-ASCII" );
        } catch( UnsupportedEncodingException ueex ) {
            throw new RuntimeException( ueex );
        }
    }
        
    /**
     * Write a line to the response
     */
    public void println( String s ) throws IOException {
        print( s );
        println();
    }

    /**
     * Write an empty line to the response
     */
    public void println() throws IOException {
        print( "\r\n" );
    }
    
    /**
     * Write text to the response
     */
    public void print( String s ) throws IOException {
        writer.write( s );
    }
    
    /**
     * Flush the writer
     */
    public void flush() throws IOException {
        writer.flush();
    }
    
    /**
     * Close the writer and the request
     */
    public void close() throws IOException {        
        flush();
        writer.close();
        request.close();
    }
    
    /**
     * Send the HTTP version and code (first things in a response)
     * 
     * @param code the HTTP response code
     */
    public void sendCode( HTTPResponseCode code ) throws IOException {
        print  ( "HTTP/1.0 " );
        print  ( "" + code.code );
        print  ( " " + code.message );
        println();
        codeHasBeenSent = true;
    }

    /**
     * Send a header
     * 
     * @param type the header type
     * @param value the header value
     */
    public void sendHeader( HTTPHeader type, String value ) throws IOException {
        if( ! codeHasBeenSent ) sendCode( HTTPResponseCode.SUCCESS_OK );
        
        print  ( type.key );
        print  ( ": " );
        println( value );
    }
    
    /**
     * Send the content type header
     *  
     * @param type the content type
     */
    public void sendContentType( MimeType type ) throws IOException {
        sendHeader( HTTPHeader.Content_Type, type.mimeType );
    }
    
    /**
     * Send a request for authorization
     * 
     * @param realm the realm to authorize within
     */
    public void sendAuthRequest( String realm ) throws IOException {
        sendCode  ( HTTPResponseCode.CLIENT_ERROR_UNAUTHORIZED );
        sendHeader( HTTPHeader.WWW_Authenticate, "Basic realm=\"" + realm + "\"" );
        println();
        close();
    }

    /** Send an error back to the client - any exceptions are suppressed.*/
    public void sendError( HTTPResponseCode code ) {
        try {
            sendCode( code );
            println();
            println( "ERROR " + code.message );
            close();            
        } catch( Exception ignored ) {}
    }

    /** Send a permanent redirect back to the client */
    public void sendPermRedirect( String newURL ) throws IOException {
        sendCode  ( HTTPResponseCode.REDIRECT_PERM );
        sendHeader( HTTPHeader.Location, newURL );
        println();
        println( HTTPResponseCode.REDIRECT_PERM.message + " to " + newURL );
        close();
    }

    /** Send a temporary redirect back to the client */
    public void sendTempRedirect( String newURL ) throws IOException {
        sendCode  ( HTTPResponseCode.REDIRECT_TEMP );
        sendHeader( HTTPHeader.Location, newURL );
        println();
        println( HTTPResponseCode.REDIRECT_TEMP.message + " to " + newURL );
        close();
    }
    
    /** Send an exception back to the client - any exceptions are suppressed. */
    public void sendException( String message, Throwable ex ) {
        try {
            sendCode( HTTPResponseCode.SERVER_ERROR_FATAL );
            sendContentType( MimeType.TEXT );
            println();
            println( message );
            println();
            
            PrintWriter pw = new PrintWriter( writer );
            ex.printStackTrace( pw );
            pw.flush();
            
            println();
            close();
        } catch( Exception ignored ) {}
    }

    /** Send a not-found error. */
    public void sendNotFound( ) throws IOException {
        sendError( HTTPResponseCode.CLIENT_ERROR_NOT_FOUND );
    }

    /** Send an OK response and close the request */
    public void sendOK( ) throws IOException {
        println( "HTTP/1.0 200 OK\r\n" );
        println();
        close();
    }

    /**
     * Write UTF-8 encoded content.  This starts (if necessary) and closes the
     * response.
     */
    public void writeContent( String content, MimeType contentType ) throws IOException {
        byte[] bytes = content.getBytes( "UTF-8" );
        
        sendHeader( HTTPHeader.Content_Length, "" + bytes.length );
        sendHeader( HTTPHeader.Content_Type, contentType.mimeType + "; charset=UTF-8" );
        
        println();
        flush();
        
        out.write( bytes );
        close();
    }
    
    /**
     * Write binary content
     * 
     * @param content the content data
     * @param contentType the content type
     */
    public void writeContent( byte[] content, MimeType contentType ) throws IOException {
        writeContent( content.length, contentType ).write( content );
    }

    /**
     * Write content headers and return the output stream for writing the content.
     * 
     * @param length the content size
     * @param contentType the content type
     */
    public OutputStream writeContent( int length, MimeType contentType )
        throws IOException {

        if( length >= 0 ) {
            sendHeader( HTTPHeader.Content_Length, ""+length );
        }

        sendHeader( HTTPHeader.Content_Type, contentType.mimeType );

        println();
        flush();

        return out;
    }
    
    
    private StringWriter contentBuffer;
    
    /**
     * Start text content with the given mime-type and return a writer for it.
     * Closing the writer will flush the content and close the request.
     * 
     * @param type the text type
     * @return the content buffer
     */
    public PrintWriter startTextContent( final MimeType type ) {
        if( contentBuffer == null ) contentBuffer = new StringWriter();
        return new PrintWriter( contentBuffer ) {

            /** @see java.io.PrintWriter#close() */
            @Override
            public void close() {
                flush();
                super.close();
                
                try {
                    writeContent( contentBuffer.toString(), type );
                } catch( IOException ioe ) {
                    throw new RuntimeException( ioe );
                }
            }            
        };
    }
}
