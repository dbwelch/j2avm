//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class net_functions extends flash.FlashObject {
    
    private net_functions() {}

    @Function("flash.net.navigateToURL")
    public static native void navigateToURL( flash.net.URLRequest request, String window );

    @Function("flash.net.navigateToURL")
    public static native void navigateToURL( flash.net.URLRequest request );

    @Function("flash.net.getClassByAlias")
    public static native flash.FlashClass getClassByAlias( String aliasName );

    @Function("flash.net.registerClassAlias")
    public static native void registerClassAlias( String aliasName, flash.FlashClass classObject );

    @Function("flash.net.sendToURL")
    public static native void sendToURL( flash.net.URLRequest request );
}
