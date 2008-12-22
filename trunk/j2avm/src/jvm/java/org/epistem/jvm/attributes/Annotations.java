package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * Base for annotation containers
 *
 * @author nickmain
 */
public abstract class Annotations extends AnnotationAttribute {

    /** The annotations - keyed by annotation class name */
    public final Map<String,JavaAnnotation> annotations = new HashMap<String,JavaAnnotation>();
    
    protected Annotations( JVMAttribute.Name name, boolean isVisible ) {
        super( name, isVisible );
    }
    
    /** Parse the annotations */
    protected void parseAnnotations( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        parseAnnotations( annotations, pool, loader, in );
    }
    
    /**
     * Dump for debug purposes
     */
    public void dump( IndentingPrintWriter out ) {
        
        out.println( name + " {" );
        out.indent();
        
        for( JavaAnnotation anno : annotations.values() ) {
            anno.dump( out );            
        }
        
        out.unindent();
        out.println( "}" );
    }
}
