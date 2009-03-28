//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class Graphics extends flash.FlashObject {

    public native void drawTriangles( double[] vertices, int[] indices, double[] uvtData, String culling );

    public native void drawTriangles( double[] vertices, int[] indices, double[] uvtData );

    public native void drawTriangles( double[] vertices, int[] indices );

    public native void drawTriangles( double[] vertices );

    public native void drawRect( double x, double y, double width, double height );

    public native void drawPath( int[] commands, double[] data, String winding );

    public native void drawPath( int[] commands, double[] data );

    public native void curveTo( double controlX, double controlY, double anchorX, double anchorY );

    public native void beginFill( int color, double alpha );

    public native void beginFill( int color );

    public native void clear( );

    public native void lineTo( double x, double y );

    public native void beginShaderFill( flash.display.Shader shader, flash.geom.Matrix matrix );

    public native void beginShaderFill( flash.display.Shader shader );

    public native void lineGradientStyle( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, flash.geom.Matrix matrix, String spreadMethod, String interpolationMethod, double focalPointRatio );

    public native void lineGradientStyle( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, flash.geom.Matrix matrix, String spreadMethod, String interpolationMethod );

    public native void lineGradientStyle( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, flash.geom.Matrix matrix, String spreadMethod );

    public native void lineGradientStyle( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, flash.geom.Matrix matrix );

    public native void lineGradientStyle( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios );

    public native void beginBitmapFill( flash.display.BitmapData bitmap, flash.geom.Matrix matrix, boolean repeat, boolean smooth );

    public native void beginBitmapFill( flash.display.BitmapData bitmap, flash.geom.Matrix matrix, boolean repeat );

    public native void beginBitmapFill( flash.display.BitmapData bitmap, flash.geom.Matrix matrix );

    public native void beginBitmapFill( flash.display.BitmapData bitmap );

    public native void beginGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, flash.geom.Matrix matrix, String spreadMethod, String interpolationMethod, double focalPointRatio );

    public native void beginGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, flash.geom.Matrix matrix, String spreadMethod, String interpolationMethod );

    public native void beginGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, flash.geom.Matrix matrix, String spreadMethod );

    public native void beginGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios, flash.geom.Matrix matrix );

    public native void beginGradientFill( String type, flash.FlashArray colors, flash.FlashArray alphas, flash.FlashArray ratios );

    public native void lineStyle( double thickness, int color, double alpha, boolean pixelHinting, String scaleMode, String caps, String joints, double miterLimit );

    public native void lineStyle( double thickness, int color, double alpha, boolean pixelHinting, String scaleMode, String caps, String joints );

    public native void lineStyle( double thickness, int color, double alpha, boolean pixelHinting, String scaleMode, String caps );

    public native void lineStyle( double thickness, int color, double alpha, boolean pixelHinting, String scaleMode );

    public native void lineStyle( double thickness, int color, double alpha, boolean pixelHinting );

    public native void lineStyle( double thickness, int color, double alpha );

    public native void lineStyle( double thickness, int color );

    public native void lineStyle( double thickness );

    public native void drawRoundRectComplex( double x, double y, double width, double height, double topLeftRadius, double topRightRadius, double bottomLeftRadius, double bottomRightRadius );

    public native void moveTo( double x, double y );

    public native void drawRoundRect( double x, double y, double width, double height, double ellipseWidth, double ellipseHeight );

    public native void drawCircle( double x, double y, double radius );

    public native void lineBitmapStyle( flash.display.BitmapData bitmap, flash.geom.Matrix matrix, boolean repeat, boolean smooth );

    public native void lineBitmapStyle( flash.display.BitmapData bitmap, flash.geom.Matrix matrix, boolean repeat );

    public native void lineBitmapStyle( flash.display.BitmapData bitmap, flash.geom.Matrix matrix );

    public native void lineBitmapStyle( flash.display.BitmapData bitmap );

    public native void lineShaderStyle( flash.display.Shader shader, flash.geom.Matrix matrix );

    public native void lineShaderStyle( flash.display.Shader shader );

    public native void drawGraphicsData( flash.display.IGraphicsData[] graphicsData );

    public native void drawEllipse( double x, double y, double width, double height );

    public native void endFill( );

    public native void copyFrom( flash.display.Graphics sourceGraphics );
}
