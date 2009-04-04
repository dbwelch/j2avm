//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ProgressEvent extends flash.events.Event {

    public  ProgressEvent( String type, boolean bubbles, boolean cancelable, int bytesLoaded, int bytesTotal ) {}

    public  ProgressEvent( String type, boolean bubbles, boolean cancelable, int bytesLoaded ) {}

    public  ProgressEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  ProgressEvent( String type, boolean bubbles ) {}

    public  ProgressEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected ProgressEvent() {}

    public static final String PROGRESS = "progress";

    public static final String SOCKET_DATA = "socketData";

    @Getter
    public native int getBytesLoaded( );

    @Setter
    public native void setBytesTotal( int value );

    @Override
    public native String toString( );

    @Setter
    public native void setBytesLoaded( int value );

    @Getter
    public native int getBytesTotal( );

    @Override
    public native flash.events.Event clone( );
}
