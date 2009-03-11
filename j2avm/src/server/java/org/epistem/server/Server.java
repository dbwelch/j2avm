package org.epistem.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/** A simple server. */
public class Server implements Runnable {
	protected boolean      mKillFlag = false;
	protected Handler     mProtocol;
	protected ServerSocket mServerSock;
	protected RequestQueue mQueue;
    
    /** The post the server is listening on */
    public final int port;

	/** Shut the server down. Takes effect upon the next request or after the current timeout. */
	public void shutdown() {
		mKillFlag = true;
		mQueue.close();
	}

	public ServerSocket getServerSocket() { return mServerSock; }
	public Handler     getProtocol() { return mProtocol; }

	/** Start the server on a new thread. */
	public Server( ServerSocket socket, Handler protocol, RequestQueue queue ) {
        this.port = socket.getLocalPort();

		init( socket, protocol, queue ); 
	}
	
	/** Makes a ServerSocket on the given port and adapter address. */
	public Server( int port, InetAddress address, Handler protocol, RequestQueue queue ) throws IOException {
        this.port = port;
        
		ServerSocket sock = new ServerSocket( port, 50, address );
		sock.setSoTimeout( 500 );
		init( sock, protocol, queue ); 
        
        //System.out.println( "Started server on " + address + ":" + port );
	}

    /** Makes a ServerSocket on the given port on the local loopback address. */
    public static Server onLoopback( int port, Handler protocol, RequestQueue queue ) throws IOException {
        return new Server( port, InetAddress.getByName("127.0.0.1"), protocol, queue );
    }    
    
	private void init( ServerSocket socket, Handler protocol, RequestQueue queue ) {
		mProtocol   = protocol;
		mServerSock = socket;
		mQueue      = queue;
	
		new Thread(this).start();
	}

    /** Wait until the server is shutting down */
    public synchronized void waitForShutdown() {        
        while( true ) {
            if( mKillFlag ) return;
            try { wait( 1000 ); } catch( InterruptedException ignored ) {}
        }
    }
    
	public void run() {
		try {
			while (!mKillFlag) {
				try {
					Socket sock = mServerSock.accept();
					if( mKillFlag ) break;
					Request request = mProtocol.makeRequest( this, sock );
					mQueue.enqueue( request );
				} catch( SocketTimeoutException ignored ) {        
                    //System.out.println( "socket timeout" );
                } catch( IOException ioe ) {
                    throw ioe;
                } catch( Exception ex ) {
                    ex.printStackTrace();
                }
			}
            
            synchronized( this ) {
                notifyAll();
            }
            
		} catch( IOException ioex ) {
			ioex.printStackTrace();
		}
	}
}
