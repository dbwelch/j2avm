//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class URLLoader extends flash.events.EventDispatcher {

    public  URLLoader( flash.net.URLRequest request ) {}

    public  URLLoader( ) {}

    @Getter
    public native String getDataFormat( );

    @Setter
    public native void setDataFormat( String setDataFormat );

    public native void load( flash.net.URLRequest request );

    @Getter
    public native Object getData( );

    @Setter
    public native void setData( Object setData );

    @Getter
    public native int getBytesLoaded( );

    @Setter
    public native void setBytesLoaded( int setBytesLoaded );

    public native void close( );

    @Getter
    public native int getBytesTotal( );

    @Setter
    public native void setBytesTotal( int setBytesTotal );
}
