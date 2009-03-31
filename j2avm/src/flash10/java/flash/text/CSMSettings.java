//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class CSMSettings extends flash.FlashObject {

    public  CSMSettings( double fontSize, double insideCutoff, double outsideCutoff ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected CSMSettings() {}

    @Getter
    public native double getFontSize( );

    @Setter
    public native void setFontSize( double setFontSize );

    @Getter
    public native double getOutsideCutoff( );

    @Setter
    public native void setOutsideCutoff( double setOutsideCutoff );

    @Getter
    public native double getInsideCutoff( );

    @Setter
    public native void setInsideCutoff( double setInsideCutoff );
}
