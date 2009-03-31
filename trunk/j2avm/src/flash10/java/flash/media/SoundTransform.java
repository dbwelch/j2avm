//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.media;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class SoundTransform extends flash.FlashObject {

    public  SoundTransform( double vol, double panning ) {}

    public  SoundTransform( double vol ) {}

    public  SoundTransform( ) {}

    @Setter
    public native void setPan( double setPan );

    @Getter
    public native double getRightToRight( );

    @Getter
    public native double getVolume( );

    @Getter
    public native double getLeftToLeft( );

    @Setter
    public native void setRightToRight( double setRightToRight );

    @Setter
    public native void setLeftToLeft( double setLeftToLeft );

    @Setter
    public native void setLeftToRight( double setLeftToRight );

    @Getter
    public native double getLeftToRight( );

    @Setter
    public native void setVolume( double setVolume );

    @Setter
    public native void setRightToLeft( double setRightToLeft );

    @Getter
    public native double getPan( );

    @Getter
    public native double getRightToLeft( );
}
