//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Font extends flash.FlashObject {

    public  Font( ) {}

    public static final native flash.FlashArray enumerateFonts( boolean enumerateDeviceFonts );

    public static final native flash.FlashArray enumerateFonts( );

    public static final native void registerFont( flash.FlashClass font );

    @Getter
    public native String getFontType( );

    @Getter
    public native String getFontStyle( );

    @Getter
    public native String getFontName( );

    public native boolean hasGlyphs( String str );
}
