//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class HTTPStatusEvent extends flash.events.Event {

    public  HTTPStatusEvent( String type, boolean bubbles, boolean cancelable, int status ) {}

    public  HTTPStatusEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  HTTPStatusEvent( String type, boolean bubbles ) {}

    public  HTTPStatusEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected HTTPStatusEvent() {}

    public static final String HTTP_STATUS = "httpStatus";

    @Getter
    public native int getStatus( );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
