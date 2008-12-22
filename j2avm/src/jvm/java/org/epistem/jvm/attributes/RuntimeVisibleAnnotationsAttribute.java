package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;

import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * Runtime visible annotations
 *
 * @author nickmain
 */
public class RuntimeVisibleAnnotationsAttribute extends Annotations {
    public RuntimeVisibleAnnotationsAttribute() {
        super( JVMAttribute.Name.RuntimeVisibleAnnotations, true );
    }
    
    public static RuntimeVisibleAnnotationsAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        RuntimeVisibleAnnotationsAttribute attr = new RuntimeVisibleAnnotationsAttribute();
        attr.parseAnnotations( pool, loader, in );
        return attr;
    }
}
