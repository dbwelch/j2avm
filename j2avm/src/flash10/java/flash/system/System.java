//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.system;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class System extends flash.FlashObject {

    public  System( ) {}

    @Getter
    public static final native flash.system.IME getIme( );

    @Getter
    public static final native boolean getUseCodePage( );

    @Getter
    public static final native int getTotalMemory( );

    @Setter
    public static final native void setUseCodePage( boolean setUseCodePage );

    @Getter
    public static final native String getVmVersion( );

    public static final native void resume( );

    public static final native void setClipboard( String string );

    public static final native void pause( );

    public static final native void gc( );

    public static final native void exit( int code );
}
