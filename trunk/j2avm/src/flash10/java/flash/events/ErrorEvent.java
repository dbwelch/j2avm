//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ErrorEvent extends flash.events.TextEvent {

    public  ErrorEvent( String type, boolean bubbles, boolean cancelable, String text ) {}

    public  ErrorEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  ErrorEvent( String type, boolean bubbles ) {}

    public  ErrorEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected ErrorEvent() {}

    public static final String ERROR = "error";

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
