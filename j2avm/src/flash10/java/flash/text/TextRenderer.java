//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class TextRenderer extends flash.FlashObject {

    public  TextRenderer( ) {}

    @Setter
    public static final native void setMaxLevel( int value );

    @Getter
    public static final native String getDisplayMode( );

    public static final native void setAdvancedAntiAliasingTable( String fontName, String fontStyle, String colorType, flash.FlashArray advancedAntiAliasingTable );

    @Getter
    public static final native int getMaxLevel( );

    @Getter
    public static final native String getAntiAliasType( );

    @Setter
    public static final native void setDisplayMode( String value );

    @Setter
    public static final native void setAntiAliasType( String value );
}
