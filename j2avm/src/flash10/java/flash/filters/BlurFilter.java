//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class BlurFilter extends flash.filters.BitmapFilter {

    public  BlurFilter( double blurX, double blurY, int quality ) {}

    public  BlurFilter( double blurX, double blurY ) {}

    public  BlurFilter( double blurX ) {}

    public  BlurFilter( ) {}

    @Getter
    public native double getBlurX( );

    @Setter
    public native void setBlurX( double value );

    @Setter
    public native void setBlurY( double value );

    @Getter
    public native double getBlurY( );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Setter
    public native void setQuality( int value );

    @Getter
    public native int getQuality( );
}
