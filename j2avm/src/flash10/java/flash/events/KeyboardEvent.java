//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class KeyboardEvent extends flash.events.Event {

    public static final String KEY_DOWN = "keyDown";

    public static final String KEY_UP = "keyUp";

    @Setter
    public native void setShiftKey( boolean setShiftKey );

    @Getter
    public native int getKeyLocation( );

    @Setter
    public native void setCharCode( int setCharCode );

    public native void updateAfterEvent( );

    @Getter
    public native int getKeyCode( );

    @Setter
    public native void setKeyCode( int setKeyCode );

    @Override
    public native flash.events.Event clone( );

    @Getter
    public native int getCharCode( );

    @Setter
    public native void setCtrlKey( boolean setCtrlKey );

    @Override
    public native String toString( );

    @Setter
    public native void setKeyLocation( int setKeyLocation );

    @Setter
    public native void setAltKey( boolean setAltKey );

    @Getter
    public native boolean getCtrlKey( );

    @Getter
    public native boolean getAltKey( );

    @Getter
    public native boolean getShiftKey( );
}
