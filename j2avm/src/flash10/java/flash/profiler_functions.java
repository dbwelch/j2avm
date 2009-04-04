//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class profiler_functions extends flash.FlashObject {
    
    private profiler_functions() {}

    @Function("flash.profiler.showRedrawRegions")
    public static native void showRedrawRegions( boolean on, int color );

    @Function("flash.profiler.showRedrawRegions")
    public static native void showRedrawRegions( boolean on );

    @Function("flash.profiler.profile")
    public static native void profile( boolean on );
}
