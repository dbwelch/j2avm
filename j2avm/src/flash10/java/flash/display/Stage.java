//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Stage extends flash.display.DisplayObjectContainer {

    public  Stage( ) {}

    @Override
    @Setter
    public native void setTabIndex( int value );

    @Setter
    public native void setStageFocusRect( boolean on );

    @Getter
    public native String getAlign( );

    @Override
    public native boolean willTrigger( String type );

    @Override
    @Setter
    public native void setMouseChildren( boolean value );

    public native boolean isFocusInaccessible( );

    @Setter
    public native void setStageHeight( int value );

    @Override
    public native boolean hasEventListener( String type );

    @Getter
    public native String getScaleMode( );

    @Override
    public native flash.display.DisplayObject addChildAt( flash.display.DisplayObject child, int index );

    @Getter
    public native boolean getShowDefaultContextMenu( );

    @Override
    @Setter
    public native void setWidth( double value );

    @Setter
    public native void setShowDefaultContextMenu( boolean value );

    @Override
    @Setter
    public native void setName( String value );

    @Override
    public native void setChildIndex( flash.display.DisplayObject child, int index );

    @Setter
    public native void setAlign( String value );

    @Setter
    public native void setScaleMode( String value );

    @Override
    @Setter
    public native void setScaleX( double value );

    @Override
    public native void swapChildrenAt( int index1, int index2 );

    @Override
    @Setter
    public native void setScaleY( double value );

    @Override
    @Setter
    public native void setScaleZ( double value );

    @Getter
    public native String getColorCorrection( );

    @Override
    @Setter
    public native void setScrollRect( flash.geom.Rectangle value );

    @Override
    @Getter
    public native int getNumChildren( );

    @Override
    @Getter
    public native double getHeight( );

    @Override
    @Setter
    public native void setBlendMode( String value );

    @Override
    @Getter
    public native flash.text.TextSnapshot getTextSnapshot( );

    @Override
    @Setter
    public native void setScale9Grid( flash.geom.Rectangle value );

    @Getter
    public native int getFullScreenWidth( );

    @Setter
    public native void setFocus( flash.display.InteractiveObject newFocus );

    @Setter
    public native void setFullScreenSourceRect( flash.geom.Rectangle value );

    @Override
    @Setter
    public native void setRotationY( double value );

    @Setter
    public native void setQuality( String value );

    @Override
    @Setter
    public native void setRotationZ( double value );

    @Override
    @Setter
    public native void setRotationX( double value );

    @Override
    @Setter
    public native void setAlpha( double value );

    @Override
    @Setter
    public native void setFocusRect( flash.FlashObject value );

    @Override
    @Setter
    public native void setAccessibilityImplementation( flash.accessibility.AccessibilityImplementation value );

    @Setter
    public native void setColorCorrection( String value );

    @Override
    @Getter
    public native boolean getTabChildren( );

    @Override
    @Getter
    public native boolean getMouseChildren( );

    @Getter
    public native int getStageHeight( );

    @Override
    @Setter
    public native void setCacheAsBitmap( boolean value );

    @Override
    @Setter
    public native void setMouseEnabled( boolean value );

    @Override
    @Setter
    public native void setAccessibilityProperties( flash.accessibility.AccessibilityProperties value );

    public native void invalidate( );

    @Override
    public native flash.display.DisplayObject removeChildAt( int index );

    @Override
    public native void addEventListener( String type, flash.FlashFunction listener, boolean useCapture, int priority, boolean useWeakReference );

    @Override
    public native void addEventListener( String type, flash.FlashFunction listener, boolean useCapture, int priority );

    @Override
    public native void addEventListener( String type, flash.FlashFunction listener, boolean useCapture );

    @Override
    public native void addEventListener( String type, flash.FlashFunction listener );

    @Override
    @Setter
    public native void setHeight( double value );

    @Override
    public native boolean dispatchEvent( flash.events.Event event );

    @Setter
    public native void setStageWidth( int value );

    @Override
    @Getter
    public native double getWidth( );

    @Getter
    public native flash.display.InteractiveObject getFocus( );

    @Setter
    public native void setFrameRate( double value );

    @Override
    @Setter
    public native void setContextMenu( flash.ui.ContextMenu value );

    @Override
    @Setter
    public native void setOpaqueBackground( flash.FlashObject value );

    @Override
    @Setter
    public native void setMask( flash.display.DisplayObject value );

    @Getter
    public native flash.geom.Rectangle getFullScreenSourceRect( );

    @Getter
    public native int getFullScreenHeight( );

    @Override
    @Setter
    public native void setVisible( boolean value );

    @Setter
    public native void setDisplayState( String value );

    @Override
    @Setter
    public native void setTransform( flash.geom.Transform value );

    @Getter
    public native int getStageWidth( );

    @Getter
    public native double getFrameRate( );

    @Getter
    public native String getColorCorrectionSupport( );

    @Getter
    public native String getDisplayState( );

    @Override
    @Setter
    public native void setX( double value );

    @Override
    @Setter
    public native void setY( double value );

    @Override
    @Setter
    public native void setZ( double value );

    @Override
    @Setter
    public native void setFilters( flash.FlashArray value );

    @Override
    @Setter
    public native void setTabChildren( boolean value );

    @Override
    @Setter
    public native void setTabEnabled( boolean value );

    @Override
    public native flash.display.DisplayObject addChild( flash.display.DisplayObject child );

    @Override
    @Setter
    public native void setRotation( double value );

    @Getter
    public native boolean getStageFocusRect( );

    @Getter
    public native String getQuality( );
}
