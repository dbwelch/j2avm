//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package adobe.utils;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class ProductManager extends flash.events.EventDispatcher {

    public native boolean launch( String parameters );

    public native boolean launch( );

    public native boolean download( String caption, String fileName, flash.FlashArray pathElements );

    public native boolean download( String caption, String fileName );

    public native boolean download( String caption );

    public native boolean download( );

    @Getter
    public native boolean getInstalled( );

    @Getter
    public native String getInstalledVersion( );

    @Getter
    public native boolean getRunning( );
}
