//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class MovieClip extends flash.display.Sprite {

    public  MovieClip( ) {}

    @Getter
    public native String getCurrentFrameLabel( );

    public native void prevFrame( );

    public native void stop( );

    @Getter
    public native flash.FlashArray getScenes( );

    public native void gotoAndPlay( flash.FlashObject frame, String scene );

    public native void gotoAndPlay( flash.FlashObject frame );

    @Setter
    public native void setEnabled( boolean value );

    @Getter
    public native int getTotalFrames( );

    @Getter
    public native int getFramesLoaded( );

    @Getter
    public native boolean getEnabled( );

    public native void nextScene( );

    @Getter
    public native int getCurrentFrame( );

    @Getter
    public native flash.display.Scene getCurrentScene( );

    public native void gotoAndStop( flash.FlashObject frame, String scene );

    public native void gotoAndStop( flash.FlashObject frame );

    public native void addFrameScript( );

    @Setter
    public native void setTrackAsMenu( boolean value );

    public native void prevScene( );

    public native void nextFrame( );

    public native void play( );

    @Getter
    public native boolean getTrackAsMenu( );

    @Getter
    public native String getCurrentLabel( );

    @Getter
    public native flash.FlashArray getCurrentLabels( );
}
