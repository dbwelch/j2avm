package org.epistem.server.http.handlers;

import java.util.Properties;

import org.epistem.server.http.HTTPRequest;
import org.epistem.server.http.HTTPRequestHandler;
import org.epistem.server.http.MimeType;

/**
 * Handler that tells the server to shut down.
 *
 * @author nickmain
 */
public class HTTPServerKillHandler implements HTTPRequestHandler {

    /**
     * @see org.epistem.server.http.HTTPRequestHandler#handleRequest(org.epistem.server.http.HTTPRequest)
     */
    public boolean handleRequest(HTTPRequest request) throws Exception {
        request.server.shutdown();
        request.response.writeContent( "Server will shutdown", MimeType.TEXT );
        return false;
    }

    /**
     * @see org.epistem.server.http.HTTPRequestHandler#init(java.lang.String, java.util.Properties)
     */
    public void init(String url, Properties props) throws Exception {
        // nada
    }

}
