//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class TextBlock extends flash.FlashObject {

    public  TextBlock( flash.text.engine.ContentElement content, flash.text.engine.TabStop[] tabStops, flash.text.engine.TextJustifier textJustifier, String lineRotation, String baselineZero, int bidiLevel, boolean applyNonLinearFontScaling, flash.text.engine.FontDescription baselineFontDescription, double baselineFontSize ) {}

    public  TextBlock( flash.text.engine.ContentElement content, flash.text.engine.TabStop[] tabStops, flash.text.engine.TextJustifier textJustifier, String lineRotation, String baselineZero, int bidiLevel, boolean applyNonLinearFontScaling, flash.text.engine.FontDescription baselineFontDescription ) {}

    public  TextBlock( flash.text.engine.ContentElement content, flash.text.engine.TabStop[] tabStops, flash.text.engine.TextJustifier textJustifier, String lineRotation, String baselineZero, int bidiLevel, boolean applyNonLinearFontScaling ) {}

    public  TextBlock( flash.text.engine.ContentElement content, flash.text.engine.TabStop[] tabStops, flash.text.engine.TextJustifier textJustifier, String lineRotation, String baselineZero, int bidiLevel ) {}

    public  TextBlock( flash.text.engine.ContentElement content, flash.text.engine.TabStop[] tabStops, flash.text.engine.TextJustifier textJustifier, String lineRotation, String baselineZero ) {}

    public  TextBlock( flash.text.engine.ContentElement content, flash.text.engine.TabStop[] tabStops, flash.text.engine.TextJustifier textJustifier, String lineRotation ) {}

    public  TextBlock( flash.text.engine.ContentElement content, flash.text.engine.TabStop[] tabStops, flash.text.engine.TextJustifier textJustifier ) {}

    public  TextBlock( flash.text.engine.ContentElement content, flash.text.engine.TabStop[] tabStops ) {}

    public  TextBlock( flash.text.engine.ContentElement content ) {}

    public  TextBlock( ) {}

    @Getter
    public native flash.text.engine.TextJustifier getTextJustifier( );

    public native flash.text.engine.TextLine getTextLineAtCharIndex( int charIndex );

    @Getter
    public native flash.text.engine.TextLine getFirstLine( );

    @Setter
    public native void setTextJustifier( flash.text.engine.TextJustifier value );

    @Getter
    public native flash.text.engine.ContentElement getContent( );

    public native int findPreviousAtomBoundary( int beforeCharIndex );

    @Getter
    public native String getBaselineZero( );

    public native int findNextAtomBoundary( int afterCharIndex );

    public native int findNextWordBoundary( int afterCharIndex );

    @Setter
    public native void setBaselineFontDescription( flash.text.engine.FontDescription value );

    @Getter
    public native String getLineRotation( );

    public native int findPreviousWordBoundary( int beforeCharIndex );

    @Getter
    public native boolean getApplyNonLinearFontScaling( );

    @Getter
    public native Object getUserData( );

    @Setter
    public native void setUserData( Object userData );

    @Getter
    public native int getBidiLevel( );

    @Setter
    public native void setBaselineZero( String value );

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
    public native void setLineRotation( String value );

    @Setter
    public native void setApplyNonLinearFontScaling( boolean value );

    @Getter
    public native flash.text.engine.TextLine getLastLine( );

    @Getter
    public native flash.text.engine.FontDescription getBaselineFontDescription( );

    @Setter
    public native void setBidiLevel( int value );

    @Setter
    public native void setBaselineFontSize( double value );

    @Setter
    public native void setContent( flash.text.engine.ContentElement value );

    public native String dump( );

    @Setter
    public native void setTabStops( flash.text.engine.TabStop[] value );

    @Getter
    public native flash.text.engine.TextLine getFirstInvalidLine( );

    @Getter
    public native String getTextLineCreationResult( );

    public native void releaseLines( flash.text.engine.TextLine firstLine, flash.text.engine.TextLine lastLine );
}
