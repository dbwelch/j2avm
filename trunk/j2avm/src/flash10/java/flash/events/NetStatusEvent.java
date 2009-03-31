//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class NetStatusEvent extends flash.events.Event {

    public  NetStatusEvent( String type, boolean bubbles, boolean cancelable, flash.FlashObject info ) {}

    public  NetStatusEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  NetStatusEvent( String type, boolean bubbles ) {}

    public  NetStatusEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected NetStatusEvent() {}

    public static final String NET_STATUS = "netStatus";

    @Setter
    public native void setInfo( flash.FlashObject setInfo );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );

    @Getter
    public native flash.FlashObject getInfo( );
}
