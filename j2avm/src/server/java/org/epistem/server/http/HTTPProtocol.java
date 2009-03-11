package org.epistem.server.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

import org.epistem.server.Handler;
import org.epistem.server.Request;
import org.epistem.server.Server;

public class HTTPProtocol implements Handler {

	private final HTTPContext rootContext;

	public HTTPProtocol( HTTPContext rootContext ) {
		this.rootContext = rootContext;
	}

	/** Create a protocol specific request instance. */
	public Request makeRequest( Server server, Socket socket ) throws IOException {
		return new HTTPRequest( socket, server, this, rootContext ); 
	}
	
	/** Send a busy response back to the client. */
	public void sendBusy( Request request ) {
        ((HTTPRequest) request).response.sendError( HTTPResponseCode.SERVER_ERROR_BUSY );
	}

    /**
     * Read an HTTP line from the input stream
     */
    public static String readLine( BufferedInputStream in ) throws IOException {
        byte[] buff = new byte[1010];

        int read = 0;
        int r = 0;

        while( true ) {
            while( (r = in.read( buff, read, 1 )) > 0 && read < 1000 ) {
                if( buff[read] == 0x0a ) break;

                read++;
            }

            if( read == 0 ) return null;

            //--trim off the CRLF
            while( read >= 1
                   && ( buff[read-1] == 0x0a || buff[read-1] == 0x0d ) ) {
                read--;
            }

            //--check for continuation
            if( r > 0 && read > 0 ) {
                in.mark(5);
                int b = in.read();

                if( b == 0x20 || b == 0x09 ) {
                    buff[read++] = (byte)b;
                    continue;
                }

                in.reset();
            }

            break;
        }

        if( read > 0 ) {
            return new String( buff, 0, read, "US-ASCII" );
        }
        else {
            return "";
        }
    }

    /** URL encode a string */
    public static String encode( String s ) {
        StringBuilder buff = new StringBuilder();

        int length = s.length();
        for( int i = 0; i < length; i++ ) {
            char c = s.charAt( i );

            if( c == ' ' ) {
                buff.append( '+' ); 
                continue;
            }

            if( c == ':' || c == '/' ) {
                buff.append( c ); 
                continue;
            }
            
            if( Character.isJavaIdentifierPart( c ) ) {
                buff.append( c );
                continue;
            }
            
            buff.append( '%' );            
            if( c < 16 ) buff.append( '0' );
            buff.append( Integer.toHexString( c ) );
        }

        return buff.toString();        
    }
    
    /** URL decode a string */
    public static String unencode( String s ) {
        StringBuilder buff = new StringBuilder();

        int length = s.length();
        for( int i = 0; i < length; i++ ) {
            char c = s.charAt( i );

            switch( c ) {
                case '+': buff.append( ' ' ); break;

                case '%': {
                    try {
                        buff.append( (char)Integer.parseInt( "" + s.charAt( ++i ) + s.charAt( ++i ), 16 ));
                    } catch( Exception ex ) {}
                    break;
                }

                default: buff.append( c ); break;
            }
        }

        return buff.toString();
    }
}
