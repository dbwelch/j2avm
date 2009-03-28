//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GlowFilter extends flash.filters.BitmapFilter {

    @Getter
    public native double getStrength( );

    @Setter
    public native void setBlurX( double setBlurX );

    @Getter
    public native int getColor( );

    @Setter
    public native void setBlurY( double setBlurY );

    @Setter
    public native void setQuality( int setQuality );

    @Setter
    public native void setColor( int setColor );

    @Setter
    public native void setStrength( double setStrength );

    @Setter
    public native void setInner( boolean setInner );

    @Getter
    public native double getBlurX( );

    @Getter
    public native double getBlurY( );

    @Setter
    public native void setKnockout( boolean setKnockout );

    @Getter
    public native boolean getInner( );

    @Getter
    public native boolean getKnockout( );

    @Setter
    public native void setAlpha( double setAlpha );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Getter
    public native double getAlpha( );

    @Getter
    public native int getQuality( );
}
