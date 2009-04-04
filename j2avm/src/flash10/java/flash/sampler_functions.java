//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class sampler_functions extends flash.FlashObject {
    
    private sampler_functions() {}

    @Function("flash.sampler.pauseSampling")
    public static native void pauseSampling( );

    @Function("flash.sampler.stopSampling")
    public static native void stopSampling( );

    @Function("flash.sampler.getMemberNames")
    public static native flash.FlashObject getMemberNames( flash.FlashObject o, boolean instanceNames );

    @Function("flash.sampler.getMemberNames")
    public static native flash.FlashObject getMemberNames( flash.FlashObject o );

    @Function("flash.sampler.getGetterInvocationCount")
    public static native double getGetterInvocationCount( flash.FlashObject obj, flash.FlashQName qname );

    @Function("flash.sampler.getInvocationCount")
    public static native double getInvocationCount( flash.FlashObject obj, flash.FlashQName qname );

    @Function("flash.sampler.getSetterInvocationCount")
    public static native double getSetterInvocationCount( flash.FlashObject obj, flash.FlashQName qname );

    @Function("flash.sampler.isGetterSetter")
    public static native boolean isGetterSetter( flash.FlashObject obj, flash.FlashQName qname );

    @Function("flash.sampler.getSamples")
    public static native flash.FlashObject getSamples( );

    @Function("flash.sampler.getSampleCount")
    public static native double getSampleCount( );

    @Function("flash.sampler.startSampling")
    public static native void startSampling( );

    @Function("flash.sampler.getSize")
    public static native double getSize( Object o );

    @Function("flash.sampler.clearSamples")
    public static native void clearSamples( );
}
