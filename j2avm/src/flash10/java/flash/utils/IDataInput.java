//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.utils;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public interface IDataInput {

    @Getter
    public int getObjectEncoding( );

    public int readUnsignedInt( );

    @Getter
    public int getBytesAvailable( );

    public int readShort( );

    public double readDouble( );

    public String readMultiByte( int length, String charSet );

    public double readFloat( );

    public int readUnsignedShort( );

    public boolean readBoolean( );

    public int readUnsignedByte( );

    public void readBytes( flash.utils.ByteArray bytes, int offset, int length );

    public void readBytes( flash.utils.ByteArray bytes, int offset );

    public void readBytes( flash.utils.ByteArray bytes );

    @Setter
    public void setEndian( String type );

    public String readUTF( );

    public int readInt( );

    public String readUTFBytes( int length );

    @Getter
    public String getEndian( );

    public Object readObject( );

    @Setter
    public void setObjectEncoding( int version );

    public int readByte( );
}
