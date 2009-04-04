//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class MouseEvent extends flash.events.Event {

    public  MouseEvent( String type, boolean bubbles, boolean cancelable, double localX, double localY, flash.display.InteractiveObject relatedObject, boolean ctrlKey, boolean altKey, boolean shiftKey, boolean buttonDown, int delta ) {}

    public  MouseEvent( String type, boolean bubbles, boolean cancelable, double localX, double localY, flash.display.InteractiveObject relatedObject, boolean ctrlKey, boolean altKey, boolean shiftKey, boolean buttonDown ) {}

    public  MouseEvent( String type, boolean bubbles, boolean cancelable, double localX, double localY, flash.display.InteractiveObject relatedObject, boolean ctrlKey, boolean altKey, boolean shiftKey ) {}

    public  MouseEvent( String type, boolean bubbles, boolean cancelable, double localX, double localY, flash.display.InteractiveObject relatedObject, boolean ctrlKey, boolean altKey ) {}

    public  MouseEvent( String type, boolean bubbles, boolean cancelable, double localX, double localY, flash.display.InteractiveObject relatedObject, boolean ctrlKey ) {}

    public  MouseEvent( String type, boolean bubbles, boolean cancelable, double localX, double localY, flash.display.InteractiveObject relatedObject ) {}

    public  MouseEvent( String type, boolean bubbles, boolean cancelable, double localX, double localY ) {}

    public  MouseEvent( String type, boolean bubbles, boolean cancelable, double localX ) {}

    public  MouseEvent( String type, boolean bubbles, boolean cancelable ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected MouseEvent() {}

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
    public native void setIsRelatedObjectInaccessible( boolean value );

    public native void updateAfterEvent( );

    @Getter
    public native flash.display.InteractiveObject getRelatedObject( );

    @Getter
    public native double getLocalX( );

    @Getter
    public native double getLocalY( );

    @Setter
    public native void setRelatedObject( flash.display.InteractiveObject value );

    @Setter
    public native void setLocalX( double value );

    @Getter
    public native double getStageY( );

    @Setter
    public native void setLocalY( double value );

    @Override
    public native flash.events.Event clone( );

    @Getter
    public native double getStageX( );

    @Setter
    public native void setCtrlKey( boolean value );

    @Override
    public native String toString( );

    @Setter
    public native void setButtonDown( boolean value );

    @Getter
    public native boolean getCtrlKey( );

    @Getter
    public native boolean getAltKey( );

    @Setter
    public native void setDelta( int value );

    @Setter
    public native void setShiftKey( boolean value );

    @Setter
    public native void setAltKey( boolean value );

    @Getter
    public native boolean getShiftKey( );

    @Getter
    public native int getDelta( );
}
