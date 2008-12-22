package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * The deprecated attribute
 *
 * @author nickmain
 */
public class DeprecatedAttribute extends JVMAttribute {
    
    public DeprecatedAttribute() {
        super( JVMAttribute.Name.Deprecated.name() );
    }
    
    public static DeprecatedAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        return new DeprecatedAttribute();
    }

    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.println( name );
    }
}
