package org.epistem.server.http;

import java.io.IOException;
import java.net.InetAddress;

import org.epistem.server.RequestDispatcher;
import org.epistem.server.RequestQueue;
import org.epistem.server.Server;
import org.epistem.server.http.handlers.HTTPAnnotatedPojoHandler;
import org.epistem.server.http.handlers.HTTPClasspathHandler;
import org.epistem.server.http.handlers.HTTPDirectoryHandler;
import org.epistem.server.http.handlers.HTTPServerKillHandler;
import org.epistem.server.http.handlers.HTTPShellHandler;
import org.epistem.server.http.handlers.HTTPSnoopHandler;

import test.TestPojo;

/** A simple HTTP server. */
public class MiniHTTPServer {
    
    private HTTPContext  root = new HTTPContext();
    private HTTPProtocol http = new HTTPProtocol( root );
    private Server server;
    
    /**
     * Start a server on the given local loopback port
     * @param port the port to listen on
     * @return the new server
     * 
     * @throws RuntimeException if there is a problem starting the server
     */
    public static MiniHTTPServer onLoopback( int port ) {
        try {
            return new MiniHTTPServer( port, InetAddress.getByName("127.0.0.1") );
        } catch( IOException ioe ) {
            throw new RuntimeException( ioe );
        }
    }
    
    /**
     * Start a server on the given port on all adapter addresses
     */
    public MiniHTTPServer( int port ) throws IOException {
        RequestQueue queue = new RequestQueue( 100 );
        RequestDispatcher.makePool( queue, 5 );
        server = new Server( port, null, http, queue );
    }

    /**
     * Start a server on the given port and adapter address
     */
    public MiniHTTPServer( int port, InetAddress address ) throws IOException {
        RequestQueue queue = new RequestQueue( 100 );
        RequestDispatcher.makePool( queue, 5 );
        server = new Server( port, address, http, queue );
    }
    
    /**
     * Shut the server down
     */
    public void shutdown() {
        server.shutdown();
    }
    
    /**
     * Wait for the server to shut down
     */
    public void waitForShutdown() {
        server.waitForShutdown();
    }
    
    /** @see java.lang.Object#finalize() */
    @Override
    protected void finalize() throws Throwable {
        shutdown();
    }

    /**
     * Add a request handler
     * 
     * @see HTTPContext#addHandler(String, HTTPRequestHandler)
     * @param regex the URL regex
     * @param handler the handler
     * 
     * @return the same instance
     */
    public MiniHTTPServer addHandler( String regex, HTTPRequestHandler handler ) {
        root.addHandler( regex, handler );
        return this;
    }
    
	/** For testing. */
    public static void main( String[] args ) throws Exception {
        
        MiniHTTPServer http = MiniHTTPServer.onLoopback( 8888 )        
            .addHandler( "(/jdk)(.*)", new HTTPDirectoryHandler( "/Developer/Java/docs" ) )
            .addHandler( "/kill", new HTTPServerKillHandler() )
            .addHandler( "(/pojo/)(.*)", new HTTPAnnotatedPojoHandler( new TestPojo() ) )
            .addHandler( "(/cp/)(.*)", new HTTPClasspathHandler( "org/epistem/server/http/" ) )
            .addHandler( "(/snoop/)(.*)", new HTTPSnoopHandler() )
            .addHandler( "/cmd", new HTTPShellHandler( null ))
            .addHandler( "/ps" , new HTTPShellHandler( "ps -ax" ) );
        
		System.out.println( "Hit URL /kill to terminate the server" );
        http.waitForShutdown();
    }
}
