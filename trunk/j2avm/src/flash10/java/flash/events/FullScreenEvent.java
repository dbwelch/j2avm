//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class FullScreenEvent extends flash.events.ActivityEvent {

    public static final String FULL_SCREEN = "fullScreen";

    @Getter
    public native boolean getFullScreen( );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
