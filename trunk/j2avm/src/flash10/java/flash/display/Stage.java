//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Stage extends flash.display.DisplayObjectContainer {

    @Setter
    @Override
    public native void setTabIndex( int setTabIndex );

    @Setter
    public native void setStageFocusRect( boolean setStageFocusRect );

    @Getter
    public native String getAlign( );

    @Override
    public native boolean willTrigger( String type );

    @Setter
    @Override
    public native void setMouseChildren( boolean setMouseChildren );

    public native boolean isFocusInaccessible( );

    @Setter
    public native void setStageHeight( int setStageHeight );

    @Override
    public native boolean hasEventListener( String type );

    @Getter
    public native String getScaleMode( );

    @Override
    public native flash.display.DisplayObject addChildAt( flash.display.DisplayObject child, int index );

    @Getter
    public native boolean getShowDefaultContextMenu( );

    @Setter
    @Override
    public native void setWidth( double setWidth );

    @Setter
    public native void setShowDefaultContextMenu( boolean setShowDefaultContextMenu );

    @Setter
    @Override
    public native void setName( String setName );

    @Override
    public native void setChildIndex( flash.display.DisplayObject child, int index );

    @Setter
    public native void setAlign( String setAlign );

    @Setter
    public native void setScaleMode( String setScaleMode );

    @Setter
    @Override
    public native void setScaleX( double setScaleX );

    @Override
    public native void swapChildrenAt( int index1, int index2 );

    @Setter
    @Override
    public native void setScaleY( double setScaleY );

    @Setter
    @Override
    public native void setScaleZ( double setScaleZ );

    @Getter
    public native String getColorCorrection( );

    @Setter
    @Override
    public native void setScrollRect( flash.geom.Rectangle setScrollRect );

    @Getter
    @Override
    public native int getNumChildren( );

    @Getter
    @Override
    public native double getHeight( );

    @Setter
    @Override
    public native void setBlendMode( String setBlendMode );

    @Getter
    @Override
    public native flash.text.TextSnapshot getTextSnapshot( );

    @Setter
    @Override
    public native void setScale9Grid( flash.geom.Rectangle setScale9Grid );

    @Getter
    public native int getFullScreenWidth( );

    @Setter
    public native void setFocus( flash.display.InteractiveObject setFocus );

    @Setter
    public native void setFullScreenSourceRect( flash.geom.Rectangle setFullScreenSourceRect );

    @Setter
    @Override
    public native void setRotationY( double setRotationY );

    @Setter
    public native void setQuality( String setQuality );

    @Setter
    @Override
    public native void setRotationZ( double setRotationZ );

    @Setter
    @Override
    public native void setRotationX( double setRotationX );

    @Setter
    @Override
    public native void setAlpha( double setAlpha );

    @Setter
    @Override
    public native void setFocusRect( flash.FlashObject setFocusRect );

    @Setter
    @Override
    public native void setAccessibilityImplementation( flash.accessibility.AccessibilityImplementation setAccessibilityImplementation );

    @Setter
    public native void setColorCorrection( String setColorCorrection );

    @Getter
    @Override
    public native boolean getTabChildren( );

    @Getter
    @Override
    public native boolean getMouseChildren( );

    @Getter
    public native int getStageHeight( );

    @Setter
    @Override
    public native void setCacheAsBitmap( boolean setCacheAsBitmap );

    @Setter
    @Override
    public native void setMouseEnabled( boolean setMouseEnabled );

    @Setter
    @Override
    public native void setAccessibilityProperties( flash.accessibility.AccessibilityProperties setAccessibilityProperties );

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

    @Setter
    @Override
    public native void setHeight( double setHeight );

    @Override
    public native boolean dispatchEvent( flash.events.Event event );

    @Setter
    public native void setStageWidth( int setStageWidth );

    @Getter
    @Override
    public native double getWidth( );

    @Getter
    public native flash.display.InteractiveObject getFocus( );

    @Setter
    public native void setFrameRate( double setFrameRate );

    @Setter
    @Override
    public native void setContextMenu( flash.ui.ContextMenu setContextMenu );

    @Setter
    @Override
    public native void setOpaqueBackground( flash.FlashObject setOpaqueBackground );

    @Setter
    @Override
    public native void setMask( flash.display.DisplayObject setMask );

    @Getter
    public native flash.geom.Rectangle getFullScreenSourceRect( );

    @Getter
    public native int getFullScreenHeight( );

    @Setter
    @Override
    public native void setVisible( boolean setVisible );

    @Setter
    public native void setDisplayState( String setDisplayState );

    @Setter
    @Override
    public native void setTransform( flash.geom.Transform setTransform );

    @Getter
    public native int getStageWidth( );

    @Getter
    public native double getFrameRate( );

    @Getter
    public native String getColorCorrectionSupport( );

    @Getter
    public native String getDisplayState( );

    @Setter
    @Override
    public native void setX( double setX );

    @Setter
    @Override
    public native void setY( double setY );

    @Setter
    @Override
    public native void setZ( double setZ );

    @Setter
    @Override
    public native void setFilters( flash.FlashArray setFilters );

    @Setter
    @Override
    public native void setTabChildren( boolean setTabChildren );

    @Setter
    @Override
    public native void setTabEnabled( boolean setTabEnabled );

    @Override
    public native flash.display.DisplayObject addChild( flash.display.DisplayObject child );

    @Setter
    @Override
    public native void setRotation( double setRotation );

    @Getter
    public native boolean getStageFocusRect( );

    @Getter
    public native String getQuality( );
}
