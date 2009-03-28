//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class URLRequest extends flash.FlashObject {

    @Getter
    public native String getMethod( );

    @Setter
    public native void setMethod( String setMethod );

    @Getter
    public native String getDigest( );

    @Setter
    public native void setContentType( String setContentType );

    @Setter
    public native void setDigest( String setDigest );

    @Getter
    public native flash.FlashObject getData( );

    @Setter
    public native void setRequestHeaders( flash.FlashArray setRequestHeaders );

    @Getter
    public native String getUrl( );

    @Setter
    public native void setData( flash.FlashObject setData );

    @Getter
    public native flash.FlashArray getRequestHeaders( );

    @Getter
    public native String getContentType( );

    @Setter
    public native void setUrl( String setUrl );
}
