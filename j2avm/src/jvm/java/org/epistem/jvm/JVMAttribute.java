package org.epistem.jvm;

import java.util.Map;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.attributes.*;

/**
 * Base for class, field, method and code attributes
 *
 * @author nickmain
 */
public class JVMAttribute {

    /** The standard attribute names */
    public static enum Name {
        Code                                 (CodeAttribute.class),
        Exceptions                           (ExceptionsAttribute.class),
        ConstantValue                        (ConstantValueAttribute.class),
        Deprecated                           (DeprecatedAttribute.class),
        Synthetic                            (SyntheticAttribute.class),
        SourceFile                           (SourceFileAttribute.class),
        LineNumberTable                      (LineNumberTableAttribute.class),
        LocalVariableTable                   (LocalVariableTableAttribute.class),
        LocalVariableTypeTable               (LocalVariableTypeTableAttribute.class),
        AnnotationDefault                    (AnnotationDefaultAttribute.class),
        EnclosingMethod                      (EnclosingMethodAttribute.class),
        Signature                            (SignatureAttribute.class), 
        InnerClasses                         (InnerClassesAttribute.class), 
        RuntimeVisibleAnnotations            (RuntimeVisibleAnnotationsAttribute.class),
        RuntimeInvisibleAnnotations          (RuntimeInvisibleAnnotationsAttribute.class),
        RuntimeVisibleParameterAnnotations   (RuntimeVisibleParameterAnnotationsAttribute.class),
        RuntimeInvisibleParameterAnnotations (RuntimeInvisibleParameterAnnotationsAttribute.class);
        
        /** The class that holds the attribute type */
        public final Class<? extends JVMAttribute> attributeClass;
        
        private Name( Class<? extends JVMAttribute> attributeClass ) {
            this.attributeClass = attributeClass;
        }
        
        /**
         * Get the name for the given class
         */
        public static Name forClass( Class<? extends JVMAttribute> attributeClass ) {
            for( Name name : values() ) {
                if( name.attributeClass == attributeClass ) return name;   
            }
            return null;
        }
    }
    
    /** The attribute name */
    public final String name;
    
    /**
     * @param name the attribute name
     */
    public JVMAttribute( String name ) {
        this.name = name;
    }

    /** @see java.lang.Object#equals(java.lang.Object) */
    @Override
    public boolean equals(Object obj) {
        if( obj == null ) return false;
        if( !( obj instanceof JVMAttribute )) return false;
        return ((JVMAttribute) obj).name.equals( name );
    }

    /** @see java.lang.Object#hashCode() */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    /** Dump for debug purposes */
    public void dump( IndentingPrintWriter out ) {
        out.println( name );
    }

}
