//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ProgressEvent extends flash.events.Event {

    public static final String PROGRESS = "progress";

    public static final String SOCKET_DATA = "socketData";

    @Getter
    public native int getBytesLoaded( );

    @Setter
    public native void setBytesTotal( int setBytesTotal );

    @Override
    public native String toString( );

    @Setter
    public native void setBytesLoaded( int setBytesLoaded );

    @Getter
    public native int getBytesTotal( );

    @Override
    public native flash.events.Event clone( );
}
