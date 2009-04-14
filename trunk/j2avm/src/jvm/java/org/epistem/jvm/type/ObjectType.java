package org.epistem.jvm.type;

/**
 * An Object type, but not arrays.
 *
 * @author nickmain
 */
public class ObjectType extends ObjectOrArrayType {

    public static final ObjectType OBJECT         = new ObjectType( "java.lang.Object" );
    public static final ObjectType STRING         = new ObjectType( "java.lang.String" );
    public static final ObjectType CLASS          = new ObjectType( "java.lang.Class" );
    public static final ObjectType STRING_BUILDER = new ObjectType( "java.lang.StringBuilder" );
    public static final ObjectType THROWABLE      = new ObjectType( "java.lang.Throwable" );
    
    /**
     * The package name or empty string
     */
    public final String packageName;
    
    /**
     * The unqualified name of the class
     */
    public final String simpleName;
    
    /**
     * @param className the fully qualified name of the class
     */
    public ObjectType( String className ) {
        super( className, abbrev( className ) );
        
        int period = className.lastIndexOf( '.' );
        if( period < 0 ) {
            packageName = "";
            simpleName  = className;
            return;
        }
        
        packageName = className.substring( 0, period );
        simpleName  = className.substring( period + 1 );
    }   
    
    private static String abbrev( String name ) {
    	if( name.equals( "java.lang.String"    ) ) return "$";
    	if( name.equals( "java.lang.Throwable" ) ) return "T";
        if( name.equals( "java.lang.Exception" ) ) return "E";

    	return "O";
    }
    
    public static ObjectType fromName( String name ) {
        ValueType vt = ValueType.fromName( name );
        if( vt instanceof ObjectType ) {
            return (ObjectType) vt;
        }
        
        throw new RuntimeException( "Not an Object or Array type: " + name );        
    }
}
