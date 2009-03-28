//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class StatusEvent extends flash.events.Event {

    public static final String STATUS = "status";

    @Getter
    public native String getCode( );

    @Setter
    public native void setLevel( String setLevel );

    @Setter
    public native void setCode( String setCode );

    @Getter
    public native String getLevel( );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
