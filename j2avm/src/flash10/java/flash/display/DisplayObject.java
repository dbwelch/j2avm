//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class DisplayObject extends flash.events.EventDispatcher implements flash.display.IBitmapDrawable {

    public  DisplayObject( ) {}

    @Getter
    public native boolean getVisible( );

    @Getter
    public native double getRotation( );

    public native flash.geom.Point localToGlobal( flash.geom.Point point );

    @Getter
    public native String getName( );

    @Setter
    public native void setWidth( double setWidth );

    public native flash.geom.Point globalToLocal( flash.geom.Point point );

    @Getter
    public native String getBlendMode( );

    @Getter
    public native flash.geom.Rectangle getScale9Grid( );

    @Setter
    public native void setName( String setName );

    @Getter
    public native double getRotationX( );

    @Getter
    public native double getRotationY( );

    @Setter
    public native void setScaleX( double setScaleX );

    @Setter
    public native void setScaleY( double setScaleY );

    @Setter
    public native void setScaleZ( double setScaleZ );

    @Getter
    public native flash.accessibility.AccessibilityProperties getAccessibilityProperties( );

    @Setter
    public native void setScrollRect( flash.geom.Rectangle setScrollRect );

    @Getter
    public native double getRotationZ( );

    @Getter
    public native double getHeight( );

    @Setter
    public native void setBlendMode( String setBlendMode );

    @Setter
    public native void setScale9Grid( flash.geom.Rectangle setScale9Grid );

    public native flash.geom.Rectangle getBounds( flash.display.DisplayObject targetCoordinateSpace );

    @Setter
    public native void setBlendShader( flash.display.Shader setBlendShader );

    @Getter
    public native flash.FlashObject getOpaqueBackground( );

    @Getter
    public native flash.display.DisplayObjectContainer getParent( );

    @Getter
    public native boolean getCacheAsBitmap( );

    @Setter
    public native void setRotationX( double setRotationX );

    @Setter
    public native void setRotationY( double setRotationY );

    @Setter
    public native void setRotationZ( double setRotationZ );

    public native flash.geom.Point local3DToGlobal( flash.geom.Vector3D point3d );

    @Setter
    public native void setAlpha( double setAlpha );

    public native flash.geom.Vector3D globalToLocal3D( flash.geom.Point point );

    @Setter
    public native void setAccessibilityProperties( flash.accessibility.AccessibilityProperties setAccessibilityProperties );

    @Getter
    public native double getWidth( );

    public native boolean hitTestPoint( double x, double y, boolean shapeFlag );

    public native boolean hitTestPoint( double x, double y );

    @Setter
    public native void setCacheAsBitmap( boolean setCacheAsBitmap );

    @Getter
    public native double getScaleX( );

    @Getter
    public native double getScaleY( );

    @Getter
    public native double getScaleZ( );

    @Getter
    public native flash.geom.Rectangle getScrollRect( );

    @Getter
    public native double getMouseX( );

    @Getter
    public native double getMouseY( );

    @Setter
    public native void setHeight( double setHeight );

    @Setter
    public native void setMask( flash.display.DisplayObject setMask );

    public native flash.geom.Rectangle getRect( flash.display.DisplayObject targetCoordinateSpace );

    @Getter
    public native double getAlpha( );

    @Setter
    public native void setTransform( flash.geom.Transform setTransform );

    @Getter
    public native flash.display.LoaderInfo getLoaderInfo( );

    @Getter
    public native flash.display.DisplayObject getRoot( );

    @Setter
    public native void setVisible( boolean setVisible );

    @Setter
    public native void setOpaqueBackground( flash.FlashObject setOpaqueBackground );

    public native boolean hitTestObject( flash.display.DisplayObject obj );

    @Getter
    public native flash.display.DisplayObject getMask( );

    @Setter
    public native void setX( double setX );

    @Setter
    public native void setY( double setY );

    @Getter
    public native flash.geom.Transform getTransform( );

    @Setter
    public native void setZ( double setZ );

    @Setter
    public native void setFilters( flash.FlashArray setFilters );

    @Getter
    public native double getX( );

    @Getter
    public native double getY( );

    @Getter
    public native double getZ( );

    @Getter
    public native flash.FlashArray getFilters( );

    @Setter
    public native void setRotation( double setRotation );

    @Getter
    public native flash.display.Stage getStage( );
}
