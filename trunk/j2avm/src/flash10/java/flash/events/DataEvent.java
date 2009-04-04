//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class DataEvent extends flash.events.TextEvent {

    public  DataEvent( String type, boolean bubbles, boolean cancelable, String data ) {}

    public  DataEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  DataEvent( String type, boolean bubbles ) {}

    public  DataEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected DataEvent() {}

    public static final String DATA = "data";

    public static final String UPLOAD_COMPLETE_DATA = "uploadCompleteData";

    @Getter
    public native String getData( );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );

    @Setter
    public native void setData( String value );
}
