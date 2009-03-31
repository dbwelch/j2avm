//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class SyncEvent extends flash.events.Event {

    public  SyncEvent( String type, boolean bubbles, boolean cancelable, flash.FlashArray changeList ) {}

    public  SyncEvent( String type, boolean bubbles, boolean cancelable ) {}

    public  SyncEvent( String type, boolean bubbles ) {}

    public  SyncEvent( String type ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected SyncEvent() {}

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
