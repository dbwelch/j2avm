/*
 * Created on Sep 16, 2003
 */
package org.epistem.server.http;

import java.util.Properties;

/**
 * Handler for an HTTP request.
 * 
 * @author nmain
 * @version $Revision: 1.1 $
 */
public interface HTTPRequestHandler {

	/** Called by the context to initialize the handler. */
	public void init( String url, Properties props ) throws Exception;
	
	/** 
	 * Handle the request
	 * 
	 * @return true if the request should be kept open.
	 */
	public boolean handleRequest( HTTPRequest request ) throws Exception; 
}
