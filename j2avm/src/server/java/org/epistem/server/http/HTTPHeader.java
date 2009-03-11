package org.epistem.server.http;

/**
 * Types of HTTP Header
 *
 * @author nickmain
 */
public enum HTTPHeader {

    Accept,
    Accept_Charset,
    Accept_Encoding,
    Accept_Language,
    Accept_Ranges,
    Age,
    Allow,
    Authorization,
    Cache_Control,
    Connection,
    Content_Encoding,
    Content_Language,
    Content_Length,
    Content_Location,
    Content_MD5,
    Content_Range,
    Content_Type,
    Date,
    ETag,
    Expect,
    Expires,
    From,
    Host,
    If_Match,
    If_Modified_Since,
    If_None_Match,
    If_Range,
    If_Unmodified_Since,
    Last_Modified,
    Location,
    Max_Forwards,
    Pragma,
    Proxy_Authenticate,
    Proxy_Authorization,
    Range,
    Referer,
    Retry_After,
    Server,
    TE,
    Trailer,
    Transfer_Encoding,
    Upgrade,
    User_Agent,
    Vary,
    Via,
    Warning,
    WWW_Authenticate,
    Cookie,
    Set_Cookie
    
    ;
    
    /**
     * The header key
     */
    public final String key;
    
    private HTTPHeader() {
        key = name().replace( '_', '-' );
    }
    
    /**
     * Get a header instance from the http string
     * @return null if the header is unknown
     */
    public static HTTPHeader fromString( String s ) {
        s = s.replace( '-', '_' );
        try {            
            return valueOf( s );
        } catch( Exception ex ) {}
        
        return null;
    }
}
