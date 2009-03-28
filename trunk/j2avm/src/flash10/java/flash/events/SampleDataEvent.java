//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class SampleDataEvent extends flash.events.Event {

    public static final String SAMPLE_DATA = "sampleData";

    @Setter
    public native void setPosition( double setPosition );

    @Setter
    public native void setData( flash.utils.ByteArray setData );

    @Getter
    public native double getPosition( );

    @Getter
    public native flash.utils.ByteArray getData( );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
