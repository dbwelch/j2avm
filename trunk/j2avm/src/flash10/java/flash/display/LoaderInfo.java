//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class LoaderInfo extends flash.events.EventDispatcher {

    public  LoaderInfo( ) {}

    public static final native flash.display.LoaderInfo getLoaderInfoByDefinition( flash.FlashObject object );

    @Override
    public native boolean dispatchEvent( flash.events.Event event );

    @Getter
    public native int getWidth( );

    @Getter
    public native int getHeight( );

    @Getter
    public native boolean getParentAllowsChild( );

    @Getter
    public native flash.FlashObject getParameters( );

    @Getter
    public native flash.utils.ByteArray getBytes( );

    @Getter
    public native double getFrameRate( );

    @Getter
    public native String getUrl( );

    @Getter
    public native int getBytesLoaded( );

    @Getter
    public native boolean getSameDomain( );

    @Getter
    public native String getContentType( );

    @Getter
    public native flash.system.ApplicationDomain getApplicationDomain( );

    @Getter
    public native int getSwfVersion( );

    @Getter
    public native int getActionScriptVersion( );

    @Getter
    public native int getBytesTotal( );

    @Getter
    public native flash.display.Loader getLoader( );

    @Getter
    public native flash.display.DisplayObject getContent( );

    @Getter
    public native String getLoaderURL( );

    @Getter
    public native flash.events.EventDispatcher getSharedEvents( );

    @Getter
    public native boolean getChildAllowsParent( );
}
