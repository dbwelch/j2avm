//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.ui;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class ContextMenuItem extends flash.events.EventDispatcher {

    @Getter
    public native boolean getEnabled( );

    @Setter
    public native void setEnabled( boolean setEnabled );

    @Getter
    public native boolean getSeparatorBefore( );

    @Getter
    public native String getCaption( );

    @Setter
    public native void setSeparatorBefore( boolean setSeparatorBefore );

    @Getter
    public native boolean getVisible( );

    @Setter
    public native void setVisible( boolean setVisible );

    @Setter
    public native void setCaption( String setCaption );

    public native flash.ui.ContextMenuItem clone( );
}
