package org.epistem.server;

import java.net.Socket;

/** Implemented by various protocols, such as HTTP. */
public interface Handler {
	/** Create a protocol specific request instance. */
	public Request makeRequest( Server server, Socket socket ) throws Exception;
	
	/** Send a busy response back to the client. */
	public void sendBusy( Request request );
}
