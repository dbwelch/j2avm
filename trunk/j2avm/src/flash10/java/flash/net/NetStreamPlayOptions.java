//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class NetStreamPlayOptions extends flash.events.EventDispatcher {

    public  NetStreamPlayOptions( ) {}

    @Getter
    public native String getOldStreamName( );

    @Setter
    public native void setOldStreamName( String oldStreamName );

    @Getter
    public native double getLen( );

    @Setter
    public native void setLen( double len );

    @Getter
    public native double getStart( );

    @Setter
    public native void setStart( double start );

    @Getter
    public native String getStreamName( );

    @Setter
    public native void setStreamName( String streamName );

    @Getter
    public native String getTransition( );

    @Setter
    public native void setTransition( String transition );
}
