//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.utils;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Timer extends flash.events.EventDispatcher {

    @Getter
    public native double getDelay( );

    @Setter
    public native void setDelay( double setDelay );

    @Setter
    public native void setRepeatCount( int setRepeatCount );

    public native void reset( );

    @Getter
    public native int getRepeatCount( );

    public native void start( );

    public native void stop( );

    @Getter
    public native int getCurrentCount( );

    @Getter
    public native boolean getRunning( );
}
