//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.geom;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Point extends flash.FlashObject {

    public  Point( double x, double y ) {}

    public  Point( double x ) {}

    public  Point( ) {}

    public static final native flash.geom.Point interpolate( flash.geom.Point pt1, flash.geom.Point pt2, double f );

    public static final native double distance( flash.geom.Point pt1, flash.geom.Point pt2 );

    public static final native flash.geom.Point polar( double len, double angle );

    public native flash.geom.Point add( flash.geom.Point v );

    @Getter
    public native double getLength( );

    public native String toString( );

    public native void normalize( double thickness );

    public native flash.geom.Point subtract( flash.geom.Point v );

    @Getter
    public native double getX( );

    @Setter
    public native void setX( double x );

    @Getter
    public native double getY( );

    @Setter
    public native void setY( double y );

    public native void offset( double dx, double dy );

    public native flash.geom.Point clone( );

    public native boolean equals( flash.geom.Point toCompare );
}
