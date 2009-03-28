//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class HTTPStatusEvent extends flash.events.Event {

    public static final String HTTP_STATUS = "httpStatus";

    @Getter
    public native int getStatus( );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
