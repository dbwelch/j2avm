//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class TimerEvent extends flash.events.Event {

    public  TimerEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  TimerEvent( String type, boolean bubbles ) {}

    public  TimerEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected TimerEvent() {}

    public static final String TIMER_COMPLETE = "timerComplete";

    public static final String TIMER = "timer";

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );

    public native void updateAfterEvent( );
}
