//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class GraphicsStroke extends flash.FlashObject implements flash.display.IGraphicsData,flash.display.IGraphicsStroke {

    @Getter
    public native String getCaps( );

    @Setter
    public native void setCaps( String setCaps );

    @Getter
    public native flash.display.IGraphicsFill getFill( );

    @Setter
    public native void setFill( flash.display.IGraphicsFill setFill );

    @Getter
    public native String getJoints( );

    @Getter
    public native String getScaleMode( );

    @Setter
    public native void setJoints( String setJoints );

    @Getter
    public native double getThickness( );

    @Setter
    public native void setThickness( double setThickness );

    @Getter
    public native boolean getPixelHinting( );

    @Setter
    public native void setPixelHinting( boolean setPixelHinting );

    @Getter
    public native double getMiterLimit( );

    @Setter
    public native void setMiterLimit( double setMiterLimit );

    @Setter
    public native void setScaleMode( String setScaleMode );
}
