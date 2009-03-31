//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class IOErrorEvent extends flash.events.ErrorEvent {

    public  IOErrorEvent( String type, boolean bubbles, boolean cancelable, String text ) {}

    public  IOErrorEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  IOErrorEvent( String type, boolean bubbles ) {}

    public  IOErrorEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected IOErrorEvent() {}

    public static final String DISK_ERROR = "diskError";

    public static final String NETWORK_ERROR = "networkError";

    public static final String VERIFY_ERROR = "verifyError";

    public static final String IO_ERROR = "ioError";

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
