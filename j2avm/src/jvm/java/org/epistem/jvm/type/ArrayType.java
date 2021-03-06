package org.epistem.jvm.type;

/**
 * An array type.
 * 
 * An array should not have an array element type.
 *
 * @author nickmain
 */
public final class ArrayType extends ObjectOrArrayType {

	/** The array suffix */
	public final static String SUFFIX = "[]";
	
    /** The type of the array elements */
    public final ValueType elementType;
    
    /** The number of dimensions */
    public final int dimensionCount;
    
    /**
     * @param type the element type
     * @param dimCount the number of dimensions
     */
    public ArrayType( ValueType type, int dimCount ) {
        this( makeName( type.name, dimCount ), type, dimCount );
        assert ! (type instanceof ArrayType );
    }
    
    private ArrayType( String name, ValueType type, int dimCount ) {
    	super( name, "A" );
        elementType    = type;
        dimensionCount = dimCount;
    }
    
    private static String makeName( String name, int dimCount ) {
        while( dimCount-- > 0 ) name += "[]";
        return name;
    }
    
    /**
     * Get the type of the first dimension - the type that is accessed via
     * a single index
     */
    public ValueType firstDimType() {
    	if( dimensionCount == 1 ) return elementType;
    	
    	return new ArrayType( elementType, dimensionCount - 1 );
    }
    
    /** Whether a type name is an array type */
    public static boolean isArrayTypeName( String name ) {
    	return name.endsWith( SUFFIX );
    }
    
    /**
     * Create an ArrayType from a type name 
     */
    public static ArrayType fromName( String name ) {
    
    	if( ! isArrayTypeName( name )) {
    		throw new IllegalArgumentException( "Array type name must end with " + SUFFIX );
    	}
    	
    	String elemName = name;
    	int dimCount = 0;
    	while( elemName.endsWith( ArrayType.SUFFIX )) {
    		dimCount++;
    		elemName = elemName.substring( 0, elemName.length() - ArrayType.SUFFIX.length() );
    	}
    	
		JVMType elementType = JVMType.fromName( elemName );
		if( elementType instanceof ValueType ) {
			return new ArrayType( name, ((ValueType) elementType), dimCount );
		} else {
			throw new IllegalArgumentException( "void array is illegal" );
		}
    }
}
