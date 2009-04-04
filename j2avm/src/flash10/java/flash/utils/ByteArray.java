//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.utils;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class ByteArray extends flash.FlashObject implements flash.utils.IDataInput,flash.utils.IDataOutput {

    public  ByteArray( ) {}

    @Getter
    public static final native int getDefaultObjectEncoding( );

    @Setter
    public static final native void setDefaultObjectEncoding( int version );

    public native void writeUTFBytes( String value );

    public native int readShort( );

    public native void writeByte( int value );

    public native void writeDouble( double value );

    public native int readUnsignedShort( );

    public native double readDouble( );

    public native void writeInt( int value );

    @Getter
    public native String getEndian( );

    @Getter
    public native int getBytesAvailable( );

    public native Object readObject( );

    public native void deflate( );

    @Getter
    public native int getPosition( );

    public native boolean readBoolean( );

    public native void inflate( );

    @Setter
    public native void setEndian( String type );

    public native String readUTF( );

    public native String readUTFBytes( int length );

    public native void writeFloat( double value );

    public native void writeMultiByte( String value, String charSet );

    public native int readUnsignedInt( );

    public native int readByte( );

    @Getter
    public native int getObjectEncoding( );

    public native void writeBytes( flash.utils.ByteArray bytes, int offset, int length );

    public native void writeBytes( flash.utils.ByteArray bytes, int offset );

    public native void writeBytes( flash.utils.ByteArray bytes );

    public native void clear( );

    public native void writeUTF( String value );

    public native void writeBoolean( boolean value );

    @Setter
    public native void setPosition( int offset );

    public native int readUnsignedByte( );

    public native void writeUnsignedInt( int value );

    public native void writeShort( int value );

    @Getter
    public native int getLength( );

    public native void compress( );

    public native String toString( );

    @Setter
    public native void setLength( int value );

    @Setter
    public native void setObjectEncoding( int version );

    public native double readFloat( );

    public native int readInt( );

    public native String readMultiByte( int length, String charSet );

    public native void uncompress( );

    public native void readBytes( flash.utils.ByteArray bytes, int offset, int length );

    public native void readBytes( flash.utils.ByteArray bytes, int offset );

    public native void readBytes( flash.utils.ByteArray bytes );

    public native void writeObject( Object object );
}
