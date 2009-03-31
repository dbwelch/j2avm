//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class FileReferenceList extends flash.events.EventDispatcher {

    public  FileReferenceList( ) {}

    public native boolean browse( flash.FlashArray typeFilter );

    public native boolean browse( );

    @Getter
    public native flash.FlashArray getFileList( );
}
