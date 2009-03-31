//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class FlashXMLList extends flash.FlashObject {

    public  FlashXMLList( Object value ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected FlashXMLList() {}

    public static final Object length = 1;

    @Override
    public native boolean hasOwnProperty( Object P );

    public native Object insertChildBefore( Object child1, Object child2 );

    public native flash.FlashArray namespaceDeclarations( );

    @Override
    public native boolean propertyIsEnumerable( Object P );

    public native flash.FlashXML setChildren( Object value );

    public native flash.FlashObject name( );

    public native flash.FlashXML replace( Object propertyName, Object value );

    public native flash.FlashXMLList normalize( );

    public native flash.FlashArray inScopeNamespaces( );

    public native String toXMLString( );

    public native flash.FlashXMLList descendants( Object name );

    public native flash.FlashXMLList descendants( );

    public native flash.FlashXMLList attributes( );

    public native flash.FlashXMLList processingInstructions( Object name );

    public native flash.FlashXMLList processingInstructions( );

    public native void setNamespace( Object ns );

    public native void setLocalName( Object name );

    public native Object namespace( Object prefix );

    public native Object namespace( );

    public native flash.FlashXMLList attribute( Object arg );

    public native int childIndex( );

    public native boolean contains( Object value );

    public native flash.FlashXML appendChild( Object child );

    public native boolean hasComplexContent( );

    public native flash.FlashObject localName( );

    public native int length( );

    public native flash.FlashXMLList valueOf( );

    public native Object parent( );

    public native flash.FlashXMLList child( Object propertyName );

    public native String toString( );

    public native boolean hasSimpleContent( );

    public native flash.FlashXML prependChild( Object value );

    public native void setName( Object name );

    public native flash.FlashXMLList text( );

    public native flash.FlashXMLList copy( );

    public native String nodeKind( );

    public native flash.FlashXMLList elements( Object name );

    public native flash.FlashXMLList elements( );

    public native Object insertChildAfter( Object child1, Object child2 );

    public native flash.FlashXML addNamespace( Object ns );

    public native flash.FlashXMLList comments( );

    public native flash.FlashXML removeNamespace( Object ns );

    public native flash.FlashXMLList children( );
}
