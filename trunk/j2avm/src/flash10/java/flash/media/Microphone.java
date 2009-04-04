//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.media;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class Microphone extends flash.events.EventDispatcher {

    public  Microphone( ) {}

    public static final native flash.media.Microphone getMicrophone( int index );

    public static final native flash.media.Microphone getMicrophone( );

    @Getter
    public static final native flash.FlashArray getNames( );

    @Setter
    public native void setRate( int rate );

    @Setter
    public native void setSoundTransform( flash.media.SoundTransform sndTransform );

    @Getter
    public native double getSilenceLevel( );

    public native void setSilenceLevel( double silenceLevel, int timeout );

    public native void setSilenceLevel( double silenceLevel );

    @Getter
    public native double getGain( );

    @Getter
    public native int getRate( );

    public native void setUseEchoSuppression( boolean useEchoSuppression );

    @Getter
    public native boolean getMuted( );

    @Setter
    public native void setCodec( String codec );

    @Setter
    public native void setGain( double gain );

    @Getter
    public native boolean getUseEchoSuppression( );

    @Getter
    public native int getSilenceTimeout( );

    @Getter
    public native int getEncodeQuality( );

    @Setter
    public native void setEncodeQuality( int quality );

    public native void setLoopBack( boolean state );

    public native void setLoopBack( );

    @Getter
    public native double getActivityLevel( );

    @Getter
    public native String getCodec( );

    @Getter
    public native int getIndex( );

    @Getter
    public native String getName( );

    @Getter
    public native flash.media.SoundTransform getSoundTransform( );

    @Setter
    public native void setFramesPerPacket( int frames );

    @Getter
    public native int getFramesPerPacket( );
}
