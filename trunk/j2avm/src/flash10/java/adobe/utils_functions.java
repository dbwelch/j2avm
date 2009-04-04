//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package adobe;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class utils_functions extends flash.FlashObject {
    
    private utils_functions() {}

    @Function("adobe.utils.MMEndCommand")
    public static native void MMEndCommand( boolean endStatus, String notifyString );

    @Function("adobe.utils.MMExecute")
    public static native String MMExecute( String name );
}
