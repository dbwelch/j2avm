//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Sprite extends flash.display.DisplayObjectContainer {

    public  Sprite( ) {}

    @Getter
    public native flash.display.DisplayObject getDropTarget( );

    @Getter
    public native flash.media.SoundTransform getSoundTransform( );

    @Getter
    public native flash.display.Sprite getHitArea( );

    @Setter
    public native void setButtonMode( boolean value );

    @Getter
    public native flash.display.Graphics getGraphics( );

    @Getter
    public native boolean getUseHandCursor( );

    @Setter
    public native void setHitArea( flash.display.Sprite value );

    @Getter
    public native boolean getButtonMode( );

    public native void stopDrag( );

    @Setter
    public native void setUseHandCursor( boolean value );

    @Setter
    public native void setSoundTransform( flash.media.SoundTransform sndTransform );

    public native void startDrag( boolean lockCenter, flash.geom.Rectangle bounds );

    public native void startDrag( boolean lockCenter );

    public native void startDrag( );
}
