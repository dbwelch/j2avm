//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.geom;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Matrix extends flash.FlashObject {

    @Getter
    public native double getA( );

    @Setter
    public native void setA( double setA );

    @Getter
    public native double getC( );

    @Setter
    public native void setC( double setC );

    @Getter
    public native double getTy( );

    @Setter
    public native void setTy( double setTy );

    public native void createBox( double scaleX, double scaleY, double rotation, double tx, double ty );

    public native void createBox( double scaleX, double scaleY, double rotation, double tx );

    public native void createBox( double scaleX, double scaleY, double rotation );

    public native void createBox( double scaleX, double scaleY );

    @Getter
    public native double getB( );

    @Setter
    public native void setB( double setB );

    @Getter
    public native double getD( );

    @Setter
    public native void setD( double setD );

    public native String toString( );

    public native void translate( double dx, double dy );

    public native void scale( double sx, double sy );

    @Getter
    public native double getTx( );

    @Setter
    public native void setTx( double setTx );

    public native void concat( flash.geom.Matrix m );

    public native void invert( );

    public native flash.geom.Point transformPoint( flash.geom.Point point );

    public native void rotate( double angle );

    public native flash.geom.Point deltaTransformPoint( flash.geom.Point point );

    public native flash.geom.Matrix clone( );

    public native void identity( );

    public native void createGradientBox( double width, double height, double rotation, double tx, double ty );

    public native void createGradientBox( double width, double height, double rotation, double tx );

    public native void createGradientBox( double width, double height, double rotation );

    public native void createGradientBox( double width, double height );
}
