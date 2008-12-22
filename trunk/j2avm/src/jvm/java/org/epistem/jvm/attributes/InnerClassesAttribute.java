package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.attributes.InnerClass.InnerClassFlag;
import org.epistem.jvm.io.ConstantPool;
import org.epistem.jvm.type.ObjectType;
import org.epistem.util.FlagIO;

/**
 * The inner-classes attribute
 *
 * @author nickmain
 */
public class InnerClassesAttribute extends JVMAttribute {

    /**
     * Immutable collection of inner classes
     */
    public final Collection<InnerClass> innerClasses;
    
    public InnerClassesAttribute( InnerClass...innerClasses ) {
        super( JVMAttribute.Name.InnerClasses.name() );
        this.innerClasses = Collections.unmodifiableCollection( Arrays.asList( innerClasses ) );
    }
    
    public static InnerClassesAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        
        int count = in.readUnsignedShort();
        
        InnerClass[] inners = new InnerClass[ count ];
        for (int i = 0; i < inners.length; i++) {
            
            int innerIndex = in.readUnsignedShort();
            int outerIndex = in.readUnsignedShort();
            int innerName  = in.readUnsignedShort();
            int flags      = in.readUnsignedShort();
            
            InnerClass ic = 
                new InnerClass( pool.getUTF8Value( innerName ), 
                                (outerIndex != 0) ? 
                                    ObjectType.fromName( pool.getClassName(outerIndex)) : 
                                    null,
                                ObjectType.fromName( pool.getClassName( innerIndex )));
            inners[i] = ic;
            
            ic.flags.addAll( FlagIO.parse( InnerClassFlag.class, flags ) );
        }
        
        return new InnerClassesAttribute( inners );
    }
    
    /**
     * Dump for debug purposes
     */
    public void dump( IndentingPrintWriter out ) {
        out.println( name + " {" );
        out.indent();
        
        for( InnerClass ic : innerClasses ) {
            ic.dump( out );
        }
        
        out.unindent();
        out.println( "}" );
    }
}
