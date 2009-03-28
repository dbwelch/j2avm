//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ActivityEvent extends flash.events.Event {

    public static final String ACTIVITY = "activity";

    @Getter
    public native boolean getActivating( );

    @Setter
    public native void setActivating( boolean setActivating );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
