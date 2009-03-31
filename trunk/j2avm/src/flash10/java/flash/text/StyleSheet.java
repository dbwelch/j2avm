//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class StyleSheet extends flash.events.EventDispatcher {

    public  StyleSheet( ) {}

    public native flash.text.TextFormat transform( flash.FlashObject formatObject );

    public native void clear( );

    public native void setStyle( String styleName, flash.FlashObject styleObject );

    public native void parseCSS( String CSSText );

    @Getter
    public native flash.FlashArray getStyleNames( );

    public native flash.FlashObject getStyle( String styleName );
}
