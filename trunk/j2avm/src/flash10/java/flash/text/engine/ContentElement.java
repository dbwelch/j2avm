//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ContentElement extends flash.FlashObject {

    public static final int GRAPHIC_ELEMENT = 65007;

    @Getter
    public native flash.text.engine.TextBlock getTextBlock( );

    @Setter
    public native void setElementFormat( flash.text.engine.ElementFormat setElementFormat );

    @Getter
    public native int getTextBlockBeginIndex( );

    @Getter
    public native String getTextRotation( );

    @Getter
    public native String getText( );

    @Setter
    public native void setEventMirror( flash.events.EventDispatcher setEventMirror );

    @Getter
    public native flash.text.engine.ElementFormat getElementFormat( );

    @Getter
    public native Object getUserData( );

    @Setter
    public native void setUserData( Object setUserData );

    @Setter
    public native void setTextRotation( String setTextRotation );

    @Getter
    public native flash.events.EventDispatcher getEventMirror( );

    @Getter
    public native String getRawText( );

    @Getter
    public native flash.text.engine.GroupElement getGroupElement( );
}
