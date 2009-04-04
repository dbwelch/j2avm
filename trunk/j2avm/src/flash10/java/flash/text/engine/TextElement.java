//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class TextElement extends flash.text.engine.ContentElement {

    public  TextElement( String text, flash.text.engine.ElementFormat elementFormat, flash.events.EventDispatcher eventMirror, String textRotation ) {}

    public  TextElement( String text, flash.text.engine.ElementFormat elementFormat, flash.events.EventDispatcher eventMirror ) {}

    public  TextElement( String text, flash.text.engine.ElementFormat elementFormat ) {}

    public  TextElement( String text ) {}

    public  TextElement( ) {}

    public native void replaceText( int beginIndex, int endIndex, String newText );

    @Setter
    public native void setText( String value );
}
