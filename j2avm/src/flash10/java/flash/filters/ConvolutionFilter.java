//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ConvolutionFilter extends flash.filters.BitmapFilter {

    public  ConvolutionFilter( double matrixX, double matrixY, flash.FlashArray matrix, double divisor, double bias, boolean preserveAlpha, boolean clamp, int color, double alpha ) {}

    public  ConvolutionFilter( double matrixX, double matrixY, flash.FlashArray matrix, double divisor, double bias, boolean preserveAlpha, boolean clamp, int color ) {}

    public  ConvolutionFilter( double matrixX, double matrixY, flash.FlashArray matrix, double divisor, double bias, boolean preserveAlpha, boolean clamp ) {}

    public  ConvolutionFilter( double matrixX, double matrixY, flash.FlashArray matrix, double divisor, double bias, boolean preserveAlpha ) {}

    public  ConvolutionFilter( double matrixX, double matrixY, flash.FlashArray matrix, double divisor, double bias ) {}

    public  ConvolutionFilter( double matrixX, double matrixY, flash.FlashArray matrix, double divisor ) {}

    public  ConvolutionFilter( double matrixX, double matrixY, flash.FlashArray matrix ) {}

    public  ConvolutionFilter( double matrixX, double matrixY ) {}

    public  ConvolutionFilter( double matrixX ) {}

    public  ConvolutionFilter( ) {}

    @Getter
    public native flash.FlashArray getMatrix( );

    @Setter
    public native void setMatrix( flash.FlashArray value );

    @Getter
    public native int getColor( );

    @Setter
    public native void setPreserveAlpha( boolean value );

    @Getter
    public native double getAlpha( );

    @Setter
    public native void setColor( int value );

    @Setter
    public native void setBias( double value );

    @Setter
    public native void setAlpha( double value );

    @Setter
    public native void setMatrixX( double value );

    @Setter
    public native void setMatrixY( double value );

    @Getter
    public native boolean getPreserveAlpha( );

    @Setter
    public native void setClamp( boolean value );

    @Getter
    public native double getMatrixX( );

    @Getter
    public native double getMatrixY( );

    @Getter
    public native double getBias( );

    @Getter
    public native boolean getClamp( );

    @Setter
    public native void setDivisor( double value );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Getter
    public native double getDivisor( );
}
