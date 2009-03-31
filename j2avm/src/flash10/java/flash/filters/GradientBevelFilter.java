//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GradientBevelFilter extends flash.filters.BitmapFilter {

    public  GradientBevelFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY, double strength, int quality, String type, boolean knockout ) {}

    public  GradientBevelFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY, double strength, int quality, String type ) {}

    public  GradientBevelFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY, double strength, int quality ) {}

    public  GradientBevelFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY, double strength ) {}

    public  GradientBevelFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX, double blurY ) {}

    public  GradientBevelFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, double blurX ) {}

    public  GradientBevelFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios ) {}

    public  GradientBevelFilter( double distance, double angle, flash.FlashArray colors, flash.FlashArray alphas ) {}

    public  GradientBevelFilter( double distance, double angle, flash.FlashArray colors ) {}

    public  GradientBevelFilter( double distance, double angle ) {}

    public  GradientBevelFilter( double distance ) {}

    public  GradientBevelFilter( ) {}

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
    public native double getDistance( );

    @Setter
    public native void setRatios( flash.FlashArray setRatios );

    @Setter
    public native void setDistance( double setDistance );

    @Getter
    public native boolean getKnockout( );

    @Setter
    public native void setType( String setType );

    @Getter
    public native flash.FlashArray getAlphas( );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Setter
    public native void setQuality( int setQuality );

    @Getter
    public native int getQuality( );
}
