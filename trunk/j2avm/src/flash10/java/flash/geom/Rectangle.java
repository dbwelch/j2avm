//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.geom;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Rectangle extends flash.FlashObject {

    public  Rectangle( double x, double y, double width, double height ) {}

    public  Rectangle( double x, double y, double width ) {}

    public  Rectangle( double x, double y ) {}

    public  Rectangle( double x ) {}

    public  Rectangle( ) {}

    public native boolean containsPoint( flash.geom.Point point );

    @Getter
    public native flash.geom.Point getSize( );

    @Setter
    public native void setSize( flash.geom.Point value );

    public native boolean isEmpty( );

    @Getter
    public native double getWidth( );

    @Setter
    public native void setWidth( double width );

    @Getter
    public native double getLeft( );

    public native void inflatePoint( flash.geom.Point point );

    public native void setEmpty( );

    @Setter
    public native void setLeft( double value );

    public native flash.geom.Rectangle union( flash.geom.Rectangle toUnion );

    @Setter
    public native void setRight( double value );

    public native void offset( double dx, double dy );

    @Getter
    public native double getTop( );

    @Setter
    public native void setBottom( double value );

    public native boolean equals( flash.geom.Rectangle toCompare );

    public native flash.geom.Rectangle intersection( flash.geom.Rectangle toIntersect );

    @Getter
    public native double getRight( );

    public native flash.geom.Rectangle clone( );

    public native void inflate( double dx, double dy );

    @Setter
    public native void setBottomRight( flash.geom.Point value );

    public native boolean containsRect( flash.geom.Rectangle rect );

    @Getter
    public native double getHeight( );

    @Setter
    public native void setHeight( double height );

    @Setter
    public native void setTop( double value );

    @Getter
    public native double getBottom( );

    public native String toString( );

    public native boolean contains( double x, double y );

    @Getter
    public native flash.geom.Point getBottomRight( );

    public native boolean intersects( flash.geom.Rectangle toIntersect );

    @Getter
    public native double getX( );

    @Setter
    public native void setX( double x );

    @Getter
    public native double getY( );

    @Setter
    public native void setY( double y );

    @Getter
    public native flash.geom.Point getTopLeft( );

    public native void offsetPoint( flash.geom.Point point );

    @Setter
    public native void setTopLeft( flash.geom.Point value );
}
