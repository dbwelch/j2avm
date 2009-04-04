//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GraphicsPath extends flash.FlashObject implements flash.display.IGraphicsPath,flash.display.IGraphicsData {

    public  GraphicsPath( int[] commands, double[] data, String winding ) {}

    public  GraphicsPath( int[] commands, double[] data ) {}

    public  GraphicsPath( int[] commands ) {}

    public  GraphicsPath( ) {}

    public native void wideLineTo( double x, double y );

    @Setter
    public native void setWinding( String value );

    @Getter
    public native int[] getCommands( );

    @Setter
    public native void setCommands( int[] commands );

    @Getter
    public native double[] getData( );

    @Setter
    public native void setData( double[] data );

    public native void moveTo( double x, double y );

    public native void lineTo( double x, double y );

    public native void curveTo( double controlX, double controlY, double anchorX, double anchorY );

    @Getter
    public native String getWinding( );

    public native void wideMoveTo( double x, double y );
}
