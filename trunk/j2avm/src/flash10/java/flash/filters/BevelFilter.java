//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class BevelFilter extends flash.filters.BitmapFilter {

    public  BevelFilter( double distance, double angle, int highlightColor, double highlightAlpha, int shadowColor, double shadowAlpha, double blurX, double blurY, double strength, int quality, String type, boolean knockout ) {}

    public  BevelFilter( double distance, double angle, int highlightColor, double highlightAlpha, int shadowColor, double shadowAlpha, double blurX, double blurY, double strength, int quality, String type ) {}

    public  BevelFilter( double distance, double angle, int highlightColor, double highlightAlpha, int shadowColor, double shadowAlpha, double blurX, double blurY, double strength, int quality ) {}

    public  BevelFilter( double distance, double angle, int highlightColor, double highlightAlpha, int shadowColor, double shadowAlpha, double blurX, double blurY, double strength ) {}

    public  BevelFilter( double distance, double angle, int highlightColor, double highlightAlpha, int shadowColor, double shadowAlpha, double blurX, double blurY ) {}

    public  BevelFilter( double distance, double angle, int highlightColor, double highlightAlpha, int shadowColor, double shadowAlpha, double blurX ) {}

    public  BevelFilter( double distance, double angle, int highlightColor, double highlightAlpha, int shadowColor, double shadowAlpha ) {}

    public  BevelFilter( double distance, double angle, int highlightColor, double highlightAlpha, int shadowColor ) {}

    public  BevelFilter( double distance, double angle, int highlightColor, double highlightAlpha ) {}

    public  BevelFilter( double distance, double angle, int highlightColor ) {}

    public  BevelFilter( double distance, double angle ) {}

    public  BevelFilter( double distance ) {}

    public  BevelFilter( ) {}

    @Getter
    public native double getStrength( );

    @Setter
    public native void setStrength( double setStrength );

    @Setter
    public native void setShadowColor( int setShadowColor );

    @Getter
    public native boolean getKnockout( );

    @Getter
    public native double getHighlightAlpha( );

    @Getter
    public native int getHighlightColor( );

    @Getter
    public native double getBlurX( );

    @Getter
    public native double getBlurY( );

    @Getter
    public native double getAngle( );

    @Setter
    public native void setHighlightAlpha( double setHighlightAlpha );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Setter
    public native void setHighlightColor( int setHighlightColor );

    @Setter
    public native void setBlurX( double setBlurX );

    @Getter
    public native int getShadowColor( );

    @Setter
    public native void setBlurY( double setBlurY );

    @Getter
    public native double getShadowAlpha( );

    @Setter
    public native void setAngle( double setAngle );

    @Setter
    public native void setDistance( double setDistance );

    @Setter
    public native void setType( String setType );

    @Getter
    public native double getDistance( );

    @Getter
    public native String getType( );

    @Setter
    public native void setKnockout( boolean setKnockout );

    @Setter
    public native void setShadowAlpha( double setShadowAlpha );

    @Setter
    public native void setQuality( int setQuality );

    @Getter
    public native int getQuality( );
}