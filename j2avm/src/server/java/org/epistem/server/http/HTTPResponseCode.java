package org.epistem.server.http;

/**
 * HTTP Response Codes.
 *
 * @author nickmain
 */
public enum HTTPResponseCode {

    SUCCESS_OK        ( 200, "OK" ),
    SUCCESS_CREATED   ( 201, "Created" ),
    SUCCESS_ACCEPTED  ( 202, "Accepted" ),
    SUCCESS_PARTIAL   ( 203, "Partial Information" ),
    SUCCESS_NO_RESULT ( 204, "No Result" ),
    
    REDIRECT_PERM         ( 301, "Moved permanently" ),
    REDIRECT_TEMP         ( 302, "Moved temporarily" ),
    REDIRECT_METHOD       ( 303, "Moved temporarily" ),
    REDIRECT_NOT_MODIFIED ( 304, "Not modified" ),
    
    CLIENT_ERROR_BAD_REQUEST  ( 400, "Bad Request" ),
    CLIENT_ERROR_UNAUTHORIZED ( 401, "Unauthorized" ),
    CLIENT_ERROR_FORBIDDEN    ( 403, "Forbidden" ),
    CLIENT_ERROR_NOT_FOUND    ( 404, "Not Found" ),
    
    SERVER_ERROR_FATAL           ( 500, "Internal Server Error" ),
    SERVER_ERROR_NOT_IMPLEMENTED ( 501, "Not implemented" ),
    SERVER_ERROR_BUSY            ( 503, "Busy - Try Again Later" );
    
    /** The response code */
    public final int code;
    
    /** The reponse message */
    public final String message;

    private HTTPResponseCode( int code, String message ) {
        this.code    = code;
        this.message = message;
    }
    
    /** The code followed by the message */
    @Override
    public String toString() {
        return code + " " + message;
    }
}
