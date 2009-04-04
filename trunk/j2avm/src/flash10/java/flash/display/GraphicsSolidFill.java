//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GraphicsSolidFill extends flash.FlashObject implements flash.display.IGraphicsFill,flash.display.IGraphicsData {

    public  GraphicsSolidFill( int color, double alpha ) {}

    public  GraphicsSolidFill( int color ) {}

    public  GraphicsSolidFill( ) {}

    @Getter
    public native int getColor( );

    @Setter
    public native void setColor( int color );

    @Getter
    public native double getAlpha( );

    @Setter
    public native void setAlpha( double alpha );
}
