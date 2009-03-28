//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.utils;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ObjectOutput extends flash.FlashObject implements flash.utils.IDataOutput {

    public native void writeMultiByte( String value, String charSet );

    public native void writeUTFBytes( String value );

    public native void writeObject( Object object );

    public native void writeBoolean( boolean value );

    public native void writeBytes( flash.utils.ByteArray bytes, int offset, int length );

    public native void writeBytes( flash.utils.ByteArray bytes, int offset );

    public native void writeBytes( flash.utils.ByteArray bytes );

    public native void writeByte( int value );

    public native void writeUTF( String value );

    public native void writeDouble( double value );

    public native void writeUnsignedInt( int value );

    public native void writeShort( int value );

    public native void writeInt( int value );

    @Getter
    public native String getEndian( );

    @Setter
    public native void setObjectEncoding( int setObjectEncoding );

    @Getter
    public native int getObjectEncoding( );

    @Setter
    public native void setEndian( String setEndian );

    public native void writeFloat( double value );
}
