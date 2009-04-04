//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class StatusEvent extends flash.events.Event {

    public  StatusEvent( String type, boolean bubbles, boolean cancelable, String code, String level ) {}

    public  StatusEvent( String type, boolean bubbles, boolean cancelable, String code ) {}

    public  StatusEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  StatusEvent( String type, boolean bubbles ) {}

    public  StatusEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected StatusEvent() {}

    public static final String STATUS = "status";

    @Getter
    public native String getCode( );

    @Setter
    public native void setLevel( String value );

    @Setter
    public native void setCode( String value );

    @Getter
    public native String getLevel( );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
