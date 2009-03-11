/*
 * Created on Sep 16, 2003
 */
package org.epistem.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A Queue for incoming requests to the server.
 * 
 * @author nmain
 * @version $Revision: 1.1 $
 */
public class RequestQueue {

	private List<Request> mQueue   = new ArrayList<Request>();
	private int  mMaxSize = 0;
	private boolean mClosed = false;

	/** @param maxSize the max size of the queue, zero for no limit. */
	public RequestQueue( int maxSize ) {
		mMaxSize = maxSize;
	}

	/** Unlimited size queue. */
	public RequestQueue() {
		this(0);
	}

	/** 
	 * Enqueue an incoming request.  Will send a busy response back to the 
	 * client and not enqueue the request if the max size has been reached.
	 */
	public synchronized void enqueue( Request request ) {
		if( mClosed 
		 || ( mMaxSize > 0 && mQueue.size() >= mMaxSize )) {
		 	request.busyClose();
		 	return;
		}
		
		mQueue.add( request );
		notify(); //wake up a dequeuer
	}
	
	/** 
	 * Dequeue a request, blocking until one is available.
	 * 
	 * @return null if the queue has been closed.
	 */
	public synchronized Request dequeue() {
		if( mClosed ) return null;
		
		while( mQueue.isEmpty() ) {
			try { wait(); } catch( Exception ignored ) {}
			if( mClosed ) return null;
		}
		
		return mQueue.remove(0);
	}
	
	/** Clear and close the queue */ 
	public synchronized void close() {
		for (Iterator i = mQueue.iterator(); i.hasNext();) {
			((Request) i.next()).close();
		}		
		mQueue.clear();
		
		mClosed = true;
		notifyAll(); //wake up all blocked dequeue calls
	}
}
