package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;

/**
 * The LineNumberTable attribute
 *
 * @author nickmain
 */
public class LineNumberTableAttribute extends JVMAttribute {
    
    
    
    /** The offsets at which line numbers start */
    public final int[] offsets;
    
    /** The line numbers corresponding to the offsets */
    public final int[] lineNumbers;
    
    public LineNumberTableAttribute( int[] offsets, int[] lineNumbers ) {
        super( JVMAttribute.Name.LineNumberTable.name() );
        
        this.lineNumbers = lineNumbers;
        this.offsets     = offsets;
    }
    
    public static LineNumberTableAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        
        int count = in.readUnsignedShort();
        int[] offsets     = new int[ count ];
        int[] lineNumbers = new int[ count ];
        
        for (int i = 0; i < lineNumbers.length; i++) {
            offsets    [i]  = in.readUnsignedShort();
            lineNumbers[i]  = in.readUnsignedShort();
        }
        
        return new LineNumberTableAttribute( offsets, lineNumbers );
    }
    
    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.println( name + " {" );
        out.indent();
        
        for (int i = 0; i < offsets.length; i++) {
            out.println( offsets[i] + ": line " + lineNumbers[i] );
        }
        
        out.unindent();
        out.println( "}" );
    }
}
