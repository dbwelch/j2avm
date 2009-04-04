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
    public native void setWidth( double value );

    public native flash.geom.Point globalToLocal( flash.geom.Point point );

    @Getter
    public native String getBlendMode( );

    @Getter
    public native flash.geom.Rectangle getScale9Grid( );

    @Setter
    public native void setName( String value );

    @Getter
    public native double getRotationX( );

    @Getter
    public native double getRotationY( );

    @Setter
    public native void setScaleX( double value );

    @Setter
    public native void setScaleY( double value );

    @Setter
    public native void setScaleZ( double value );

    @Getter
    public native flash.accessibility.AccessibilityProperties getAccessibilityProperties( );

    @Setter
    public native void setScrollRect( flash.geom.Rectangle value );

    @Getter
    public native double getRotationZ( );

    @Getter
    public native double getHeight( );

    @Setter
    public native void setBlendMode( String value );

    @Setter
    public native void setScale9Grid( flash.geom.Rectangle innerRectangle );

    public native flash.geom.Rectangle getBounds( flash.display.DisplayObject targetCoordinateSpace );

    @Setter
    public native void setBlendShader( flash.display.Shader value );

    @Getter
    public native flash.FlashObject getOpaqueBackground( );

    @Getter
    public native flash.display.DisplayObjectContainer getParent( );

    @Getter
    public native boolean getCacheAsBitmap( );

    @Setter
    public native void setRotationX( double value );

    @Setter
    public native void setRotationY( double value );

    @Setter
    public native void setRotationZ( double value );

    public native flash.geom.Point local3DToGlobal( flash.geom.Vector3D point3d );

    @Setter
    public native void setAlpha( double value );

    public native flash.geom.Vector3D globalToLocal3D( flash.geom.Point point );

    @Setter
    public native void setAccessibilityProperties( flash.accessibility.AccessibilityProperties value );

    @Getter
    public native double getWidth( );

    public native boolean hitTestPoint( double x, double y, boolean shapeFlag );

    public native boolean hitTestPoint( double x, double y );

    @Setter
    public native void setCacheAsBitmap( boolean value );

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
    public native void setHeight( double value );

    @Setter
    public native void setMask( flash.display.DisplayObject value );

    public native flash.geom.Rectangle getRect( flash.display.DisplayObject targetCoordinateSpace );

    @Getter
    public native double getAlpha( );

    @Setter
    public native void setTransform( flash.geom.Transform value );

    @Getter
    public native flash.display.LoaderInfo getLoaderInfo( );

    @Getter
    public native flash.display.DisplayObject getRoot( );

    @Setter
    public native void setVisible( boolean value );

    @Setter
    public native void setOpaqueBackground( flash.FlashObject value );

    public native boolean hitTestObject( flash.display.DisplayObject obj );

    @Getter
    public native flash.display.DisplayObject getMask( );

    @Setter
    public native void setX( double value );

    @Setter
    public native void setY( double value );

    @Getter
    public native flash.geom.Transform getTransform( );

    @Setter
    public native void setZ( double value );

    @Setter
    public native void setFilters( flash.FlashArray value );

    @Getter
    public native double getX( );

    @Getter
    public native double getY( );

    @Getter
    public native double getZ( );

    @Getter
    public native flash.FlashArray getFilters( );

    @Setter
    public native void setRotation( double value );

    @Getter
    public native flash.display.Stage getStage( );
}
