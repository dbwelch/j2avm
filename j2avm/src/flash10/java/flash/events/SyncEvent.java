//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class SyncEvent extends flash.events.Event {

    public static final String SYNC = "sync";

    @Setter
    public native void setChangeList( flash.FlashArray setChangeList );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );

    @Getter
    public native flash.FlashArray getChangeList( );
}
