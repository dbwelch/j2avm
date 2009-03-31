//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class FileReference extends flash.events.EventDispatcher {

    public  FileReference( ) {}

    public native void upload( flash.net.URLRequest request, String uploadDataFieldName, boolean testUpload );

    public native void upload( flash.net.URLRequest request, String uploadDataFieldName );

    public native void upload( flash.net.URLRequest request );

    public native void load( );

    @Getter
    public native int getSize( );

    @Getter
    public native String getType( );

    public native boolean browse( flash.FlashArray typeFilter );

    public native boolean browse( );

    @Getter
    public native String getName( );

    @Getter
    public native String getCreator( );

    @Getter
    public native flash.FlashDate getCreationDate( );

    public native void download( flash.net.URLRequest request, String defaultFileName );

    public native void download( flash.net.URLRequest request );

    @Getter
    public native flash.FlashDate getModificationDate( );

    @Getter
    public native flash.utils.ByteArray getData( );

    public native void cancel( );

    public native void save( Object data, String defaultFileName );

    public native void save( Object data );
}
