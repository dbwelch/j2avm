//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Socket extends flash.events.EventDispatcher implements flash.utils.IDataInput,flash.utils.IDataOutput {

    public  Socket( String host, int port ) {}

    public  Socket( String host ) {}

    public  Socket( ) {}

    public native void writeUTFBytes( String value );

    public native void flush( );

    public native void writeObject( Object object );

    public native void writeByte( int value );

    @Getter
    public native boolean getConnected( );

    public native int readShort( );

    public native int readUnsignedShort( );

    public native double readDouble( );

    public native void writeInt( int value );

    @Getter
    public native String getEndian( );

    @Setter
    public native void setObjectEncoding( int version );

    @Getter
    public native int getBytesAvailable( );

    public native void writeDouble( double value );

    public native Object readObject( );

    public native String readUTF( );

    @Setter
    public native void setEndian( String type );

    public native boolean readBoolean( );

    public native String readUTFBytes( int length );

    public native void writeFloat( double value );

    @Setter
    public native void setTimeout( int value );

    public native int readByte( );

    public native void writeUTF( String value );

    public native void writeBoolean( boolean value );

    @Getter
    public native int getObjectEncoding( );

    public native int readUnsignedInt( );

    public native void writeBytes( flash.utils.ByteArray bytes, int offset, int length );

    public native void writeBytes( flash.utils.ByteArray bytes, int offset );

    public native void writeBytes( flash.utils.ByteArray bytes );

    public native void writeMultiByte( String value, String charSet );

    public native int readUnsignedByte( );

    @Getter
    public native int getTimeout( );

    public native void writeUnsignedInt( int value );

    public native void writeShort( int value );

    public native double readFloat( );

    public native void connect( String host, int port );

    public native String readMultiByte( int length, String charSet );

    public native void readBytes( flash.utils.ByteArray bytes, int offset, int length );

    public native void readBytes( flash.utils.ByteArray bytes, int offset );

    public native void readBytes( flash.utils.ByteArray bytes );

    public native void close( );

    public native int readInt( );
}
