//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class FocusEvent extends flash.events.Event {

    public static final String MOUSE_FOCUS_CHANGE = "mouseFocusChange";

    public static final String FOCUS_OUT = "focusOut";

    public static final String KEY_FOCUS_CHANGE = "keyFocusChange";

    public static final String FOCUS_IN = "focusIn";

    @Setter
    public native void setShiftKey( boolean setShiftKey );

    @Getter
    public native boolean getIsRelatedObjectInaccessible( );

    @Getter
    public native boolean getShiftKey( );

    @Getter
    public native flash.display.InteractiveObject getRelatedObject( );

    @Override
    public native String toString( );

    @Getter
    public native int getKeyCode( );

    @Setter
    public native void setIsRelatedObjectInaccessible( boolean setIsRelatedObjectInaccessible );

    @Setter
    public native void setRelatedObject( flash.display.InteractiveObject setRelatedObject );

    @Override
    public native flash.events.Event clone( );

    @Setter
    public native void setKeyCode( int setKeyCode );
}
