//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class utils_functions extends flash.FlashObject {
    
    private utils_functions() {}

    @Function("flash.utils.setTimeout")
    public static native int setTimeout( flash.FlashFunction closure, double delay );

    @Function("flash.utils.setInterval")
    public static native int setInterval( flash.FlashFunction closure, double delay );

    @Function("flash.utils.clearTimeout")
    public static native void clearTimeout( int id );

    @Function("flash.utils.clearInterval")
    public static native void clearInterval( int id );

    @Function("flash.utils.getQualifiedSuperclassName")
    public static native String getQualifiedSuperclassName( Object value );

    @Function("flash.utils.getTimer")
    public static native int getTimer( );

    @Function("flash.utils.describeType")
    public static native flash.FlashXML describeType( Object value );

    @Function("flash.utils.getDefinitionByName")
    public static native flash.FlashObject getDefinitionByName( String name );

    @Function("flash.utils.unescapeMultiByte")
    public static native String unescapeMultiByte( String value );

    @Function("flash.utils.getQualifiedClassName")
    public static native String getQualifiedClassName( Object value );

    @Function("flash.utils.escapeMultiByte")
    public static native String escapeMultiByte( String value );
}
