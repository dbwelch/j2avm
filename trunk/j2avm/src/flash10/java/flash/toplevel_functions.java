//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class toplevel_functions extends flash.FlashObject {
    
    private toplevel_functions() {}

    @Function("escape")
    public static native String escape( String s );

    @Function("escape")
    public static native String escape( );

    @Function("encodeURI")
    public static native String encodeURI( String uri );

    @Function("encodeURI")
    public static native String encodeURI( );

    @Function("decodeURI")
    public static native String decodeURI( String uri );

    @Function("decodeURI")
    public static native String decodeURI( );

    @Function("encodeURIComponent")
    public static native String encodeURIComponent( String uri );

    @Function("encodeURIComponent")
    public static native String encodeURIComponent( );

    @Function("parseInt")
    public static native double parseInt( String s, int radix );

    @Function("parseInt")
    public static native double parseInt( String s );

    @Function("parseInt")
    public static native double parseInt( );

    @Function("unescape")
    public static native String unescape( String s );

    @Function("unescape")
    public static native String unescape( );

    @Function("parseFloat")
    public static native double parseFloat( String str );

    @Function("parseFloat")
    public static native double parseFloat( );

    @Function("isNaN")
    public static native boolean isNaN( double n );

    @Function("isFinite")
    public static native boolean isFinite( double n );

    @Function("decodeURIComponent")
    public static native String decodeURIComponent( String uri );

    @Function("decodeURIComponent")
    public static native String decodeURIComponent( );

    @Function("isXMLName")
    public static native boolean isXMLName( Object str );

    @Function("trace")
    public static native void trace( );
}
