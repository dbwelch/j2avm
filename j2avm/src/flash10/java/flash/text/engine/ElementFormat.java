//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class ElementFormat extends flash.FlashObject {

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift, String kerning, double trackingRight, double trackingLeft, String locale, String breakOpportunity, String digitCase, String digitWidth, String ligatureLevel, String typographicCase ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift, String kerning, double trackingRight, double trackingLeft, String locale, String breakOpportunity, String digitCase, String digitWidth, String ligatureLevel ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift, String kerning, double trackingRight, double trackingLeft, String locale, String breakOpportunity, String digitCase, String digitWidth ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift, String kerning, double trackingRight, double trackingLeft, String locale, String breakOpportunity, String digitCase ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift, String kerning, double trackingRight, double trackingLeft, String locale, String breakOpportunity ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift, String kerning, double trackingRight, double trackingLeft, String locale ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift, String kerning, double trackingRight, double trackingLeft ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift, String kerning, double trackingRight ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift, String kerning ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline, double baselineShift ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline, String alignmentBaseline ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation, String dominantBaseline ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha, String textRotation ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color, double alpha ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize, int color ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription, double fontSize ) {}

    public  ElementFormat( flash.text.engine.FontDescription fontDescription ) {}

    public  ElementFormat( ) {}

    @Setter
    public native void setBaselineShift( double value );

    @Setter
    public native void setTrackingLeft( double value );

    @Getter
    public native double getBaselineShift( );

    @Getter
    public native String getDominantBaseline( );

    @Setter
    public native void setColor( int value );

    @Getter
    public native String getAlignmentBaseline( );

    @Setter
    public native void setDominantBaseline( String dominantBaseline );

    @Getter
    public native String getTextRotation( );

    @Getter
    public native String getKerning( );

    @Setter
    public native void setAlignmentBaseline( String alignmentBaseline );

    @Setter
    public native void setTrackingRight( double value );

    @Getter
    public native String getBreakOpportunity( );

    @Setter
    public native void setTextRotation( String value );

    @Setter
    public native void setKerning( String value );

    @Getter
    public native String getDigitWidth( );

    @Setter
    public native void setFontDescription( flash.text.engine.FontDescription value );

    @Setter
    public native void setLocked( boolean value );

    public native flash.text.engine.ElementFormat clone( );

    @Getter
    public native double getAlpha( );

    @Setter
    public native void setLigatureLevel( String ligatureLevelType );

    @Setter
    public native void setFontSize( double value );

    @Getter
    public native String getLocale( );

    @Getter
    public native boolean getLocked( );

    @Getter
    public native int getColor( );

    @Getter
    public native double getTrackingRight( );

    @Setter
    public native void setBreakOpportunity( String opportunityType );

    @Getter
    public native flash.text.engine.FontDescription getFontDescription( );

    @Setter
    public native void setTypographicCase( String typographicCaseType );

    @Getter
    public native double getFontSize( );

    @Setter
    public native void setDigitWidth( String digitWidthType );

    @Setter
    public native void setLocale( String value );

    @Getter
    public native double getTrackingLeft( );

    @Getter
    public native String getLigatureLevel( );

    @Setter
    public native void setDigitCase( String digitCaseType );

    @Getter
    public native String getTypographicCase( );

    @Setter
    public native void setAlpha( double value );

    @Getter
    public native String getDigitCase( );

    public native flash.text.engine.FontMetrics getFontMetrics( );
}
