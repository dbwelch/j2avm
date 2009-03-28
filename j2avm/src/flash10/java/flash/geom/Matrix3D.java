//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.geom;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Matrix3D extends flash.FlashObject {

    public static final native flash.geom.Matrix3D interpolate( flash.geom.Matrix3D thisMat, flash.geom.Matrix3D toMat, double percent );

    public native void transpose( );

    public native void prependTranslation( double x, double y, double z );

    @Setter
    public native void setRawData( double[] setRawData );

    public native flash.geom.Vector3D deltaTransformVector( flash.geom.Vector3D v );

    @Getter
    public native flash.geom.Vector3D getPosition( );

    public native void pointAt( flash.geom.Vector3D pos, flash.geom.Vector3D at, flash.geom.Vector3D up );

    public native void pointAt( flash.geom.Vector3D pos, flash.geom.Vector3D at );

    public native void pointAt( flash.geom.Vector3D pos );

    public native void transformVectors( double[] vin, double[] vout );

    public native void prependRotation( double degrees, flash.geom.Vector3D axis, flash.geom.Vector3D pivotPoint );

    public native void prependRotation( double degrees, flash.geom.Vector3D axis );

    public native void prepend( flash.geom.Matrix3D rhs );

    public native flash.geom.Vector3D transformVector( flash.geom.Vector3D v );

    public native void appendScale( double xScale, double yScale, double zScale );

    public native flash.geom.Vector3D[] decompose( String orientationStyle );

    public native flash.geom.Vector3D[] decompose( );

    @Getter
    public native double[] getRawData( );

    public native void interpolateTo( flash.geom.Matrix3D toMat, double percent );

    @Getter
    public native double getDeterminant( );

    public native boolean invert( );

    public native void appendTranslation( double x, double y, double z );

    public native void appendRotation( double degrees, flash.geom.Vector3D axis, flash.geom.Vector3D pivotPoint );

    public native void appendRotation( double degrees, flash.geom.Vector3D axis );

    @Setter
    public native void setPosition( flash.geom.Vector3D setPosition );

    public native void append( flash.geom.Matrix3D lhs );

    public native void prependScale( double xScale, double yScale, double zScale );

    public native flash.geom.Matrix3D clone( );

    public native void identity( );

    public native boolean recompose( flash.geom.Vector3D[] components, String orientationStyle );

    public native boolean recompose( flash.geom.Vector3D[] components );
}
