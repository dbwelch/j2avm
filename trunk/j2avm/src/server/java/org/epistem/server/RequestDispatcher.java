/*
 * Created on Sep 16, 2003
 */
package org.epistem.server;

/**
 * A dispatcher thread for processing server requests.
 * 
 * @author nmain
 * @version $Revision: 1.1 $
 */
public class RequestDispatcher implements Runnable {

	/** Make a number of dispatcher threads. */
	public static void makePool( RequestQueue queue, int numThreads ) {
		for (int i = 1; i <= numThreads; i++) {
			new  RequestDispatcher( "Request Dispatcher " + i, queue );
		}
	}

	private RequestQueue mQueue;
	
	/** Start a dispatcher thread. */
	public RequestDispatcher( String name, RequestQueue queue ) {
		mQueue = queue;
		new Thread( this, name ).start();
	}

	public void run() {
		Request request = null;
		
		while( (request = mQueue.dequeue() ) != null ) {
			request.processRequest();
		}
	}
}
