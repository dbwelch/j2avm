//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.utils;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ObjectInput extends flash.FlashObject implements flash.utils.IDataInput {

    public  ObjectInput( ) {}

    public native int readUnsignedInt( );

    public native int readByte( );

    public native int readShort( );

    public native double readDouble( );

    public native boolean readBoolean( );

    public native int readUnsignedByte( );

    @Getter
    public native int getObjectEncoding( );

    public native Object readObject( );

    public native int readUnsignedShort( );

    @Getter
    public native String getEndian( );

    @Getter
    public native int getBytesAvailable( );

    @Setter
    public native void setObjectEncoding( int setObjectEncoding );

    public native String readMultiByte( int length, String charSet );

    public native double readFloat( );

    public native String readUTF( );

    @Setter
    public native void setEndian( String setEndian );

    public native int readInt( );

    public native String readUTFBytes( int length );

    public native void readBytes( flash.utils.ByteArray bytes, int offset, int length );

    public native void readBytes( flash.utils.ByteArray bytes, int offset );

    public native void readBytes( flash.utils.ByteArray bytes );
}
