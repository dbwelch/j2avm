//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GraphicsGradientFill extends flash.FlashObject implements flash.display.IGraphicsFill,flash.display.IGraphicsData {

    public  GraphicsGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, Object matrix, Object spreadMethod, String interpolationMethod, double focalPointRatio ) {}

    public  GraphicsGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, Object matrix, Object spreadMethod, String interpolationMethod ) {}

    public  GraphicsGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, Object matrix, Object spreadMethod ) {}

    public  GraphicsGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, Object matrix ) {}

    public  GraphicsGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios ) {}

    public  GraphicsGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas ) {}

    public  GraphicsGradientFill( String type, flash.FlashArray colors ) {}

    public  GraphicsGradientFill( String type ) {}

    public  GraphicsGradientFill( ) {}

    @Getter
    public native flash.geom.Matrix getMatrix( );

    @Setter
    public native void setMatrix( flash.geom.Matrix matrix );

    @Getter
    public native String getInterpolationMethod( );

    @Getter
    public native double getFocalPointRatio( );

    @Setter
    public native void setFocalPointRatio( double focalPointRatio );

    @Getter
    public native flash.FlashArray getRatios( );

    @Setter
    public native void setRatios( flash.FlashArray ratios );

    @Setter
    public native void setSpreadMethod( String value );

    @Getter
    public native flash.FlashArray getColors( );

    @Setter
    public native void setColors( flash.FlashArray colors );

    @Setter
    public native void setInterpolationMethod( String value );

    @Setter
    public native void setType( String value );

    @Getter
    public native flash.FlashArray getAlphas( );

    @Setter
    public native void setAlphas( flash.FlashArray alphas );

    @Getter
    public native String getType( );

    @Getter
    public native String getSpreadMethod( );
}
