//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class NetStreamInfo extends flash.FlashObject {

    public  NetStreamInfo( double curBPS, double byteCount, double maxBPS, double audioBPS, double audioByteCount, double videoBPS, double videoByteCount, double dataBPS, double dataByteCount, double playbackBPS, double droppedFrames, double audioBufferByteLength, double videoBufferByteLength, double dataBufferByteLength, double audioBufferLength, double videoBufferLength, double dataBufferLength, double srtt, double audioLossRate ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected NetStreamInfo() {}

    @Getter
    public native double getVideoBufferByteLength( );

    @Getter
    public native double getDroppedFrames( );

    @Getter
    public native double getDataBytesPerSecond( );

    @Getter
    public native double getDataBufferLength( );

    @Getter
    public native double getAudioLossRate( );

    @Getter
    public native double getCurrentBytesPerSecond( );

    @Getter
    public native double getVideoBytesPerSecond( );

    @Getter
    public native double getVideoByteCount( );

    @Getter
    public native double getSRTT( );

    @Getter
    public native double getAudioByteCount( );

    @Getter
    public native double getAudioBytesPerSecond( );

    @Getter
    public native double getDataBufferByteLength( );

    @Getter
    public native double getPlaybackBytesPerSecond( );

    @Getter
    public native double getMaxBytesPerSecond( );

    @Getter
    public native double getDataByteCount( );

    @Getter
    public native double getAudioBufferLength( );

    @Getter
    public native double getVideoBufferLength( );

    @Getter
    public native double getAudioBufferByteLength( );

    @Getter
    public native double getByteCount( );

    public native String toString( );
}
