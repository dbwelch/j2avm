//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class NetFilterEvent extends flash.events.Event {

    @Getter
    public native flash.utils.ByteArray getData( );

    @Setter
    public native void setData( flash.utils.ByteArray setData );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );

    @Getter
    public native flash.utils.ByteArray getHeader( );

    @Setter
    public native void setHeader( flash.utils.ByteArray setHeader );
}
