package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;

import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * Runtime visible parameter annotations
 *
 * @author nickmain
 */
public class RuntimeVisibleParameterAnnotationsAttribute extends ParameterAnnotationAttribute {
    public RuntimeVisibleParameterAnnotationsAttribute() {
        super( JVMAttribute.Name.RuntimeVisibleParameterAnnotations, true );
    }
    
    public static RuntimeVisibleParameterAnnotationsAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        RuntimeVisibleParameterAnnotationsAttribute attr = new RuntimeVisibleParameterAnnotationsAttribute();
        attr.parseAnnotations( pool, loader, in );
        return attr;
    }
}
