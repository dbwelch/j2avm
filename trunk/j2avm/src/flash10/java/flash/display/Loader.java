//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Loader extends flash.display.DisplayObjectContainer {

    public  Loader( ) {}

    @Getter
    public native flash.display.LoaderInfo getContentLoaderInfo( );

    public native void load( flash.net.URLRequest request, flash.system.LoaderContext context );

    public native void load( flash.net.URLRequest request );

    @Override
    public native flash.display.DisplayObject removeChild( flash.display.DisplayObject child );

    public native void loadBytes( flash.utils.ByteArray bytes, flash.system.LoaderContext context );

    public native void loadBytes( flash.utils.ByteArray bytes );

    @Override
    public native flash.display.DisplayObject addChildAt( flash.display.DisplayObject child, int index );

    @Override
    public native void setChildIndex( flash.display.DisplayObject child, int index );

    @Override
    public native flash.display.DisplayObject removeChildAt( int index );

    public native void close( );

    @Getter
    public native flash.display.DisplayObject getContent( );

    public native void unloadAndStop( boolean gc );

    public native void unloadAndStop( );

    @Override
    public native flash.display.DisplayObject addChild( flash.display.DisplayObject child );

    public native void unload( );
}
