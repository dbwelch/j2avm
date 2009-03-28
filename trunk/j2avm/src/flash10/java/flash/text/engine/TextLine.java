//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class TextLine extends flash.display.DisplayObjectContainer {

    public static final int MAX_LINE_WIDTH = 1000000;

    @Getter
    public native flash.text.engine.TextLineMirrorRegion[] getMirrorRegions( );

    @Getter
    public native double getDescent( );

    public native flash.display.DisplayObject getAtomGraphic( int atomIndex );

    public native double getBaselinePosition( String baseline );

    @Getter
    public native flash.text.engine.TextLine getNextLine( );

    public native flash.text.engine.TextLineMirrorRegion getMirrorRegion( flash.events.EventDispatcher mirror );

    @Getter
    public native flash.text.engine.TextLine getPreviousLine( );

    public native String dump( );

    public native int getAtomBidiLevel( int atomIndex );

    public native int getAtomIndexAtPoint( double stageX, double stageY );

    @Getter
    public native double getUnjustifiedTextWidth( );

    @Getter
    public native Object getUserData( );

    @Setter
    public native void setUserData( Object setUserData );

    @Setter
    @Override
    public native void setTabEnabled( boolean setTabEnabled );

    @Getter
    public native double getTextWidth( );

    @Getter
    public native double getAscent( );

    @Setter
    @Override
    public native void setContextMenu( flash.ui.ContextMenu setContextMenu );

    public native int getAtomIndexAtCharIndex( int charIndex );

    @Getter
    public native flash.text.engine.TextBlock getTextBlock( );

    public native boolean getAtomWordBoundaryOnLeft( int atomIndex );

    public native int getAtomTextBlockBeginIndex( int atomIndex );

    public native flash.geom.Rectangle getAtomBounds( int atomIndex );

    @Setter
    public native void setValidity( String setValidity );

    @Setter
    @Override
    public native void setTabChildren( boolean setTabChildren );

    @Getter
    public native int getTextBlockBeginIndex( );

    @Getter
    public native boolean getHasGraphicElement( );

    public native int getAtomTextBlockEndIndex( int atomIndex );

    @Getter
    public native String getValidity( );

    @Getter
    public native double getTextHeight( );

    @Getter
    public native double getSpecifiedWidth( );

    @Setter
    @Override
    public native void setFocusRect( flash.FlashObject setFocusRect );

    public native String getAtomTextRotation( int atomIndex );

    @Setter
    @Override
    public native void setTabIndex( int setTabIndex );

    @Getter
    public native int getRawTextLength( );

    public native double getAtomCenter( int atomIndex );

    @Getter
    public native int getAtomCount( );

    public native void flushAtomData( );
}
