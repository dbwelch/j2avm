package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;

import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * Runtime invisible annotations
 *
 * @author nickmain
 */
public class RuntimeInvisibleAnnotationsAttribute extends Annotations {
    public RuntimeInvisibleAnnotationsAttribute() {
        super( JVMAttribute.Name.RuntimeInvisibleAnnotations, false );
    }
    
    public static RuntimeInvisibleAnnotationsAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        RuntimeInvisibleAnnotationsAttribute attr = new RuntimeInvisibleAnnotationsAttribute();
        attr.parseAnnotations( pool, loader, in );
        return attr;
    }
}
