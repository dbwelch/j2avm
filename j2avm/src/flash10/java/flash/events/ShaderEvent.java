//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ShaderEvent extends flash.events.Event {

    public  ShaderEvent( String type, boolean bubbles, boolean cancelable, flash.display.BitmapData bitmap, flash.utils.ByteArray array, double[] vector ) {}

    public  ShaderEvent( String type, boolean bubbles, boolean cancelable, flash.display.BitmapData bitmap, flash.utils.ByteArray array ) {}

    public  ShaderEvent( String type, boolean bubbles, boolean cancelable, flash.display.BitmapData bitmap ) {}

    public  ShaderEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  ShaderEvent( String type, boolean bubbles ) {}

    public  ShaderEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected ShaderEvent() {}

    public static final String COMPLETE = "complete";

    @Setter
    public native void setByteArray( flash.utils.ByteArray bArray );

    @Getter
    public native double[] getVector( );

    @Setter
    public native void setVector( double[] v );

    @Override
    public native String toString( );

    @Getter
    public native flash.utils.ByteArray getByteArray( );

    @Setter
    public native void setBitmapData( flash.display.BitmapData bmpData );

    @Getter
    public native flash.display.BitmapData getBitmapData( );

    @Override
    public native flash.events.Event clone( );
}
