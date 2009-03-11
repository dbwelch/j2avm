/*
 * Created on Sep 16, 2003
 */
package org.epistem.server.http;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An HTTP context (set of mapped handlers).
 * Handlers are mapped by regexes which are tested in the order they were
 * added to the context.
 * 
 * @author nmain
 */
public class HTTPContext {
	private Map<Pattern,HTTPRequestHandler> urlMappings = 
        new LinkedHashMap<Pattern,HTTPRequestHandler>();
	
	public HTTPContext() {}
	
	/** 
     * Add a request handler for the given url regex.  The order in which handlers
     * are added is significant. 
     */
	public void addHandler( String urlRegex, HTTPRequestHandler handler ) {
        urlMappings.put( Pattern.compile( urlRegex ), handler );
	}
	
	/**
	 * Get a request handler for a given url.  
	 * 
	 * @param pathInfo String[2] fill in { handlerPath, remainingPath }.
	 * @return null if no handler exists.
	 */
	public HTTPRequestHandler getHandler( String url, String[] pathInfo ) {

        HTTPRequestHandler handler = null;
        
        for( Pattern pattern : urlMappings.keySet() ) {
            Matcher matcher = pattern.matcher( url );
            if( ! matcher.matches() ) continue;
        
            handler = urlMappings.get( pattern );
            int groupCount = matcher.groupCount();
            for( int i = 0; i < pathInfo.length && i < groupCount; i++ ) {
                pathInfo[i] = matcher.group( i + 1 );
            }
            
            break;
        }
        		
		return handler;
	}
}
