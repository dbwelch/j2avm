//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package adobe.utils;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class CustomActions extends flash.FlashObject {

    public  CustomActions( ) {}

    public static final native void installActions( String name, String data );

    public static final native void uninstallActions( String name );

    @Getter
    public static final native flash.FlashArray getActionsList( );

    public static final native String getActions( String name );
}
