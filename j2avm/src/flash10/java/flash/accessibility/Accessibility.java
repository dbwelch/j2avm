//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.accessibility;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class Accessibility extends flash.FlashObject {

    public  Accessibility( ) {}

    public static final native void sendEvent( flash.display.DisplayObject source, int childID, int eventType, boolean nonHTML );

    public static final native void sendEvent( flash.display.DisplayObject source, int childID, int eventType );

    public static final native void updateProperties( );

    @Getter
    public static final native boolean getActive( );
}
