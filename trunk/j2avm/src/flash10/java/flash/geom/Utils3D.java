//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.geom;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Utils3D extends flash.FlashObject {

    public  Utils3D( ) {}

    public static final native flash.geom.Matrix3D pointTowards( double percent, flash.geom.Matrix3D mat, flash.geom.Vector3D pos, flash.geom.Vector3D at, flash.geom.Vector3D up );

    public static final native flash.geom.Matrix3D pointTowards( double percent, flash.geom.Matrix3D mat, flash.geom.Vector3D pos, flash.geom.Vector3D at );

    public static final native flash.geom.Matrix3D pointTowards( double percent, flash.geom.Matrix3D mat, flash.geom.Vector3D pos );

    public static final native flash.geom.Vector3D projectVector( flash.geom.Matrix3D m, flash.geom.Vector3D v );

    public static final native void projectVectors( flash.geom.Matrix3D m, double[] verts, double[] projectedVerts, double[] uvts );
}
