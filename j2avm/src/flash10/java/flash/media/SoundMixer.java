//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.media;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class SoundMixer extends flash.FlashObject {

    @Setter
    public static final native void setSoundTransform( flash.media.SoundTransform setSoundTransform );

    public static final native boolean areSoundsInaccessible( );

    @Getter
    public static final native int getBufferTime( );

    public static final native void computeSpectrum( flash.utils.ByteArray outputArray, boolean FFTMode, int stretchFactor );

    public static final native void computeSpectrum( flash.utils.ByteArray outputArray, boolean FFTMode );

    public static final native void computeSpectrum( flash.utils.ByteArray outputArray );

    @Setter
    public static final native void setBufferTime( int setBufferTime );

    @Getter
    public static final native flash.media.SoundTransform getSoundTransform( );

    public static final native void stopAll( );
}
