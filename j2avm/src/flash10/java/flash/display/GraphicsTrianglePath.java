//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GraphicsTrianglePath extends flash.FlashObject implements flash.display.IGraphicsPath,flash.display.IGraphicsData {

    public  GraphicsTrianglePath( double[] vertices, int[] indices, double[] uvtData, String culling ) {}

    public  GraphicsTrianglePath( double[] vertices, int[] indices, double[] uvtData ) {}

    public  GraphicsTrianglePath( double[] vertices, int[] indices ) {}

    public  GraphicsTrianglePath( double[] vertices ) {}

    public  GraphicsTrianglePath( ) {}

    @Getter
    public native String getCulling( );

    @Getter
    public native int[] getIndices( );

    @Setter
    public native void setIndices( int[] indices );

    @Getter
    public native double[] getVertices( );

    @Setter
    public native void setVertices( double[] vertices );

    @Getter
    public native double[] getUvtData( );

    @Setter
    public native void setUvtData( double[] uvtData );

    @Setter
    public native void setCulling( String value );
}
