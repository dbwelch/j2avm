//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ContentElement extends flash.FlashObject {

    public  ContentElement( flash.text.engine.ElementFormat elementFormat, flash.events.EventDispatcher eventMirror, String textRotation ) {}

    public  ContentElement( flash.text.engine.ElementFormat elementFormat, flash.events.EventDispatcher eventMirror ) {}

    public  ContentElement( flash.text.engine.ElementFormat elementFormat ) {}

    public  ContentElement( ) {}

    public static final int GRAPHIC_ELEMENT = 65007;

    @Getter
    public native flash.text.engine.TextBlock getTextBlock( );

    @Setter
    public native void setElementFormat( flash.text.engine.ElementFormat value );

    @Getter
    public native int getTextBlockBeginIndex( );

    @Getter
    public native String getTextRotation( );

    @Getter
    public native String getText( );

    @Setter
    public native void setEventMirror( flash.events.EventDispatcher value );

    @Getter
    public native flash.text.engine.ElementFormat getElementFormat( );

    @Getter
    public native Object getUserData( );

    @Setter
    public native void setUserData( Object userData );

    @Setter
    public native void setTextRotation( String value );

    @Getter
    public native flash.events.EventDispatcher getEventMirror( );

    @Getter
    public native String getRawText( );

    @Getter
    public native flash.text.engine.GroupElement getGroupElement( );
}
