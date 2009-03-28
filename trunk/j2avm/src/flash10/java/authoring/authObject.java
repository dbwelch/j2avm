//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package authoring;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class authObject extends flash.FlashObject {

    @Setter
    public static final native void setOffScreenSurfaceRenderingEnabled( boolean setOffScreenSurfaceRenderingEnabled );

    @Getter
    public static final native boolean getOffScreenSurfaceRenderingEnabled( );

    public native int FrameOffset( );

    public native boolean CacheAsBitmap( );

    public native int EndPosition( );

    public native boolean IsSelected( );

    @Getter
    public native int getKey( );

    public native boolean IsVisible( boolean inThumbnailPreview );

    public native int StartPosition( );

    public native boolean HasShapeSelection( );

    public native authoring.authObject MotionPath( );

    public native flash.geom.Rectangle Bounds( int flags, int minFrame, int maxFrame );

    public native flash.geom.Rectangle Bounds( int flags, int minFrame );

    public native flash.geom.Rectangle Bounds( int flags );

    public native flash.FlashArray ThreeDTranslationHandlePoints( );

    public native flash.geom.ColorTransform ColorXForm( );

    @Getter
    public native int getType( );

    public native flash.geom.Point CenterPoint( );

    @Getter
    public native int getSwfKey( );

    public native boolean IsFloater( );

    public native int OutlineColor( );

    public native flash.FlashArray Filters( );

    public native String BlendingMode( );

    public native boolean OutlineMode( );

    public native int FrameType( );

    public native boolean Locked( );

    @Getter
    public native authoring.authObject getFirstChild( );

    public native flash.geom.Matrix3D ThreeDMatrix( );

    @Getter
    public native authoring.authObject getNextSibling( );

    public native int SymbolBehavior( );

    public native flash.geom.Rectangle Scale9Grid( );

    public native flash.geom.Point LivePreviewSize( );

    public native flash.geom.Point RegistrationPoint( );

    public native boolean HasEmptyPath( );

    public native authoring.authObject FrameForFrameNumber( int frameNum );

    public native int SymbolMode( );

    public native boolean IsPrimitive( );

    public native flash.geom.Matrix ObjMatrix( );
}
