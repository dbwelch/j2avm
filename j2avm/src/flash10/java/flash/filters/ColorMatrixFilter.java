//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class ColorMatrixFilter extends flash.filters.BitmapFilter {

    public  ColorMatrixFilter( flash.FlashArray matrix ) {}

    public  ColorMatrixFilter( ) {}

    @Getter
    public native flash.FlashArray getMatrix( );

    @Setter
    public native void setMatrix( flash.FlashArray value );

    @Override
    public native flash.filters.BitmapFilter clone( );
}
