//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.ui;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class ContextMenuItem extends flash.events.EventDispatcher {

    public  ContextMenuItem( String caption, boolean separatorBefore, boolean enabled, boolean visible ) {}

    public  ContextMenuItem( String caption, boolean separatorBefore, boolean enabled ) {}

    public  ContextMenuItem( String caption, boolean separatorBefore ) {}

    public  ContextMenuItem( String caption ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected ContextMenuItem() {}

    @Getter
    public native boolean getEnabled( );

    @Setter
    public native void setEnabled( boolean value );

    @Getter
    public native boolean getSeparatorBefore( );

    @Getter
    public native String getCaption( );

    @Setter
    public native void setSeparatorBefore( boolean value );

    @Getter
    public native boolean getVisible( );

    @Setter
    public native void setVisible( boolean value );

    @Setter
    public native void setCaption( String value );

    public native flash.ui.ContextMenuItem clone( );
}
