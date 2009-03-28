//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class FlashFunction extends flash.FlashObject {

    public static final int length = 1;

    @Getter
    public native Object getPrototype( );

    @Setter
    public native void setPrototype( Object setPrototype );

    public native Object call( Object thisArg );

    @Getter
    public native int getLength( );

    public native Object apply( Object thisArg, Object argArray );
}
