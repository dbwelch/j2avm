//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GlowFilter extends flash.filters.BitmapFilter {

    public  GlowFilter( int color, double alpha, double blurX, double blurY, double strength, int quality, boolean inner, boolean knockout ) {}

    public  GlowFilter( int color, double alpha, double blurX, double blurY, double strength, int quality, boolean inner ) {}

    public  GlowFilter( int color, double alpha, double blurX, double blurY, double strength, int quality ) {}

    public  GlowFilter( int color, double alpha, double blurX, double blurY, double strength ) {}

    public  GlowFilter( int color, double alpha, double blurX, double blurY ) {}

    public  GlowFilter( int color, double alpha, double blurX ) {}

    public  GlowFilter( int color, double alpha ) {}

    public  GlowFilter( int color ) {}

    public  GlowFilter( ) {}

    @Getter
    public native double getStrength( );

    @Setter
    public native void setBlurX( double value );

    @Getter
    public native int getColor( );

    @Setter
    public native void setBlurY( double value );

    @Setter
    public native void setQuality( int value );

    @Setter
    public native void setColor( int value );

    @Setter
    public native void setStrength( double value );

    @Setter
    public native void setInner( boolean value );

    @Getter
    public native double getBlurX( );

    @Getter
    public native double getBlurY( );

    @Setter
    public native void setKnockout( boolean value );

    @Getter
    public native boolean getInner( );

    @Getter
    public native boolean getKnockout( );

    @Setter
    public native void setAlpha( double value );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Getter
    public native double getAlpha( );

    @Getter
    public native int getQuality( );
}
