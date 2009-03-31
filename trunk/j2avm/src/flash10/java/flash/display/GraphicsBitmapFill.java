//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GraphicsBitmapFill extends flash.FlashObject implements flash.display.IGraphicsData,flash.display.IGraphicsFill {

    public  GraphicsBitmapFill( flash.display.BitmapData bitmapData, flash.geom.Matrix matrix, boolean repeat, boolean smooth ) {}

    public  GraphicsBitmapFill( flash.display.BitmapData bitmapData, flash.geom.Matrix matrix, boolean repeat ) {}

    public  GraphicsBitmapFill( flash.display.BitmapData bitmapData, flash.geom.Matrix matrix ) {}

    public  GraphicsBitmapFill( flash.display.BitmapData bitmapData ) {}

    public  GraphicsBitmapFill( ) {}

    @Getter
    public native flash.geom.Matrix getMatrix( );

    @Setter
    public native void setMatrix( flash.geom.Matrix setMatrix );

    @Getter
    public native flash.display.BitmapData getBitmapData( );

    @Setter
    public native void setBitmapData( flash.display.BitmapData setBitmapData );

    @Getter
    public native boolean getRepeat( );

    @Setter
    public native void setRepeat( boolean setRepeat );

    @Getter
    public native boolean getSmooth( );

    @Setter
    public native void setSmooth( boolean setSmooth );
}
