//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Shader extends flash.FlashObject {

    public  Shader( flash.utils.ByteArray code ) {}

    public  Shader( ) {}

    @Setter
    public native void setByteCode( flash.utils.ByteArray code );

    @Setter
    public native void setData( flash.display.ShaderData p );

    @Getter
    public native String getPrecisionHint( );

    @Getter
    public native flash.display.ShaderData getData( );

    @Setter
    public native void setPrecisionHint( String p );
}
