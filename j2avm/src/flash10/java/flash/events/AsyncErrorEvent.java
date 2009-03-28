//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class AsyncErrorEvent extends flash.events.ErrorEvent {

    public static final String ASYNC_ERROR = "asyncError";

    @Getter
    public native flash.FlashError getError( );

    @Setter
    public native void setError( flash.FlashError setError );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
