package org.epistem.jvm.type;


/**
 * Common base for types that can have values (all but void)
 *
 * @author nickmain
 */
public abstract class ValueType extends JVMType {

    private static class Unknown extends ValueType { Unknown() {super("UNKNOWN","U");} }
    public static final ValueType UNKNOWN = new Unknown();
    
    /*pkg*/ ValueType( String name, String abbreviation ) {
        super( name, abbreviation );
    }
    
    /**
     * Create a ValueType from a type name 
     */
    public static ValueType fromName( String name ) {
    	
    	if( name.equals( "void" ) ) {
    		throw new IllegalArgumentException( "Value type cannot be void" );
    	}
    	
        return (ValueType) JVMType.fromName( name );
    }
}
