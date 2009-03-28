//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class BitmapData extends flash.FlashObject implements flash.display.IBitmapDrawable {

    public native void copyPixels( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, flash.display.BitmapData alphaBitmapData, flash.geom.Point alphaPoint, boolean mergeAlpha );

    public native void copyPixels( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, flash.display.BitmapData alphaBitmapData, flash.geom.Point alphaPoint );

    public native void copyPixels( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, flash.display.BitmapData alphaBitmapData );

    public native void copyPixels( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint );

    public native void setPixel( int x, int y, int color );

    public native boolean hitTest( flash.geom.Point firstPoint, int firstAlphaThreshold, flash.FlashObject secondObject, flash.geom.Point secondBitmapDataPoint, int secondAlphaThreshold );

    public native boolean hitTest( flash.geom.Point firstPoint, int firstAlphaThreshold, flash.FlashObject secondObject, flash.geom.Point secondBitmapDataPoint );

    public native boolean hitTest( flash.geom.Point firstPoint, int firstAlphaThreshold, flash.FlashObject secondObject );

    public native void applyFilter( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, flash.filters.BitmapFilter filter );

    public native void fillRect( flash.geom.Rectangle rect, int color );

    public native void colorTransform( flash.geom.Rectangle rect, flash.geom.ColorTransform colorTransform );

    public native void draw( flash.display.IBitmapDrawable source, flash.geom.Matrix matrix, flash.geom.ColorTransform colorTransform, String blendMode, flash.geom.Rectangle clipRect, boolean smoothing );

    public native void draw( flash.display.IBitmapDrawable source, flash.geom.Matrix matrix, flash.geom.ColorTransform colorTransform, String blendMode, flash.geom.Rectangle clipRect );

    public native void draw( flash.display.IBitmapDrawable source, flash.geom.Matrix matrix, flash.geom.ColorTransform colorTransform, String blendMode );

    public native void draw( flash.display.IBitmapDrawable source, flash.geom.Matrix matrix, flash.geom.ColorTransform colorTransform );

    public native void draw( flash.display.IBitmapDrawable source, flash.geom.Matrix matrix );

    public native void draw( flash.display.IBitmapDrawable source );

    public native void setVector( flash.geom.Rectangle rect, int[] inputVector );

    @Getter
    public native int getWidth( );

    public native void copyChannel( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, int sourceChannel, int destChannel );

    public native int getPixel( int x, int y );

    public native flash.geom.Rectangle generateFilterRect( flash.geom.Rectangle sourceRect, flash.filters.BitmapFilter filter );

    @Getter
    public native boolean getTransparent( );

    public native void unlock( flash.geom.Rectangle changeRect );

    public native void unlock( );

    public native void scroll( int x, int y );

    public native flash.geom.Rectangle getColorBoundsRect( int mask, int color, boolean findColor );

    public native flash.geom.Rectangle getColorBoundsRect( int mask, int color );

    public native int pixelDissolve( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, int randomSeed, int numPixels, int fillColor );

    public native int pixelDissolve( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, int randomSeed, int numPixels );

    public native int pixelDissolve( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, int randomSeed );

    public native int pixelDissolve( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint );

    public native void noise( int randomSeed, int low, int high, int channelOptions, boolean grayScale );

    public native void noise( int randomSeed, int low, int high, int channelOptions );

    public native void noise( int randomSeed, int low, int high );

    public native void noise( int randomSeed, int low );

    public native void noise( int randomSeed );

    public native flash.display.BitmapData clone( );

    public native void dispose( );

    public native void floodFill( int x, int y, int color );

    public native void setPixel32( int x, int y, int color );

    @Getter
    public native flash.geom.Rectangle getRect( );

    public native flash.FlashObject compare( flash.display.BitmapData otherBitmapData );

    public native void perlinNoise( double baseX, double baseY, int numOctaves, int randomSeed, boolean stitch, boolean fractalNoise, int channelOptions, boolean grayScale, flash.FlashArray offsets );

    public native void perlinNoise( double baseX, double baseY, int numOctaves, int randomSeed, boolean stitch, boolean fractalNoise, int channelOptions, boolean grayScale );

    public native void perlinNoise( double baseX, double baseY, int numOctaves, int randomSeed, boolean stitch, boolean fractalNoise, int channelOptions );

    public native void perlinNoise( double baseX, double baseY, int numOctaves, int randomSeed, boolean stitch, boolean fractalNoise );

    @Getter
    public native int getHeight( );

    public native void paletteMap( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, flash.FlashArray redArray, flash.FlashArray greenArray, flash.FlashArray blueArray, flash.FlashArray alphaArray );

    public native void paletteMap( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, flash.FlashArray redArray, flash.FlashArray greenArray, flash.FlashArray blueArray );

    public native void paletteMap( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, flash.FlashArray redArray, flash.FlashArray greenArray );

    public native void paletteMap( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, flash.FlashArray redArray );

    public native void paletteMap( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint );

    public native flash.utils.ByteArray getPixels( flash.geom.Rectangle rect );

    public native int threshold( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, String operation, int threshold, int color, int mask, boolean copySource );

    public native int threshold( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, String operation, int threshold, int color, int mask );

    public native int threshold( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, String operation, int threshold, int color );

    public native int threshold( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, String operation, int threshold );

    public native int getPixel32( int x, int y );

    public native void lock( );

    public native void setPixels( flash.geom.Rectangle rect, flash.utils.ByteArray inputByteArray );

    public native void merge( flash.display.BitmapData sourceBitmapData, flash.geom.Rectangle sourceRect, flash.geom.Point destPoint, int redMultiplier, int greenMultiplier, int blueMultiplier, int alphaMultiplier );

    public native int[] getVector( flash.geom.Rectangle rect );

    public native double[][] histogram( flash.geom.Rectangle hRect );

    public native double[][] histogram( );
}
