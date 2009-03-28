//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class FontDescription extends flash.FlashObject {

    public static final native boolean isFontCompatible( String fontName, String fontWeight, String fontPosture );

    @Setter
    public native void setFontLookup( String setFontLookup );

    @Getter
    public native String getFontWeight( );

    @Getter
    public native String getFontLookup( );

    @Getter
    public native boolean getLocked( );

    @Setter
    public native void setFontWeight( String setFontWeight );

    @Getter
    public native String getRenderingMode( );

    @Setter
    public native void setCffHinting( String setCffHinting );

    @Setter
    public native void setFontPosture( String setFontPosture );

    @Setter
    public native void setFontName( String setFontName );

    @Getter
    public native String getCffHinting( );

    @Getter
    public native String getFontPosture( );

    @Getter
    public native String getFontName( );

    @Setter
    public native void setLocked( boolean setLocked );

    public native flash.text.engine.FontDescription clone( );

    @Setter
    public native void setRenderingMode( String setRenderingMode );
}
