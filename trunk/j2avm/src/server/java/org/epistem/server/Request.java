/*
 * Created on Sep 16, 2003
 */
package org.epistem.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * A client request to the server.
 * 
 * @author nmain
 */
public abstract class Request {

	protected Socket       socket;
	protected Server       server;
	protected Handler      handler;
	protected InputStream  in;
	protected OutputStream out;
	
	public Request( Socket socket, Server server, Handler handler ) throws IOException {
		this.socket  = socket;
        this.server  = server;
        this.handler = handler;
        this.in      = socket.getInputStream();
        this.out     = socket.getOutputStream();
	}
		
	/** Send a busy response back to the client and close the request. */
	public void busyClose() {
        handler.sendBusy( this );
		close();
	}
	
	/** Close the request. */
	public void close() {
		if( in     != null ) try { in.close(); in = null; } catch( IOException ignored ) {}
		if( out    != null ) try { out.flush(); out.close(); out = null; } catch( IOException ignored ) {}		
		if( socket != null ) try { socket.close(); socket = null; } catch( IOException ignored ) {}
	}
	
	/** Process the request. */
	public abstract void processRequest();
}
