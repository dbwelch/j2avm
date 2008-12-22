package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * Base for parameter annotations
 *
 * @author nickmain
 */
public abstract class ParameterAnnotationAttribute extends AnnotationAttribute {
    
    /** The list of parameter annotations - each is a map keyed by annotation class name */
    public List<Map<String,JavaAnnotation>> parameterAnnotations = new ArrayList<Map<String,JavaAnnotation>>();

    protected ParameterAnnotationAttribute( JVMAttribute.Name name, boolean isVisible ) {
        super( name, isVisible );
    }
    
    /** Parse the parameter annotations */
    protected void parseAnnotations( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        
        int paramCount = in.readUnsignedByte();
        
        for (int i = 0; i < paramCount; i++) {
            Map<String,JavaAnnotation> annotations = new HashMap<String, JavaAnnotation>();            
            parseAnnotations( annotations, pool, loader, in );
            parameterAnnotations.add( annotations );
        }
    }
    
    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.println( name + " {" );
        out.indent();
        
        for( Map<String,JavaAnnotation> annotations : parameterAnnotations ) {
            out.println( "parameter {" );
            out.indent();
            
            for( JavaAnnotation anno : annotations.values() ) {
                anno.dump( out );            
            }
            
            out.unindent();
            out.println( "}" );                    
        }
        
        out.unindent();
        out.println( "}" );        
    }
}
