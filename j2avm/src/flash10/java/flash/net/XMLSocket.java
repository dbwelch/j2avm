//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class XMLSocket extends flash.events.EventDispatcher {

    public  XMLSocket( String host, int port ) {}

    public  XMLSocket( String host ) {}

    public  XMLSocket( ) {}

    @Getter
    public native int getTimeout( );

    @Setter
    public native void setTimeout( int value );

    public native void send( Object object );

    @Getter
    public native boolean getConnected( );

    public native void connect( String host, int port );

    public native void close( );
}
