//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ShaderJob extends flash.events.EventDispatcher {

    public  ShaderJob( flash.display.Shader shader, flash.FlashObject target, int width, int height ) {}

    public  ShaderJob( flash.display.Shader shader, flash.FlashObject target, int width ) {}

    public  ShaderJob( flash.display.Shader shader, flash.FlashObject target ) {}

    public  ShaderJob( flash.display.Shader shader ) {}

    public  ShaderJob( ) {}

    public native void start( boolean waitForCompletion );

    public native void start( );

    @Getter
    public native flash.display.Shader getShader( );

    @Getter
    public native int getWidth( );

    @Getter
    public native int getHeight( );

    @Setter
    public native void setTarget( flash.FlashObject setTarget );

    @Setter
    public native void setShader( flash.display.Shader setShader );

    @Setter
    public native void setWidth( int setWidth );

    @Getter
    public native double getProgress( );

    @Setter
    public native void setHeight( int setHeight );

    @Getter
    public native flash.FlashObject getTarget( );

    public native void cancel( );
}
