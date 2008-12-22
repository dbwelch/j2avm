package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * The default values for annotation methods
 *
 * @author nickmain
 */
public class AnnotationDefaultAttribute extends JVMAttribute {

    /** The default value */
    public final JavaAnnotation.Value value;
    
    /**
     * @param value the default value
     */
    public AnnotationDefaultAttribute( JavaAnnotation.Value value ) {
        super( JVMAttribute.Name.AnnotationDefault.name() );
        this.value = value;
    }
    
    public static AnnotationDefaultAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        
        return new AnnotationDefaultAttribute( AnnotationAttribute.parseValue( pool, loader, in ));
    }
    
    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.print( name + " = " );
        value.dump( out );
        out.println();
    }    
}
