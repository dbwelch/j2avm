//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class InteractiveObject extends flash.display.DisplayObject {

    public  InteractiveObject( ) {}

    @Getter
    public native flash.accessibility.AccessibilityImplementation getAccessibilityImplementation( );

    @Getter
    public native flash.FlashObject getFocusRect( );

    @Setter
    public native void setFocusRect( flash.FlashObject setFocusRect );

    @Getter
    public native boolean getDoubleClickEnabled( );

    @Setter
    public native void setContextMenu( flash.ui.ContextMenu setContextMenu );

    @Getter
    public native boolean getTabEnabled( );

    @Getter
    public native flash.ui.ContextMenu getContextMenu( );

    @Setter
    public native void setAccessibilityImplementation( flash.accessibility.AccessibilityImplementation setAccessibilityImplementation );

    @Setter
    public native void setDoubleClickEnabled( boolean setDoubleClickEnabled );

    @Setter
    public native void setMouseEnabled( boolean setMouseEnabled );

    @Setter
    public native void setTabIndex( int setTabIndex );

    @Getter
    public native boolean getMouseEnabled( );

    @Getter
    public native int getTabIndex( );

    @Setter
    public native void setTabEnabled( boolean setTabEnabled );
}
