//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class TextEvent extends flash.events.Event {

    public  TextEvent( String type, boolean bubbles, boolean cancelable, String text ) {}

    public  TextEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  TextEvent( String type, boolean bubbles ) {}

    public  TextEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected TextEvent() {}

    public static final String TEXT_INPUT = "textInput";

    public static final String LINK = "link";

    @Getter
    public native String getText( );

    @Setter
    public native void setText( String value );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
