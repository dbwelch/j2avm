//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GraphicElement extends flash.text.engine.ContentElement {

    public  GraphicElement( flash.display.DisplayObject graphic, double elementWidth, double elementHeight, flash.text.engine.ElementFormat elementFormat, flash.events.EventDispatcher eventMirror, String textRotation ) {}

    public  GraphicElement( flash.display.DisplayObject graphic, double elementWidth, double elementHeight, flash.text.engine.ElementFormat elementFormat, flash.events.EventDispatcher eventMirror ) {}

    public  GraphicElement( flash.display.DisplayObject graphic, double elementWidth, double elementHeight, flash.text.engine.ElementFormat elementFormat ) {}

    public  GraphicElement( flash.display.DisplayObject graphic, double elementWidth, double elementHeight ) {}

    public  GraphicElement( flash.display.DisplayObject graphic, double elementWidth ) {}

    public  GraphicElement( flash.display.DisplayObject graphic ) {}

    public  GraphicElement( ) {}

    @Setter
    public native void setGraphic( flash.display.DisplayObject setGraphic );

    @Getter
    public native double getElementHeight( );

    @Setter
    public native void setElementWidth( double setElementWidth );

    @Setter
    public native void setElementHeight( double setElementHeight );

    @Getter
    public native flash.display.DisplayObject getGraphic( );

    @Getter
    public native double getElementWidth( );
}
