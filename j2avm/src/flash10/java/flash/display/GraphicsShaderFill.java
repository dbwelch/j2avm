//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GraphicsShaderFill extends flash.FlashObject implements flash.display.IGraphicsFill,flash.display.IGraphicsData {

    public  GraphicsShaderFill( flash.display.Shader shader, flash.geom.Matrix matrix ) {}

    public  GraphicsShaderFill( flash.display.Shader shader ) {}

    public  GraphicsShaderFill( ) {}

    @Getter
    public native flash.geom.Matrix getMatrix( );

    @Setter
    public native void setMatrix( flash.geom.Matrix matrix );

    @Getter
    public native flash.display.Shader getShader( );

    @Setter
    public native void setShader( flash.display.Shader shader );
}
