//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class TextJustifier extends flash.FlashObject {

    public  TextJustifier( String locale, String lineJustification ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected TextJustifier() {}

    public static final native flash.text.engine.TextJustifier getJustifierForLocale( String locale );

    @Setter
    public native void setLineJustification( String value );

    @Getter
    public native String getLocale( );

    public native flash.text.engine.TextJustifier clone( );

    @Getter
    public native String getLineJustification( );
}
