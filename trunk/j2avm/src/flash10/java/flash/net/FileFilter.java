//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class FileFilter extends flash.FlashObject {

    public  FileFilter( String description, String extension, String macType ) {}

    public  FileFilter( String description, String extension ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected FileFilter() {}

    @Setter
    public native void setMacType( String value );

    @Setter
    public native void setDescription( String value );

    @Getter
    public native String getMacType( );

    @Getter
    public native String getDescription( );

    @Setter
    public native void setExtension( String value );

    @Getter
    public native String getExtension( );
}
