//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class Scene extends flash.FlashObject {

    public  Scene( String name, flash.FlashArray labels, int numFrames ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected Scene() {}

    @Getter
    public native int getNumFrames( );

    @Getter
    public native String getName( );

    @Getter
    public native flash.FlashArray getLabels( );
}
