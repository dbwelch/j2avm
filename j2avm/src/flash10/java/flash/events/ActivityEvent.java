//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ActivityEvent extends flash.events.Event {

    public  ActivityEvent( String type, boolean bubbles, boolean cancelable, boolean activating ) {}

    public  ActivityEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  ActivityEvent( String type, boolean bubbles ) {}

    public  ActivityEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected ActivityEvent() {}

    public static final String ACTIVITY = "activity";

    @Getter
    public native boolean getActivating( );

    @Setter
    public native void setActivating( boolean value );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
