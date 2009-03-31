//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class SecurityErrorEvent extends flash.events.ErrorEvent {

    public  SecurityErrorEvent( String type, boolean bubbles, boolean cancelable, String text ) {}

    public  SecurityErrorEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  SecurityErrorEvent( String type, boolean bubbles ) {}

    public  SecurityErrorEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected SecurityErrorEvent() {}

    public static final String SECURITY_ERROR = "securityError";

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
