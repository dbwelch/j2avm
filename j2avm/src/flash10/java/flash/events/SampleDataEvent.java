//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class SampleDataEvent extends flash.events.Event {

    public  SampleDataEvent( String type, boolean bubbles, boolean cancelable, double theposition, flash.utils.ByteArray thedata ) {}

    public  SampleDataEvent( String type, boolean bubbles, boolean cancelable, double theposition ) {}

    public  SampleDataEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  SampleDataEvent( String type, boolean bubbles ) {}

    public  SampleDataEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected SampleDataEvent() {}

    public static final String SAMPLE_DATA = "sampleData";

    @Setter
    public native void setPosition( double theposition );

    @Setter
    public native void setData( flash.utils.ByteArray thedata );

    @Getter
    public native double getPosition( );

    @Getter
    public native flash.utils.ByteArray getData( );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
