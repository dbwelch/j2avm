//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.media;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class Camera extends flash.events.EventDispatcher {

    public  Camera( ) {}

    @Getter
    public static final native flash.FlashArray getNames( );

    public static final native flash.media.Camera getCamera( String name );

    public static final native flash.media.Camera getCamera( );

    @Getter
    public native boolean getLoopback( );

    public native void setMode( int width, int height, double fps, boolean favorArea );

    public native void setMode( int width, int height, double fps );

    @Getter
    public native int getWidth( );

    @Getter
    public native int getHeight( );

    @Getter
    public native double getFps( );

    @Getter
    public native String getName( );

    public native void setMotionLevel( int motionLevel, int timeout );

    public native void setMotionLevel( int motionLevel );

    @Getter
    public native boolean getMuted( );

    @Getter
    public native int getMotionLevel( );

    @Getter
    public native double getCurrentFPS( );

    @Getter
    public native int getBandwidth( );

    @Getter
    public native int getIndex( );

    @Getter
    public native int getKeyFrameInterval( );

    public native void setLoopback( boolean compress );

    public native void setLoopback( );

    @Getter
    public native double getActivityLevel( );

    public native void setCursor( boolean value );

    @Getter
    public native int getMotionTimeout( );

    public native void setKeyFrameInterval( int keyFrameInterval );

    public native void setQuality( int bandwidth, int quality );

    @Getter
    public native int getQuality( );
}
