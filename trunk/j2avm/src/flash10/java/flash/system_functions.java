//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class system_functions extends flash.FlashObject {
    
    private system_functions() {}

    @Function("flash.system.fscommand")
    public static native void fscommand( String command, String args );

    @Function("flash.system.fscommand")
    public static native void fscommand( String command );
}
