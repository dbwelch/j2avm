//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.printing;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class PrintJob extends flash.events.EventDispatcher {

    public  PrintJob( ) {}

    @Getter
    public native String getOrientation( );

    @Getter
    public native int getPaperHeight( );

    @Getter
    public native int getPageHeight( );

    @Getter
    public native int getPageWidth( );

    public native boolean start( );

    @Getter
    public native int getPaperWidth( );

    public native void addPage( flash.display.Sprite sprite, flash.geom.Rectangle printArea, flash.printing.PrintJobOptions options, int frameNum );

    public native void addPage( flash.display.Sprite sprite, flash.geom.Rectangle printArea, flash.printing.PrintJobOptions options );

    public native void addPage( flash.display.Sprite sprite, flash.geom.Rectangle printArea );

    public native void addPage( flash.display.Sprite sprite );

    public native void send( );
}
