//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ShaderEvent extends flash.events.Event {

    public static final String COMPLETE = "complete";

    @Setter
    public native void setByteArray( flash.utils.ByteArray setByteArray );

    @Getter
    public native double[] getVector( );

    @Setter
    public native void setVector( double[] setVector );

    @Override
    public native String toString( );

    @Getter
    public native flash.utils.ByteArray getByteArray( );

    @Setter
    public native void setBitmapData( flash.display.BitmapData setBitmapData );

    @Getter
    public native flash.display.BitmapData getBitmapData( );

    @Override
    public native flash.events.Event clone( );
}
