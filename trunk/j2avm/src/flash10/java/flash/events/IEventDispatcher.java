//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public interface IEventDispatcher {

    public boolean dispatchEvent( flash.events.Event event );

    public boolean hasEventListener( String type );

    public boolean willTrigger( String type );

    public void removeEventListener( String type, flash.FlashFunction listener, boolean useCapture );

    public void removeEventListener( String type, flash.FlashFunction listener );

    public void addEventListener( String type, flash.FlashFunction listener, boolean useCapture, int priority, boolean useWeakReference );

    public void addEventListener( String type, flash.FlashFunction listener, boolean useCapture, int priority );

    public void addEventListener( String type, flash.FlashFunction listener, boolean useCapture );

    public void addEventListener( String type, flash.FlashFunction listener );
}
