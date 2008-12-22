package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;

import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * Runtime invisible parameter annotations
 *
 * @author nickmain
 */
public class RuntimeInvisibleParameterAnnotationsAttribute extends ParameterAnnotationAttribute {
    public RuntimeInvisibleParameterAnnotationsAttribute() {
        super( JVMAttribute.Name.RuntimeInvisibleParameterAnnotations, false );
    }
    
    public static RuntimeInvisibleParameterAnnotationsAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        RuntimeInvisibleParameterAnnotationsAttribute attr = new RuntimeInvisibleParameterAnnotationsAttribute();
        attr.parseAnnotations( pool, loader, in );
        return attr;
    }
}
