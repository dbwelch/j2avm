//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.desktop;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Clipboard extends flash.FlashObject {

    public  Clipboard( ) {}

    @Getter
    public static final native flash.desktop.Clipboard getGeneralClipboard( );

    public native void clear( );

    public native void clearData( String format );

    @Getter
    public native flash.FlashArray getFormats( );

    public native flash.FlashObject getData( String format, String transferMode );

    public native flash.FlashObject getData( String format );

    public native boolean setData( String format, flash.FlashObject data, boolean serializable );

    public native boolean setData( String format, flash.FlashObject data );

    public native boolean hasFormat( String format );

    public native boolean setDataHandler( String format, flash.FlashFunction handler, boolean serializable );

    public native boolean setDataHandler( String format, flash.FlashFunction handler );
}
