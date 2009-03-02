package org.epistem.j2avm.util;

import org.epistem.jvm.type.*;

import com.anotherbigidea.flash.avm2.model.AVM2QName;
import com.anotherbigidea.flash.avm2.model.AVM2StandardName;

/**
 * Utilities for generating AVM2 names
 *
 * @author nickmain
 */
public class NameUtils {
    
    /**
     * Get the AVM2 QName corresponding to a Java type
     */
    public static AVM2QName qnameForJavaType( JVMType type ) {

        if( type == VoidType.VOID         ) return AVM2StandardName.TypeVoid.qname; 
        if( type == PrimitiveType.BYTE    ) return AVM2StandardName.TypeInt.qname;
        if( type == PrimitiveType.BOOLEAN ) return AVM2StandardName.TypeBoolean.qname;
        if( type == PrimitiveType.SHORT   ) return AVM2StandardName.TypeInt.qname;
        if( type == PrimitiveType.CHAR    ) return AVM2StandardName.TypeInt.qname;
        if( type == PrimitiveType.INT     ) return AVM2StandardName.TypeInt.qname;
        if( type == PrimitiveType.FLOAT   ) return AVM2StandardName.TypeNumber.qname;
        if( type == PrimitiveType.LONG    ) return AVM2StandardName.TypeInt.qname;
        if( type == PrimitiveType.DOUBLE  ) return AVM2StandardName.TypeNumber.qname;

        if( type instanceof ArrayType  ) {
            return AVM2StandardName.TypeArray.qname;
        }
        
        if( type instanceof ObjectType ) {
            return nameForJavaClass( (ObjectType) type );
        }
 
        return null;
    }
    
    /**
     * Get or generate the AVM2 name for the given Java class.
     * 
     * TODO: this would be the place to plug in some sort of strategy/factory
     *       for generating names
     * 
     * @param className the Java class name
     */
    public static final AVM2QName nameForJavaClass( ObjectType className ) {
        
//-The following is handled in FlashNativeClassTranslator:        
//        String name = ( className.name.startsWith( "flash.Flash" ) ) ?
//                          className.name.substring( 11 ) :
//                          className.name;
            
        String name = className.name;
        return new AVM2QName( name );
    }
    
    /**
     * Strip the "get", "is", "has", "set" prefix from an accessor method name and
     * return the target field name.
     * @param accessor the accessor method name
     * @return the field name
     */
    public static String nameFromAccessor( String accessor ) {
        
        String name;
        
        if     ( accessor.startsWith( "get" ) ) name = accessor.substring( 3 );
        else if( accessor.startsWith( "is"  ) ) name = accessor.substring( 2 );
        else if( accessor.startsWith( "has" ) ) name = accessor.substring( 3 );
        else if( accessor.startsWith( "set" ) ) name = accessor.substring( 3 );
        else name = accessor;
        
        name = name.substring( 0, 1 ).toLowerCase() + name.substring( 1 );
        return name;
    }
}
