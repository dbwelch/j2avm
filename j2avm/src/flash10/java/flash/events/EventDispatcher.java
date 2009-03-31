//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class EventDispatcher extends flash.FlashObject implements flash.events.IEventDispatcher {

    public  EventDispatcher( flash.events.IEventDispatcher target ) {}

    public  EventDispatcher( ) {}

    public native boolean dispatchEvent( flash.events.Event event );

    public native boolean willTrigger( String type );

    public native void removeEventListener( String type, flash.FlashFunction listener, boolean useCapture );

    public native void removeEventListener( String type, flash.FlashFunction listener );

    public native String toString( );

    public native boolean hasEventListener( String type );

    public native void addEventListener( String type, flash.FlashFunction listener, boolean useCapture, int priority, boolean useWeakReference );

    public native void addEventListener( String type, flash.FlashFunction listener, boolean useCapture, int priority );

    public native void addEventListener( String type, flash.FlashFunction listener, boolean useCapture );

    public native void addEventListener( String type, flash.FlashFunction listener );
}
