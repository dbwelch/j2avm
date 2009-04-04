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
    public native double getDistance( );

    @Setter
    public native void setRatios( flash.FlashArray value );

    @Setter
    public native void setDistance( double value );

    @Getter
    public native boolean getKnockout( );

    @Setter
    public native void setType( String value );

    @Getter
    public native flash.FlashArray getAlphas( );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Setter
    public native void setQuality( int value );

    @Getter
    public native int getQuality( );
}
