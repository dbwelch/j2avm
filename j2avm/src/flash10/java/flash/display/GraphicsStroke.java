//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GraphicsStroke extends flash.FlashObject implements flash.display.IGraphicsStroke,flash.display.IGraphicsData {

    public  GraphicsStroke( double thickness, boolean pixelHinting, String scaleMode, String caps, String joints, double miterLimit, flash.display.IGraphicsFill fill ) {}

    public  GraphicsStroke( double thickness, boolean pixelHinting, String scaleMode, String caps, String joints, double miterLimit ) {}

    public  GraphicsStroke( double thickness, boolean pixelHinting, String scaleMode, String caps, String joints ) {}

    public  GraphicsStroke( double thickness, boolean pixelHinting, String scaleMode, String caps ) {}

    public  GraphicsStroke( double thickness, boolean pixelHinting, String scaleMode ) {}

    public  GraphicsStroke( double thickness, boolean pixelHinting ) {}

    public  GraphicsStroke( double thickness ) {}

    public  GraphicsStroke( ) {}

    @Getter
    public native String getCaps( );

    @Setter
    public native void setCaps( String value );

    @Getter
    public native flash.display.IGraphicsFill getFill( );

    @Setter
    public native void setFill( flash.display.IGraphicsFill fill );

    @Getter
    public native String getJoints( );

    @Getter
    public native String getScaleMode( );

    @Setter
    public native void setJoints( String value );

    @Getter
    public native double getThickness( );

    @Setter
    public native void setThickness( double thickness );

    @Getter
    public native boolean getPixelHinting( );

    @Setter
    public native void setPixelHinting( boolean pixelHinting );

    @Getter
    public native double getMiterLimit( );

    @Setter
    public native void setMiterLimit( double miterLimit );

    @Setter
    public native void setScaleMode( String value );
}
