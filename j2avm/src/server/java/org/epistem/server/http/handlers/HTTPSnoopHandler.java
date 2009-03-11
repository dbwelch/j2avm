package org.epistem.server.http.handlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

import org.epistem.server.http.HTTPHeader;
import org.epistem.server.http.HTTPMethod;
import org.epistem.server.http.HTTPRequest;
import org.epistem.server.http.HTTPRequestHandler;
import org.epistem.server.http.HTTPResponse;
import org.epistem.server.http.MimeType;
import org.epistem.util.Hex;

/**
 * The classic "Snoop" handler
 *
 * @author nickmain
 */
public class HTTPSnoopHandler implements HTTPRequestHandler {
	
    /**
     * @see org.epistem.server.http.HTTPRequestHandler#init(java.lang.String, java.util.Properties)
     */
	public void init( String url, Properties props ) {
		//nada
	}
	
    /**
     * @see org.epistem.server.http.HTTPRequestHandler#handleRequest(org.epistem.server.http.HTTPRequest)
     */
    public boolean handleRequest( HTTPRequest request ) {
       
        HTTPResponse resp = request.response;
        
    	try {
			Map<HTTPHeader,String> headers = request.headers;

            PrintWriter printer = resp.startTextContent( MimeType.HTML );
            
	        printer.println( "<html><head><title>HTTP Snoop</title></head><body>" );
	        printer.println( "<h3 align=center>HTTP Snoop</h3>" );
	        printer.println( "<table cellpadding=3 border=0>" );
	
	        printer.println( "<tr><td>Method:</td><td><b>"         + request.method + "</b></td></tr>" );
            printer.println( "<tr><td>Host:</td><td><b>"           + request.host + "</b></td></tr>" );
	        printer.println( "<tr><td>Path:</td><td><b>"           + request.path + "</b></td></tr>" );
	        printer.println( "<tr><td>HTTP Ver:</td><td><b>"       + request.httpVer + "</b></td></tr>" );
	        printer.println( "<tr><td>Query String:</td><td><b>"   + request.queryString + "</b></td></tr>" );
            printer.println( "<tr><td>Handler Path:</td><td><b>"   + request.handlerPath + "</b></td></tr>" );
            printer.println( "<tr><td>Remaining Path:</td><td><b>" + request.remainingPath + "</b></td></tr>" );
	
	        String[] userpw = request.getUserPassword();
	        if( userpw != null ) {
	            printer.println( "<tr><td>User:</td><td><b>" + userpw[0] + "</b></td></tr>" );
	            printer.println( "<tr><td>Password:</td><td><b>" + userpw[1] + "</b></td></tr>" );
	        }
	
	        printer.println( "</table><hr>Headers:<ol>" );
	
            for( Map.Entry<HTTPHeader,String> entry : headers.entrySet() ) {
	            printer.println( "<li>" + entry.getKey().key + " = <b>" + entry.getValue() + "</b></li>" );
	        }
	
	        printer.println( "</ol>" );
	
	        if( request.method == HTTPMethod.POST ) {
	            int length = request.contentLength;
	
	            if( length > 0 ) {
	            	InputStream in = request.postData;

                    byte[] buff = new byte[ length ];
                    
                    new DataInputStream( in ).readFully( buff );
                    
                    printer.println( "<hr>Post Data:<br><pre>" );
                    printer.println( Hex.dump( buff, 0, "    " ));
                    printer.println( "</pre>" );
	            }
	        }
	
	        printer.println( "</body></html>" );
	        printer.close();
            
		} catch( IOException ioe ) { request.response.sendException( ioe.toString(), ioe ); }

		return false;    
    }
}
