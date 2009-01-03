package org.epistem.jvm.type;

/**
 * A method signature.
 *  
 * @author dmain
 */
public final class Signature {

	/** The method name */
	public final String name;
	
	/** The parameter types */
	public final ValueType[] paramTypes;
	
	private String toString;	
	
	public Signature( String name, ValueType...paramTypes ) {
		this.paramTypes = paramTypes;
		this.name       = name;		
	}

	@Override
	public boolean equals(Object obj) {
		if( obj == null ) return false;
		return toString().equals( obj.toString() );
	}

	@Override
	public int hashCode() {
	    return toString().hashCode();
	}

	@Override
	public String toString() {
	    if( toString != null ) return toString;
	    
		StringBuilder buff = new StringBuilder( name );
		buff.append( "(" );
		
		boolean first = true;
		for( ValueType vt : paramTypes ) {
			if( first ) first = false;
			else buff.append( "," );
			
			buff.append( vt.name );
		}
		
		buff.append( ")" );
		return toString = buff.toString();
	}
}
