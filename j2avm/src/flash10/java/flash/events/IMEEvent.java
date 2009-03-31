//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class IMEEvent extends flash.events.TextEvent {

    public  IMEEvent( String type, boolean bubbles, boolean cancelable, String text ) {}

    public  IMEEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  IMEEvent( String type, boolean bubbles ) {}

    public  IMEEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected IMEEvent() {}

    public static final String IME_COMPOSITION = "imeComposition";

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
