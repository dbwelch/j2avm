//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ContextMenuEvent extends flash.events.Event {

    public static final String MENU_ITEM_SELECT = "menuItemSelect";

    public static final String MENU_SELECT = "menuSelect";

    @Getter
    public native flash.display.InteractiveObject getContextMenuOwner( );

    @Setter
    public native void setIsMouseTargetInaccessible( boolean setIsMouseTargetInaccessible );

    @Getter
    public native boolean getIsMouseTargetInaccessible( );

    @Setter
    public native void setMouseTarget( flash.display.InteractiveObject setMouseTarget );

    @Setter
    public native void setContextMenuOwner( flash.display.InteractiveObject setContextMenuOwner );

    @Getter
    public native flash.display.InteractiveObject getMouseTarget( );

    @Override
    public native String toString( );

    @Override
    public native flash.events.Event clone( );
}
