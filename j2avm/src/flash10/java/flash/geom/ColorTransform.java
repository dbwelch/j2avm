//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.geom;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ColorTransform extends flash.FlashObject {

    public  ColorTransform( double redMultiplier, double greenMultiplier, double blueMultiplier, double alphaMultiplier, double redOffset, double greenOffset, double blueOffset, double alphaOffset ) {}

    public  ColorTransform( double redMultiplier, double greenMultiplier, double blueMultiplier, double alphaMultiplier, double redOffset, double greenOffset, double blueOffset ) {}

    public  ColorTransform( double redMultiplier, double greenMultiplier, double blueMultiplier, double alphaMultiplier, double redOffset, double greenOffset ) {}

    public  ColorTransform( double redMultiplier, double greenMultiplier, double blueMultiplier, double alphaMultiplier, double redOffset ) {}

    public  ColorTransform( double redMultiplier, double greenMultiplier, double blueMultiplier, double alphaMultiplier ) {}

    public  ColorTransform( double redMultiplier, double greenMultiplier, double blueMultiplier ) {}

    public  ColorTransform( double redMultiplier, double greenMultiplier ) {}

    public  ColorTransform( double redMultiplier ) {}

    public  ColorTransform( ) {}

    @Getter
    public native int getColor( );

    @Getter
    public native double getRedOffset( );

    @Setter
    public native void setRedOffset( double redOffset );

    @Setter
    public native void setColor( int newColor );

    @Getter
    public native double getGreenMultiplier( );

    @Setter
    public native void setGreenMultiplier( double greenMultiplier );

    @Getter
    public native double getBlueOffset( );

    @Setter
    public native void setBlueOffset( double blueOffset );

    public native String toString( );

    @Getter
    public native double getAlphaOffset( );

    @Setter
    public native void setAlphaOffset( double alphaOffset );

    @Getter
    public native double getRedMultiplier( );

    @Setter
    public native void setRedMultiplier( double redMultiplier );

    public native void concat( flash.geom.ColorTransform second );

    @Getter
    public native double getBlueMultiplier( );

    @Setter
    public native void setBlueMultiplier( double blueMultiplier );

    @Getter
    public native double getGreenOffset( );

    @Setter
    public native void setGreenOffset( double greenOffset );

    @Getter
    public native double getAlphaMultiplier( );

    @Setter
    public native void setAlphaMultiplier( double alphaMultiplier );
}
