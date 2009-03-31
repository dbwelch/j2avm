//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class TextLineMirrorRegion extends flash.FlashObject {

    public  TextLineMirrorRegion( ) {}

    @Getter
    public native flash.text.engine.TextLine getTextLine( );

    @Getter
    public native flash.geom.Rectangle getBounds( );

    @Getter
    public native flash.text.engine.TextLineMirrorRegion getNextRegion( );

    @Getter
    public native flash.text.engine.TextLineMirrorRegion getPreviousRegion( );

    @Getter
    public native flash.text.engine.ContentElement getElement( );

    @Getter
    public native flash.events.EventDispatcher getMirror( );
}
