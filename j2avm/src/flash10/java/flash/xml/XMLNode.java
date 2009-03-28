//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.xml;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class XMLNode extends flash.FlashObject {

    @Getter
    public native flash.xml.XMLNode getPreviousSibling( );

    @Setter
    public native void setPreviousSibling( flash.xml.XMLNode setPreviousSibling );

    @Getter
    public native flash.xml.XMLNode getParentNode( );

    @Setter
    public native void setParentNode( flash.xml.XMLNode setParentNode );

    @Getter
    public native flash.xml.XMLNode getNextSibling( );

    @Setter
    public native void setNextSibling( flash.xml.XMLNode setNextSibling );

    @Getter
    public native String getNamespaceURI( );

    @Getter
    public native String getPrefix( );

    @Getter
    public native flash.xml.XMLNode getFirstChild( );

    @Setter
    public native void setFirstChild( flash.xml.XMLNode setFirstChild );

    @Getter
    public native String getNodeValue( );

    @Setter
    public native void setNodeValue( String setNodeValue );

    @Getter
    public native flash.FlashObject getAttributes( );

    @Getter
    public native int getNodeType( );

    @Setter
    public native void setNodeType( int setNodeType );

    public native void appendChild( flash.xml.XMLNode node );

    @Getter
    public native flash.xml.XMLNode getLastChild( );

    @Setter
    public native void setLastChild( flash.xml.XMLNode setLastChild );

    public native void insertBefore( flash.xml.XMLNode node, flash.xml.XMLNode before );

    public native String getNamespaceForPrefix( String prefix );

    @Getter
    public native String getNodeName( );

    @Setter
    public native void setNodeName( String setNodeName );

    @Setter
    public native void setAttributes( flash.FlashObject setAttributes );

    public native boolean hasChildNodes( );

    @Getter
    public native flash.FlashArray getChildNodes( );

    @Getter
    public native String getLocalName( );

    public native String getPrefixForNamespace( String ns );

    public native String toString( );

    public native void removeNode( );

    public native flash.xml.XMLNode cloneNode( boolean deep );
}
