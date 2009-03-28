//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class BlurFilter extends flash.filters.BitmapFilter {

    @Getter
    public native double getBlurX( );

    @Setter
    public native void setBlurX( double setBlurX );

    @Setter
    public native void setBlurY( double setBlurY );

    @Getter
    public native double getBlurY( );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Setter
    public native void setQuality( int setQuality );

    @Getter
    public native int getQuality( );
}
