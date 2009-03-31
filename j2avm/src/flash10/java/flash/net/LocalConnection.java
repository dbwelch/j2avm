//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class LocalConnection extends flash.events.EventDispatcher {

    public  LocalConnection( ) {}

    @Getter
    public native String getDomain( );

    @Setter
    public native void setClient( flash.FlashObject setClient );

    public native void close( );

    public native void allowInsecureDomain( );

    public native void connect( String connectionName );

    @Getter
    public native flash.FlashObject getClient( );

    public native void allowDomain( );

    public native void send( String connectionName, String methodName );
}
