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
public class SourceFileAttribute extends JVMAttribute {
    
    /** The source file name */
    public final String filename;
    
    public SourceFileAttribute( String filename ) {
        super( JVMAttribute.Name.SourceFile.name() );
        this.filename = filename;
    }
    
    public static SourceFileAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {        
        int index = in.readUnsignedShort();
        return new SourceFileAttribute( pool.getUTF8Value( index ));
    }
    
    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.println( name + " = " + filename );
    }
}
