//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.filters;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class DisplacementMapFilter extends flash.filters.BitmapFilter {

    public  DisplacementMapFilter( flash.display.BitmapData mapBitmap, flash.geom.Point mapPoint, int componentX, int componentY, double scaleX, double scaleY, String mode, int color, double alpha ) {}

    public  DisplacementMapFilter( flash.display.BitmapData mapBitmap, flash.geom.Point mapPoint, int componentX, int componentY, double scaleX, double scaleY, String mode, int color ) {}

    public  DisplacementMapFilter( flash.display.BitmapData mapBitmap, flash.geom.Point mapPoint, int componentX, int componentY, double scaleX, double scaleY, String mode ) {}

    public  DisplacementMapFilter( flash.display.BitmapData mapBitmap, flash.geom.Point mapPoint, int componentX, int componentY, double scaleX, double scaleY ) {}

    public  DisplacementMapFilter( flash.display.BitmapData mapBitmap, flash.geom.Point mapPoint, int componentX, int componentY, double scaleX ) {}

    public  DisplacementMapFilter( flash.display.BitmapData mapBitmap, flash.geom.Point mapPoint, int componentX, int componentY ) {}

    public  DisplacementMapFilter( flash.display.BitmapData mapBitmap, flash.geom.Point mapPoint, int componentX ) {}

    public  DisplacementMapFilter( flash.display.BitmapData mapBitmap, flash.geom.Point mapPoint ) {}

    public  DisplacementMapFilter( flash.display.BitmapData mapBitmap ) {}

    public  DisplacementMapFilter( ) {}

    @Getter
    public native int getComponentY( );

    @Override
    public native flash.filters.BitmapFilter clone( );

    @Getter
    public native double getAlpha( );

    @Setter
    public native void setMode( String setMode );

    @Setter
    public native void setMapPoint( flash.geom.Point setMapPoint );

    @Setter
    public native void setAlpha( double setAlpha );

    @Getter
    public native String getMode( );

    @Getter
    public native flash.display.BitmapData getMapBitmap( );

    @Setter
    public native void setColor( int setColor );

    @Getter
    public native double getScaleX( );

    @Getter
    public native double getScaleY( );

    @Getter
    public native int getColor( );

    @Getter
    public native flash.geom.Point getMapPoint( );

    @Setter
    public native void setComponentX( int setComponentX );

    @Setter
    public native void setComponentY( int setComponentY );

    @Getter
    public native int getComponentX( );

    @Setter
    public native void setScaleX( double setScaleX );

    @Setter
    public native void setMapBitmap( flash.display.BitmapData setMapBitmap );

    @Setter
    public native void setScaleY( double setScaleY );
}
