//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.geom;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Transform extends flash.FlashObject {

    public  Transform( flash.display.DisplayObject displayObject ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected Transform() {}

    @Getter
    public native flash.geom.Matrix getMatrix( );

    @Setter
    public native void setMatrix( flash.geom.Matrix value );

    @Setter
    public native void setMatrix3D( flash.geom.Matrix3D m );

    @Getter
    public native flash.geom.ColorTransform getColorTransform( );

    @Getter
    public native flash.geom.PerspectiveProjection getPerspectiveProjection( );

    @Getter
    public native flash.geom.Matrix getConcatenatedMatrix( );

    @Getter
    public native flash.geom.Matrix3D getMatrix3D( );

    public native flash.geom.Matrix3D getRelativeMatrix3D( flash.display.DisplayObject relativeTo );

    @Setter
    public native void setPerspectiveProjection( flash.geom.PerspectiveProjection pm );

    @Getter
    public native flash.geom.ColorTransform getConcatenatedColorTransform( );

    @Setter
    public native void setColorTransform( flash.geom.ColorTransform value );

    @Getter
    public native flash.geom.Rectangle getPixelBounds( );
}
