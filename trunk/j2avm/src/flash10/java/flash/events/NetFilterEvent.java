//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class NetFilterEvent extends flash.events.Event {

    public  NetFilterEvent( String type, boolean bubbles, boolean cancelable, flash.utils.ByteArray header, flash.utils.ByteArray data ) {}

    public  NetFilterEvent( String type, boolean bubbles, boolean cancelable, flash.utils.ByteArray header ) {}

    public  NetFilterEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  NetFilterEvent( String type, boolean bubbles ) {}

    public  NetFilterEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected NetFilterEvent() {}

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
