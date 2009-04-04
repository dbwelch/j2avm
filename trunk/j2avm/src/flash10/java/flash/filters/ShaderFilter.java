//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ShaderFilter extends flash.filters.BitmapFilter {

    public  ShaderFilter( flash.display.Shader shader ) {}

    public  ShaderFilter( ) {}

    @Getter
    public native int getRightExtension( );

    @Getter
    public native flash.display.Shader getShader( );

    @Setter
    public native void setRightExtension( int v );

    @Setter
    public native void setBottomExtension( int v );

    @Setter
    public native void setShader( flash.display.Shader shader );

    @Setter
    public native void setLeftExtension( int v );

    @Getter
    public native int getTopExtension( );

    @Setter
    public native void setTopExtension( int v );

    @Getter
    public native int getLeftExtension( );

    @Getter
    public native int getBottomExtension( );
}
