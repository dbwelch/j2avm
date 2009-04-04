//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class FontDescription extends flash.FlashObject {

    public  FontDescription( String fontName, String fontWeight, String fontPosture, String fontLookup, String renderingMode, String cffHinting ) {}

    public  FontDescription( String fontName, String fontWeight, String fontPosture, String fontLookup, String renderingMode ) {}

    public  FontDescription( String fontName, String fontWeight, String fontPosture, String fontLookup ) {}

    public  FontDescription( String fontName, String fontWeight, String fontPosture ) {}

    public  FontDescription( String fontName, String fontWeight ) {}

    public  FontDescription( String fontName ) {}

    public  FontDescription( ) {}

    public static final native boolean isFontCompatible( String fontName, String fontWeight, String fontPosture );

    @Setter
    public native void setFontLookup( String value );

    @Getter
    public native String getFontWeight( );

    @Getter
    public native String getFontLookup( );

    @Getter
    public native boolean getLocked( );

    @Setter
    public native void setFontWeight( String value );

    @Getter
    public native String getRenderingMode( );

    @Setter
    public native void setCffHinting( String value );

    @Setter
    public native void setFontPosture( String value );

    @Setter
    public native void setFontName( String value );

    @Getter
    public native String getCffHinting( );

    @Getter
    public native String getFontPosture( );

    @Getter
    public native String getFontName( );

    @Setter
    public native void setLocked( boolean value );

    public native flash.text.engine.FontDescription clone( );

    @Setter
    public native void setRenderingMode( String value );
}
