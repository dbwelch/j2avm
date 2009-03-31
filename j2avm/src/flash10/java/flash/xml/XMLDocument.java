//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.xml;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class XMLDocument extends flash.xml.XMLNode {

    public  XMLDocument( String source ) {}

    public  XMLDocument( ) {}

    public native flash.xml.XMLNode createElement( String name );

    @Getter
    public native flash.FlashObject getIdMap( );

    @Setter
    public native void setIdMap( flash.FlashObject setIdMap );

    public native void parseXML( String source );

    @Getter
    public native flash.FlashObject getXmlDecl( );

    @Setter
    public native void setXmlDecl( flash.FlashObject setXmlDecl );

    public native flash.xml.XMLNode createTextNode( String text );

    @Override
    public native String toString( );

    @Getter
    public native boolean getIgnoreWhite( );

    @Setter
    public native void setIgnoreWhite( boolean setIgnoreWhite );

    @Getter
    public native flash.FlashObject getDocTypeDecl( );

    @Setter
    public native void setDocTypeDecl( flash.FlashObject setDocTypeDecl );
}
