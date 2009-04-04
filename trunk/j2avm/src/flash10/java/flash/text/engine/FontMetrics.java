//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class FontMetrics extends flash.FlashObject {

    public  FontMetrics( flash.geom.Rectangle emBox, double strikethroughOffset, double strikethroughThickness, double underlineOffset, double underlineThickness, double subscriptOffset, double subscriptScale, double superscriptOffset, double superscriptScale ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected FontMetrics() {}

    @Getter
    public native double getStrikethroughThickness( );

    @Setter
    public native void setStrikethroughThickness( double strikethroughThickness );

    @Getter
    public native flash.geom.Rectangle getEmBox( );

    @Setter
    public native void setEmBox( flash.geom.Rectangle emBox );

    @Getter
    public native double getSuperscriptScale( );

    @Setter
    public native void setSuperscriptScale( double superscriptScale );

    @Getter
    public native double getStrikethroughOffset( );

    @Setter
    public native void setStrikethroughOffset( double strikethroughOffset );

    @Getter
    public native double getUnderlineThickness( );

    @Setter
    public native void setUnderlineThickness( double underlineThickness );

    @Getter
    public native double getSubscriptScale( );

    @Setter
    public native void setSubscriptScale( double subscriptScale );

    @Getter
    public native double getSuperscriptOffset( );

    @Setter
    public native void setSuperscriptOffset( double superscriptOffset );

    @Getter
    public native double getSubscriptOffset( );

    @Setter
    public native void setSubscriptOffset( double subscriptOffset );

    @Getter
    public native double getUnderlineOffset( );

    @Setter
    public native void setUnderlineOffset( double underlineOffset );
}
