//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class SimpleButton extends flash.display.InteractiveObject {

    public  SimpleButton( flash.display.DisplayObject upState, flash.display.DisplayObject overState, flash.display.DisplayObject downState, flash.display.DisplayObject hitTestState ) {}

    public  SimpleButton( flash.display.DisplayObject upState, flash.display.DisplayObject overState, flash.display.DisplayObject downState ) {}

    public  SimpleButton( flash.display.DisplayObject upState, flash.display.DisplayObject overState ) {}

    public  SimpleButton( flash.display.DisplayObject upState ) {}

    public  SimpleButton( ) {}

    @Getter
    public native boolean getEnabled( );

    @Setter
    public native void setEnabled( boolean value );

    @Getter
    public native flash.display.DisplayObject getHitTestState( );

    @Setter
    public native void setHitTestState( flash.display.DisplayObject value );

    @Setter
    public native void setUpState( flash.display.DisplayObject value );

    @Getter
    public native flash.display.DisplayObject getDownState( );

    @Setter
    public native void setSoundTransform( flash.media.SoundTransform sndTransform );

    @Getter
    public native flash.media.SoundTransform getSoundTransform( );

    @Getter
    public native flash.display.DisplayObject getUpState( );

    @Setter
    public native void setUseHandCursor( boolean value );

    @Setter
    public native void setOverState( flash.display.DisplayObject value );

    @Getter
    public native boolean getUseHandCursor( );

    @Getter
    public native boolean getTrackAsMenu( );

    @Getter
    public native flash.display.DisplayObject getOverState( );

    @Setter
    public native void setDownState( flash.display.DisplayObject value );

    @Setter
    public native void setTrackAsMenu( boolean value );
}
