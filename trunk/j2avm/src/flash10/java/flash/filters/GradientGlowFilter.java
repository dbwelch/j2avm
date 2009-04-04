//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GradientGlowFilter extends flash.filters.BitmapFilter {

    public  GradientGlowFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY, double strength, int quality, String type, boolean knockout ) {}

    public  GradientGlowFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY, double strength, int quality, String type ) {}

    public  GradientGlowFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY, double strength, int quality ) {}

    public  GradientGlowFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY, double strength ) {}

    public  GradientGlowFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY ) {}

    public  GradientGlowFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX ) {}

    public  GradientGlowFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios ) {}

    public  GradientGlowFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas ) {}

    public  GradientGlowFilter( double distance, double angle, flash.FlashArray colors ) {}

    public  GradientGlowFilter( double distance, double angle ) {}

    public  GradientGlowFilter( double distance ) {}

    public  GradientGlowFilter( ) {}

    @Setter
    public native void setColors( flash.FlashArray value );

    @Getter
    public native double getStrength( );

    @Setter
    public native void setBlurX( double value );

    @Setter
    public native void setBlurY( double value );

    @Setter
    public native void setAngle( double value );

    @Getter
    public native String getType( );

    @Getter
    public native flash.FlashArray getRatios( );

    @Setter
    public native void setStrength( double value );

    @Setter
    public native void setAlphas( flash.FlashArray value );

    @Getter
    public native flash.FlashArray getColors( );

    @Getter
    public native double getBlurX( );

    @Getter
    public native double getBlurY( );

    @Getter
    public native double getAngle( );

    @Setter
    public native void setKnockout( boolean value );

    @Getter
    public native flash.FlashArray getAlphas( );

    @Setter
    public native void setRatios( flash.FlashArray value );

    @Setter
    public native void setDistance( double value );

    @Getter
    public native boolean getKnockout( );

    @Setter
    public native void setType( String value );

    @Getter
    public native double getDistance( );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Setter
    public native void setQuality( int value );

    @Getter
    public native int getQuality( );
}
