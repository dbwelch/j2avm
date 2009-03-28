//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class TextBlock extends flash.FlashObject {

    @Getter
    public native flash.text.engine.TextJustifier getTextJustifier( );

    public native flash.text.engine.TextLine getTextLineAtCharIndex( int charIndex );

    @Getter
    public native flash.text.engine.TextLine getFirstLine( );

    @Setter
    public native void setTextJustifier( flash.text.engine.TextJustifier setTextJustifier );

    @Getter
    public native flash.text.engine.ContentElement getContent( );

    public native int findPreviousAtomBoundary( int beforeCharIndex );

    @Getter
    public native String getBaselineZero( );

    public native int findNextAtomBoundary( int afterCharIndex );

    public native int findNextWordBoundary( int afterCharIndex );

    @Setter
    public native void setBaselineFontDescription( flash.text.engine.FontDescription setBaselineFontDescription );

    @Getter
    public native String getLineRotation( );

    public native int findPreviousWordBoundary( int beforeCharIndex );

    @Getter
    public native boolean getApplyNonLinearFontScaling( );

    @Getter
    public native Object getUserData( );

    @Setter
    public native void setUserData( Object setUserData );

    @Getter
    public native int getBidiLevel( );

    @Setter
    public native void setBaselineZero( String setBaselineZero );

    @Getter
    public native double getBaselineFontSize( );

    public native flash.text.engine.TextLine createTextLine( flash.text.engine.TextLine previousLine, double width, double lineOffset, boolean fitSomething );

    public native flash.text.engine.TextLine createTextLine( flash.text.engine.TextLine previousLine, double width, double lineOffset );

    public native flash.text.engine.TextLine createTextLine( flash.text.engine.TextLine previousLine, double width );

    public native flash.text.engine.TextLine createTextLine( flash.text.engine.TextLine previousLine );

    public native flash.text.engine.TextLine createTextLine( );

    @Getter
    public native flash.text.engine.TabStop[] getTabStops( );

    @Setter
    public native void setLineRotation( String setLineRotation );

    @Setter
    public native void setApplyNonLinearFontScaling( boolean setApplyNonLinearFontScaling );

    @Getter
    public native flash.text.engine.TextLine getLastLine( );

    @Getter
    public native flash.text.engine.FontDescription getBaselineFontDescription( );

    @Setter
    public native void setBidiLevel( int setBidiLevel );

    @Setter
    public native void setBaselineFontSize( double setBaselineFontSize );

    @Setter
    public native void setContent( flash.text.engine.ContentElement setContent );

    public native String dump( );

    @Setter
    public native void setTabStops( flash.text.engine.TabStop[] setTabStops );

    @Getter
    public native flash.text.engine.TextLine getFirstInvalidLine( );

    @Getter
    public native String getTextLineCreationResult( );

    public native void releaseLines( flash.text.engine.TextLine firstLine, flash.text.engine.TextLine lastLine );
}
