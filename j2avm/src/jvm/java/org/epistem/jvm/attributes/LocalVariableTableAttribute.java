package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;
import org.epistem.jvm.type.JVMType;
import org.epistem.jvm.type.ValueType;

/**
 * The local-variable-table attribute
 *
 * @author nickmain
 */
public class LocalVariableTableAttribute extends JVMAttribute {
    
    /** The local var info */
    public final Collection<LocalVariableInfo> localVariables = new HashSet<LocalVariableInfo>();
     
    public LocalVariableTableAttribute( LocalVariableInfo...localVariableInfos ) {
        super( JVMAttribute.Name.LocalVariableTable.name() );
        
        localVariables.addAll( Arrays.asList( localVariableInfos ));
    }
    
    public static LocalVariableTableAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        
        int count = in.readUnsignedShort();
        LocalVariableInfo[] infos = new LocalVariableInfo[ count ];
        
        for (int i = 0; i < infos.length; i++) {
            int startOffset  = in.readUnsignedShort();
            int offsetLength = in.readUnsignedShort();
            int nameIndex    = in.readUnsignedShort();
            int typeIndex    = in.readUnsignedShort();
            int varIndex     = in.readUnsignedShort();
            
            infos[i] = new LocalVariableInfo( startOffset, offsetLength,
                                              pool.getUTF8Value( nameIndex ),
                                              (ValueType) JVMType.fromName( pool.getTypeName( typeIndex )),
                                              varIndex );
        }
        
        return new LocalVariableTableAttribute( infos );
    }
    
    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.println( name + " {" );
        out.indent();
        
        for ( LocalVariableInfo info : localVariables ) {
            info.dump( out );
            out.println();
        }
        
        out.unindent();
        out.println( "}" );
    }
}
