package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * The synthetic attribute
 *
 * @author nickmain
 */
public class SyntheticAttribute extends JVMAttribute {
    
    public SyntheticAttribute() {
        super( JVMAttribute.Name.Synthetic.name() );
    }
    
    public static SyntheticAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        return new SyntheticAttribute();
    }
    
    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.println( name );
    }
}
