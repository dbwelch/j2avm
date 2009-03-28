//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.events;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Event extends flash.FlashObject {

    public static final String CANCEL = "cancel";

    public static final String ENTER_FRAME = "enterFrame";

    public static final String SOUND_COMPLETE = "soundComplete";

    public static final String UNLOAD = "unload";

    public static final String INIT = "init";

    public static final String RENDER = "render";

    public static final String TAB_ENABLED_CHANGE = "tabEnabledChange";

    public static final String ADDED_TO_STAGE = "addedToStage";

    public static final String FRAME_CONSTRUCTED = "frameConstructed";

    public static final String TAB_CHILDREN_CHANGE = "tabChildrenChange";

    public static final String CUT = "cut";

    public static final String CLEAR = "clear";

    public static final String CHANGE = "change";

    public static final String RESIZE = "resize";

    public static final String COMPLETE = "complete";

    public static final String FULLSCREEN = "fullScreen";

    public static final String SELECT_ALL = "selectAll";

    public static final String REMOVED = "removed";

    public static final String CONNECT = "connect";

    public static final String SCROLL = "scroll";

    public static final String OPEN = "open";

    public static final String CLOSE = "close";

    public static final String MOUSE_LEAVE = "mouseLeave";

    public static final String ADDED = "added";

    public static final String REMOVED_FROM_STAGE = "removedFromStage";

    public static final String EXIT_FRAME = "exitFrame";

    public static final String TAB_INDEX_CHANGE = "tabIndexChange";

    public static final String PASTE = "paste";

    public static final String DEACTIVATE = "deactivate";

    public static final String COPY = "copy";

    public static final String ID3 = "id3";

    public static final String ACTIVATE = "activate";

    public static final String SELECT = "select";

    public native boolean isDefaultPrevented( );

    @Getter
    public native int getEventPhase( );

    public native String formatToString( String className );

    public native flash.events.Event clone( );

    @Getter
    public native boolean getBubbles( );

    public native void preventDefault( );

    public native void stopPropagation( );

    public native String toString( );

    @Getter
    public native flash.FlashObject getTarget( );

    @Getter
    public native boolean getCancelable( );

    @Getter
    public native flash.FlashObject getCurrentTarget( );

    @Getter
    public native String getType( );

    public native void stopImmediatePropagation( );
}
