//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class TextSnapshot extends flash.FlashObject {

    public native boolean getSelected( int beginIndex, int endIndex );

    public native String getText( int beginIndex, int endIndex, boolean includeLineEndings );

    public native String getText( int beginIndex, int endIndex );

    public native void setSelected( int beginIndex, int endIndex, boolean select );

    public native void setSelectColor( int hexColor );

    public native void setSelectColor( );

    public native int findText( int beginIndex, String textToFind, boolean caseSensitive );

    @Getter
    public native int getCharCount( );

    public native double hitTestTextNearPos( double x, double y, double maxDistance );

    public native double hitTestTextNearPos( double x, double y );

    public native flash.FlashArray getTextRunInfo( int beginIndex, int endIndex );

    public native String getSelectedText( boolean includeLineEndings );

    public native String getSelectedText( );
}
