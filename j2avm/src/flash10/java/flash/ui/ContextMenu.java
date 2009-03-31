//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.ui;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class ContextMenu extends flash.events.EventDispatcher {

    public  ContextMenu( ) {}

    @Setter
    public native void setBuiltInItems( flash.ui.ContextMenuBuiltInItems setBuiltInItems );

    @Getter
    public native flash.ui.ContextMenuBuiltInItems getBuiltInItems( );

    @Getter
    public native flash.ui.ContextMenuClipboardItems getClipboardItems( );

    @Getter
    public native flash.FlashArray getCustomItems( );

    @Setter
    public native void setClipboardMenu( boolean setClipboardMenu );

    @Setter
    public native void setLink( flash.net.URLRequest setLink );

    @Getter
    public native boolean getClipboardMenu( );

    @Getter
    public native flash.net.URLRequest getLink( );

    @Setter
    public native void setClipboardItems( flash.ui.ContextMenuClipboardItems setClipboardItems );

    public native flash.ui.ContextMenu clone( );

    @Setter
    public native void setCustomItems( flash.FlashArray setCustomItems );

    public native void hideBuiltInItems( );
}
