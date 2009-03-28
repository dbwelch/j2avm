//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GroupElement extends flash.text.engine.ContentElement {

    public native flash.text.engine.ContentElement getElementAt( int index );

    public native flash.text.engine.ContentElement getElementAtCharIndex( int charIndex );

    @Getter
    public native int getElementCount( );

    public native int getElementIndex( flash.text.engine.ContentElement element );

    public native flash.text.engine.TextElement splitTextElement( int elementIndex, int splitIndex );

    public native flash.text.engine.GroupElement groupElements( int beginIndex, int endIndex );

    public native void setElements( flash.text.engine.ContentElement[] value );

    public native flash.text.engine.ContentElement[] replaceElements( int beginIndex, int endIndex, flash.text.engine.ContentElement[] newElements );

    public native flash.text.engine.TextElement mergeTextElements( int beginIndex, int endIndex );

    public native void ungroupElements( int groupIndex );
}
