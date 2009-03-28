//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.media;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class SoundChannel extends flash.events.EventDispatcher {

    public native void stop( );

    @Getter
    public native double getLeftPeak( );

    @Getter
    public native double getPosition( );

    @Setter
    public native void setSoundTransform( flash.media.SoundTransform setSoundTransform );

    @Getter
    public native double getRightPeak( );

    @Getter
    public native flash.media.SoundTransform getSoundTransform( );
}
