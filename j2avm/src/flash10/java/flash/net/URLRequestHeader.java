//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class URLRequestHeader extends flash.FlashObject {

    public  URLRequestHeader( String name, String value ) {}

    public  URLRequestHeader( String name ) {}

    public  URLRequestHeader( ) {}

    @Getter
    public native String getValue( );

    @Setter
    public native void setValue( String value );

    @Getter
    public native String getName( );

    @Setter
    public native void setName( String name );
}
