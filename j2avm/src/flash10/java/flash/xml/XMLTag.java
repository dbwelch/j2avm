//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.xml;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class XMLTag extends flash.FlashObject {

    public  XMLTag( ) {}

    @Getter
    public native String getValue( );

    @Setter
    public native void setValue( String v );

    @Setter
    public native void setType( int value );

    @Getter
    public native int getType( );

    @Setter
    public native void setEmpty( boolean value );

    @Setter
    public native void setAttrs( flash.FlashObject value );

    @Getter
    public native boolean getEmpty( );

    @Getter
    public native flash.FlashObject getAttrs( );
}
