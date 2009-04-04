//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.media;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class SoundLoaderContext extends flash.FlashObject {

    public  SoundLoaderContext( double bufferTime, boolean checkPolicyFile ) {}

    public  SoundLoaderContext( double bufferTime ) {}

    public  SoundLoaderContext( ) {}

    @Getter
    public native double getBufferTime( );

    @Setter
    public native void setBufferTime( double bufferTime );

    @Getter
    public native boolean getCheckPolicyFile( );

    @Setter
    public native void setCheckPolicyFile( boolean checkPolicyFile );
}
