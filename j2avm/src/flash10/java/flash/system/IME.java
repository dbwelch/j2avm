//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.system;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class IME extends flash.events.EventDispatcher {

    public  IME( ) {}

    public static final native void setCompositionString( String composition );

    @Getter
    public static final native boolean getEnabled( );

    @Setter
    public static final native void setConversionMode( String mode );

    @Setter
    public static final native void setConstructOK( boolean construct );

    public static final native void doConversion( );

    @Getter
    public static final native String getConversionMode( );

    @Setter
    public static final native void setEnabled( boolean enabled );
}
