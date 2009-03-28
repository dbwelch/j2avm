//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class MouseEvent extends flash.events.Event {

    public static final String MOUSE_WHEEL = "mouseWheel";

    public static final String MOUSE_MOVE = "mouseMove";

    public static final String ROLL_OUT = "rollOut";

    public static final String MOUSE_OVER = "mouseOver";

    public static final String CLICK = "click";

    public static final String MOUSE_OUT = "mouseOut";

    public static final String MOUSE_UP = "mouseUp";

    public static final String DOUBLE_CLICK = "doubleClick";

    public static final String MOUSE_DOWN = "mouseDown";

    public static final String ROLL_OVER = "rollOver";

    @Getter
    public native boolean getIsRelatedObjectInaccessible( );

    @Getter
    public native boolean getButtonDown( );

    @Setter
    public native void setIsRelatedObjectInaccessible( boolean setIsRelatedObjectInaccessible );

    public native void updateAfterEvent( );

    @Getter
    public native flash.display.InteractiveObject getRelatedObject( );

    @Getter
    public native double getLocalX( );

    @Getter
    public native double getLocalY( );

    @Setter
    public native void setRelatedObject( flash.display.InteractiveObject setRelatedObject );

    @Setter
    public native void setLocalX( double setLocalX );

    @Getter
    public native double getStageY( );

    @Setter
    public native void setLocalY( double setLocalY );

    @Override
    public native flash.events.Event clone( );

    @Getter
    public native double getStageX( );

    @Setter
    public native void setCtrlKey( boolean setCtrlKey );

    @Override
    public native String toString( );

    @Setter
    public native void setButtonDown( boolean setButtonDown );

    @Getter
    public native boolean getCtrlKey( );

    @Getter
    public native boolean getAltKey( );

    @Setter
    public native void setDelta( int setDelta );

    @Setter
    public native void setShiftKey( boolean setShiftKey );

    @Setter
    public native void setAltKey( boolean setAltKey );

    @Getter
    public native boolean getShiftKey( );

    @Getter
    public native int getDelta( );
}
