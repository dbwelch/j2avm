//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.media;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Sound extends flash.events.EventDispatcher {

    public  Sound( flash.net.URLRequest stream, flash.media.SoundLoaderContext context ) {}

    public  Sound( flash.net.URLRequest stream ) {}

    public  Sound( ) {}

    public native double extract( flash.utils.ByteArray target, double length, double startPosition );

    public native double extract( flash.utils.ByteArray target, double length );

    public native void load( flash.net.URLRequest stream, flash.media.SoundLoaderContext context );

    public native void load( flash.net.URLRequest stream );

    public native void close( );

    @Getter
    public native String getUrl( );

    @Getter
    public native int getBytesLoaded( );

    public native flash.media.SoundChannel play( double startTime, int loops, flash.media.SoundTransform sndTransform );

    public native flash.media.SoundChannel play( double startTime, int loops );

    public native flash.media.SoundChannel play( double startTime );

    public native flash.media.SoundChannel play( );

    @Getter
    public native double getLength( );

    @Getter
    public native flash.media.ID3Info getId3( );

    @Getter
    public native int getBytesTotal( );

    @Getter
    public native boolean getIsBuffering( );
}
