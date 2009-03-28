//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class NetStream extends flash.events.EventDispatcher {

    public static final String CONNECT_TO_FMS = "connectToFMS";

    public static final String DIRECT_CONNECTIONS = "directConnections";

    @Setter
    public native void setSoundTransform( flash.media.SoundTransform setSoundTransform );

    public native void togglePause( );

    @Setter
    public native void setMaxPauseBufferTime( double setMaxPauseBufferTime );

    @Getter
    public native double getMaxPauseBufferTime( );

    public native void seek( double offset );

    public native void send( String handlerName );

    @Getter
    public native flash.FlashArray getPeerStreams( );

    public native void attachCamera( flash.media.Camera theCamera, int snapshotMilliseconds );

    public native void attachCamera( flash.media.Camera theCamera );

    @Getter
    public native flash.FlashObject getClient( );

    public native void publish( String name, String type );

    public native void publish( String name );

    public native void publish( );

    @Getter
    public native int getBytesLoaded( );

    public native void attachAudio( flash.media.Microphone microphone );

    @Getter
    public native double getTime( );

    @Getter
    public native double getBufferLength( );

    @Setter
    public native void setClient( flash.FlashObject setClient );

    public native void receiveVideo( boolean flag );

    @Getter
    public native int getBytesTotal( );

    @Setter
    public native void setBufferTime( double setBufferTime );

    @Getter
    public native int getVideoCodec( );

    @Getter
    public native flash.media.SoundTransform getSoundTransform( );

    @Getter
    public native String getFarNonce( );

    @Getter
    public native int getAudioCodec( );

    public native boolean onPeerConnect( flash.net.NetStream subscriber );

    @Getter
    public native String getNearNonce( );

    @Setter
    public native void setCheckPolicyFile( boolean setCheckPolicyFile );

    @Getter
    public native double getBufferTime( );

    @Getter
    public native flash.net.NetStreamInfo getInfo( );

    @Getter
    public native double getCurrentFPS( );

    public native void receiveVideoFPS( double FPS );

    @Getter
    public native int getObjectEncoding( );

    public native void receiveAudio( boolean flag );

    public native void resume( );

    public native void pause( );

    @Getter
    public native double getLiveDelay( );

    @Getter
    public native String getFarID( );

    public native void play( );

    @Getter
    public native int getDecodedFrames( );

    @Getter
    public native boolean getCheckPolicyFile( );

    public native void play2( flash.net.NetStreamPlayOptions param );

    public native void close( );
}
