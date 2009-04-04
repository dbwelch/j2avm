//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class SpaceJustifier extends flash.text.engine.TextJustifier {

    public  SpaceJustifier( String locale, String lineJustification, boolean letterSpacing ) {}

    public  SpaceJustifier( String locale, String lineJustification ) {}

    public  SpaceJustifier( String locale ) {}

    public  SpaceJustifier( ) {}

    @Setter
    public native void setLetterSpacing( boolean value );

    @Override
    public native flash.text.engine.TextJustifier clone( );

    @Getter
    public native boolean getLetterSpacing( );
}
