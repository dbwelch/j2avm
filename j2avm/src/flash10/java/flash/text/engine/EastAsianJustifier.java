//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class EastAsianJustifier extends flash.text.engine.TextJustifier {

    public  EastAsianJustifier( String locale, String lineJustification, String justificationStyle ) {}

    public  EastAsianJustifier( String locale, String lineJustification ) {}

    public  EastAsianJustifier( String locale ) {}

    public  EastAsianJustifier( ) {}

    @Override
    public native flash.text.engine.TextJustifier clone( );

    @Setter
    public native void setJustificationStyle( String setJustificationStyle );

    @Getter
    public native String getJustificationStyle( );
}
