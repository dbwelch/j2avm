//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class DropShadowFilter extends flash.filters.BitmapFilter {

    public  DropShadowFilter( double distance, double angle, int color, double alpha, double blurX, double blurY, double strength, int quality, boolean inner, boolean knockout, boolean hideObject ) {}

    public  DropShadowFilter( double distance, double angle, int color, double alpha, double blurX, double blurY, double strength, int quality, boolean inner, boolean knockout ) {}

    public  DropShadowFilter( double distance, double angle, int color, double alpha, double blurX, double blurY, double strength, int quality, boolean inner ) {}

    public  DropShadowFilter( double distance, double angle, int color, double alpha, double blurX, double blurY, double strength, int quality ) {}

    public  DropShadowFilter( double distance, double angle, int color, double alpha, double blurX, double blurY, double strength ) {}

    public  DropShadowFilter( double distance, double angle, int color, double alpha, double blurX, double blurY ) {}

    public  DropShadowFilter( double distance, double angle, int color, double alpha, double blurX ) {}

    public  DropShadowFilter( double distance, double angle, int color, double alpha ) {}

    public  DropShadowFilter( double distance, double angle, int color ) {}

    public  DropShadowFilter( double distance, double angle ) {}

    public  DropShadowFilter( double distance ) {}

    public  DropShadowFilter( ) {}

    @Getter
    public native boolean getHideObject( );

    @Setter
    public native void setBlurX( double value );

    @Getter
    public native int getColor( );

    @Setter
    public native void setBlurY( double value );

    @Setter
    public native void setQuality( int value );

    @Setter
    public native void setAngle( double value );

    @Getter
    public native double getStrength( );

    @Setter
    public native void setHideObject( boolean value );

    @Setter
    public native void setDistance( double value );

    @Setter
    public native void setInner( boolean value );

    @Setter
    public native void setColor( int value );

    @Setter
    public native void setStrength( double value );

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
