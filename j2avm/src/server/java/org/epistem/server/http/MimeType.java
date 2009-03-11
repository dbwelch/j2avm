package org.epistem.server.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Mime types.
 *
 * @author nickmain
 */
public enum MimeType {

    HTML  ( "text/html"                        , "html", "htm" ),
    GIF   ( "image/gif"                        , "gif"  ),
    PNG   ( "image/png"                        , "png"  ),
    JPEG  ( "image/jpeg"                       , "jpg", "jpe", "jpeg" ),
    TEXT  ( "text/plain"                       , "txt"  ),
    XML   ( "text/xml"                         , "xml"  ),
    BINARY( "application/octet-stream"         , "bin", "exe", "dat", "class" ),
    FORM  ( "application/x-www-form-urlencoded", "data" ),
    SWF   ( "application/x-shockwave-flash"    , "swf"  );
    
    /** Filename suffixes */
    public final String[] suffixes;
    
    /** Mime type string */
    public final String mimeType;
    
    private MimeType( String mimeType, String... suffixes ) {
        this.mimeType = mimeType;
        this.suffixes = suffixes;
    }
    
    /**
     * Get the mime type for a given file suffix.
     * @return null if not found
     */
    public static MimeType forSuffix( String suffix ) {
        return suffixToType.get( suffix );
    }
    
    /** Map of suffix to type */
    private static Map<String, MimeType> suffixToType = new HashMap<String, MimeType>();
    static {
        for (MimeType mt : values()) {
            for (String suffix : mt.suffixes) {
                suffixToType.put( suffix, mt );
            }
        }
    }
    
    /** The mime type string */
    @Override
    public String toString() {
        return mimeType;
    }
}
