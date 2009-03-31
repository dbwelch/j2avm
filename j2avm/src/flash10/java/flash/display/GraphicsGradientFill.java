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
    public native void setMatrix( flash.geom.Matrix setMatrix );

    @Getter
    public native String getInterpolationMethod( );

    @Getter
    public native double getFocalPointRatio( );

    @Setter
    public native void setFocalPointRatio( double setFocalPointRatio );

    @Getter
    public native flash.FlashArray getRatios( );

    @Setter
    public native void setRatios( flash.FlashArray setRatios );

    @Setter
    public native void setSpreadMethod( String setSpreadMethod );

    @Getter
    public native flash.FlashArray getColors( );

    @Setter
    public native void setColors( flash.FlashArray setColors );

    @Setter
    public native void setInterpolationMethod( String setInterpolationMethod );

    @Setter
    public native void setType( String setType );

    @Getter
    public native flash.FlashArray getAlphas( );

    @Setter
    public native void setAlphas( flash.FlashArray setAlphas );

    @Getter
    public native String getType( );

    @Getter
    public native String getSpreadMethod( );
}
