//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class URLStream extends flash.events.EventDispatcher implements flash.utils.IDataInput {

    public  URLStream( ) {}

    public native int readUnsignedInt( );

    public native double readDouble( );

    public native double readFloat( );

    public native boolean readBoolean( );

    public native int readShort( );

    @Getter
    public native boolean getConnected( );

    public native int readUnsignedShort( );

    public native int readUnsignedByte( );

    @Getter
    public native int getObjectEncoding( );

    public native Object readObject( );

    @Getter
    public native String getEndian( );

    @Getter
    public native int getBytesAvailable( );

    @Setter
    public native void setObjectEncoding( int setObjectEncoding );

    public native void load( flash.net.URLRequest request );

    public native String readMultiByte( int length, String charSet );

    public native String readUTF( );

    @Setter
    public native void setEndian( String setEndian );

    public native int readInt( );

    public native String readUTFBytes( int length );

    public native void readBytes( flash.utils.ByteArray bytes, int offset, int length );

    public native void readBytes( flash.utils.ByteArray bytes, int offset );

    public native void readBytes( flash.utils.ByteArray bytes );

    public native int readByte( );

    public native void close( );
}
