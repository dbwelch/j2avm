//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GradientGlowFilter extends flash.filters.BitmapFilter {

    @Setter
    public native void setColors( flash.FlashArray setColors );

    @Getter
    public native double getStrength( );

    @Setter
    public native void setBlurX( double setBlurX );

    @Setter
    public native void setBlurY( double setBlurY );

    @Setter
    public native void setAngle( double setAngle );

    @Getter
    public native String getType( );

    @Getter
    public native flash.FlashArray getRatios( );

    @Setter
    public native void setStrength( double setStrength );

    @Setter
    public native void setAlphas( flash.FlashArray setAlphas );

    @Getter
    public native flash.FlashArray getColors( );

    @Getter
    public native double getBlurX( );

    @Getter
    public native double getBlurY( );

    @Getter
    public native double getAngle( );

    @Setter
    public native void setKnockout( boolean setKnockout );

    @Getter
    public native flash.FlashArray getAlphas( );

    @Setter
    public native void setRatios( flash.FlashArray setRatios );

    @Setter
    public native void setDistance( double setDistance );

    @Getter
    public native boolean getKnockout( );

    @Setter
    public native void setType( String setType );

    @Getter
    public native double getDistance( );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Setter
    public native void setQuality( int setQuality );

    @Getter
    public native int getQuality( );
}
