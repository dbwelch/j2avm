//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class AsyncErrorEvent extends flash.events.ErrorEvent {

    public  AsyncErrorEvent( String type, boolean bubbles, boolean cancelable, String text, flash.FlashError error ) {}

    public  AsyncErrorEvent( String type, boolean bubbles, boolean cancelable, String text ) {}

    public  AsyncErrorEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  AsyncErrorEvent( String type, boolean bubbles ) {}

    public  AsyncErrorEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected AsyncErrorEvent() {}

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
