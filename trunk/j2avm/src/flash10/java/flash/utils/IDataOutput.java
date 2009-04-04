//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.utils;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public interface IDataOutput {

    public void writeMultiByte( String value, String charSet );

    public void writeUTFBytes( String value );

    public void writeShort( int value );

    public void writeByte( int value );

    public void writeUTF( String value );

    public void writeBoolean( boolean value );

    @Getter
    public String getEndian( );

    @Setter
    public void setObjectEncoding( int version );

    public void writeBytes( flash.utils.ByteArray bytes, int offset, int length );

    public void writeBytes( flash.utils.ByteArray bytes, int offset );

    public void writeBytes( flash.utils.ByteArray bytes );

    public void writeInt( int value );

    @Getter
    public int getObjectEncoding( );

    @Setter
    public void setEndian( String type );

    public void writeDouble( double value );

    public void writeUnsignedInt( int value );

    public void writeFloat( double value );

    public void writeObject( Object object );
}
