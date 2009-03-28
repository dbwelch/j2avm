//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class FlashXML extends flash.FlashObject {

    public static final native flash.FlashObject settings( );

    @Setter
    public static final native void setPrettyIndent( int setPrettyIndent );

    public static final native void setSettings( flash.FlashObject o );

    public static final native void setSettings( );

    @Getter
    public static final native boolean getIgnoreComments( );

    @Getter
    public static final native int getPrettyIndent( );

    @Getter
    public static final native boolean getIgnoreProcessingInstructions( );

    @Getter
    public static final native boolean getPrettyPrinting( );

    public static final Object length = 1;

    @Getter
    public static final native boolean getIgnoreWhitespace( );

    @Setter
    public static final native void setIgnoreComments( boolean setIgnoreComments );

    @Setter
    public static final native void setIgnoreProcessingInstructions( boolean setIgnoreProcessingInstructions );

    @Setter
    public static final native void setPrettyPrinting( boolean setPrettyPrinting );

    public static final native flash.FlashObject defaultSettings( );

    @Setter
    public static final native void setIgnoreWhitespace( boolean setIgnoreWhitespace );

    @Override
    public native boolean hasOwnProperty( Object P );

    public native Object insertChildBefore( Object child1, Object child2 );

    public native flash.FlashXML replace( Object propertyName, Object value );

    public native Object setNotification( flash.FlashFunction f );

    public native String toXMLString( );

    @Override
    public native boolean propertyIsEnumerable( Object P );

    public native flash.FlashXML setChildren( Object value );

    public native flash.FlashObject name( );

    public native flash.FlashXML normalize( );

    public native flash.FlashArray inScopeNamespaces( );

    public native void setLocalName( Object name );

    public native flash.FlashObject localName( );

    public native flash.FlashXMLList attributes( );

    public native flash.FlashXMLList processingInstructions( Object name );

    public native flash.FlashXMLList processingInstructions( );

    public native void setNamespace( Object ns );

    public native Object namespace( Object prefix );

    public native Object namespace( );

    public native flash.FlashXMLList child( Object propertyName );

    public native int childIndex( );

    public native boolean contains( Object value );

    public native flash.FlashXML appendChild( Object child );

    public native boolean hasComplexContent( );

    public native flash.FlashXMLList descendants( Object name );

    public native flash.FlashXMLList descendants( );

    public native int length( );

    public native flash.FlashXML valueOf( );

    public native Object parent( );

    public native flash.FlashXMLList attribute( Object arg );

    public native String toString( );

    public native boolean hasSimpleContent( );

    public native flash.FlashXML prependChild( Object value );

    public native void setName( Object name );

    public native flash.FlashFunction notification( );

    public native flash.FlashXMLList comments( );

    public native flash.FlashXML copy( );

    public native String nodeKind( );

    public native flash.FlashXMLList elements( Object name );

    public native flash.FlashXMLList elements( );

    public native Object insertChildAfter( Object child1, Object child2 );

    public native flash.FlashXML addNamespace( Object ns );

    public native flash.FlashArray namespaceDeclarations( );

    public native flash.FlashXMLList text( );

    public native flash.FlashXML removeNamespace( Object ns );

    public native flash.FlashXMLList children( );
}
