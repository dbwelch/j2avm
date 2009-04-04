//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.ui;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class ContextMenuClipboardItems extends flash.FlashObject {

    public  ContextMenuClipboardItems( ) {}

    @Getter
    public native boolean getCut( );

    @Setter
    public native void setCut( boolean cut );

    @Getter
    public native boolean getPaste( );

    @Setter
    public native void setPaste( boolean paste );

    @Getter
    public native boolean getCopy( );

    @Setter
    public native void setCopy( boolean copy );

    @Getter
    public native boolean getSelectAll( );

    @Setter
    public native void setSelectAll( boolean selectAll );

    @Getter
    public native boolean getClear( );

    @Setter
    public native void setClear( boolean clear );

    public native flash.ui.ContextMenuClipboardItems clone( );
}
