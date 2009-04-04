//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class TextRun extends flash.FlashObject {

    public  TextRun( int beginIndex, int endIndex, flash.text.TextFormat textFormat ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected TextRun() {}

    @Getter
    public native flash.text.TextFormat getTextFormat( );

    @Setter
    public native void setTextFormat( flash.text.TextFormat textFormat );

    @Getter
    public native int getEndIndex( );

    @Setter
    public native void setEndIndex( int endIndex );

    @Getter
    public native int getBeginIndex( );

    @Setter
    public native void setBeginIndex( int beginIndex );
}
