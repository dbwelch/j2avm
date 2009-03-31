//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.geom;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class PerspectiveProjection extends flash.FlashObject {

    public  PerspectiveProjection( ) {}

    @Getter
    public native flash.geom.Point getProjectionCenter( );

    public native flash.geom.Matrix3D toMatrix3D( );

    @Getter
    public native double getFieldOfView( );

    @Setter
    public native void setProjectionCenter( flash.geom.Point setProjectionCenter );

    @Getter
    public native double getFocalLength( );

    @Setter
    public native void setFieldOfView( double setFieldOfView );

    @Setter
    public native void setFocalLength( double setFocalLength );
}
