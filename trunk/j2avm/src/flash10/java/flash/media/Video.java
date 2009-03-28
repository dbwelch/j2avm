//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.media;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Video extends flash.display.DisplayObject {

    @Getter
    public native int getVideoHeight( );

    @Getter
    public native boolean getSmoothing( );

    @Getter
    public native int getDeblocking( );

    @Setter
    public native void setSmoothing( boolean setSmoothing );

    public native void attachCamera( flash.media.Camera camera );

    @Getter
    public native int getVideoWidth( );

    @Setter
    public native void setDeblocking( int setDeblocking );

    public native void clear( );

    public native void attachNetStream( flash.net.NetStream netStream );
}
