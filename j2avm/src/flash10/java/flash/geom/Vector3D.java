//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.geom;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Vector3D extends flash.FlashObject {

    public  Vector3D( double x, double y, double z, double w ) {}

    public  Vector3D( double x, double y, double z ) {}

    public  Vector3D( double x, double y ) {}

    public  Vector3D( double x ) {}

    public  Vector3D( ) {}

    public static final native double distance( flash.geom.Vector3D pt1, flash.geom.Vector3D pt2 );

    public static final native double angleBetween( flash.geom.Vector3D a, flash.geom.Vector3D b );

    @Getter
    public static native flash.geom.Vector3D getZ_AXIS( );

    @Setter
    public static native void setZ_AXIS( flash.geom.Vector3D Z_AXIS );

    @Getter
    public static native flash.geom.Vector3D getY_AXIS( );

    @Setter
    public static native void setY_AXIS( flash.geom.Vector3D Y_AXIS );

    @Getter
    public static native flash.geom.Vector3D getX_AXIS( );

    @Setter
    public static native void setX_AXIS( flash.geom.Vector3D X_AXIS );

    public native void project( );

    @Getter
    public native double getLengthSquared( );

    public native void negate( );

    public native flash.geom.Vector3D add( flash.geom.Vector3D a );

    public native double dotProduct( flash.geom.Vector3D a );

    public native boolean nearEquals( flash.geom.Vector3D toCompare, double tolerance, boolean allFour );

    public native boolean nearEquals( flash.geom.Vector3D toCompare, double tolerance );

    public native void scaleBy( double s );

    public native void decrementBy( flash.geom.Vector3D a );

    public native String toString( );

    @Getter
    public native double getLength( );

    public native flash.geom.Vector3D crossProduct( flash.geom.Vector3D a );

    public native void incrementBy( flash.geom.Vector3D a );

    @Getter
    public native double getW( );

    @Setter
    public native void setW( double w );

    @Getter
    public native double getX( );

    @Setter
    public native void setX( double x );

    @Getter
    public native double getY( );

    @Setter
    public native void setY( double y );

    @Getter
    public native double getZ( );

    @Setter
    public native void setZ( double z );

    public native flash.geom.Vector3D subtract( flash.geom.Vector3D a );

    public native double normalize( );

    public native flash.geom.Vector3D clone( );

    public native boolean equals( flash.geom.Vector3D toCompare, boolean allFour );

    public native boolean equals( flash.geom.Vector3D toCompare );
}
