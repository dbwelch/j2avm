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

/**
 * The local-variable-table attribute
 *
 * @author nickmain
 */
public class LocalVariableTypeTableAttribute extends JVMAttribute {
    
    /** The local var info */
    public final Collection<LocalVariableTypeInfo> localVariables = new HashSet<LocalVariableTypeInfo>();
     
    public LocalVariableTypeTableAttribute( LocalVariableTypeInfo...localVariableInfos ) {
        super( JVMAttribute.Name.LocalVariableTypeTable.name() );
        
        localVariables.addAll( Arrays.asList( localVariableInfos ));
    }
    
    public static LocalVariableTypeTableAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        
        int count = in.readUnsignedShort();
        LocalVariableTypeInfo[] infos = new LocalVariableTypeInfo[ count ];
        
        for (int i = 0; i < infos.length; i++) {
            int startOffset  = in.readUnsignedShort();
            int offsetLength = in.readUnsignedShort();
            int nameIndex    = in.readUnsignedShort();
            int sigIndex     = in.readUnsignedShort();
            int varIndex     = in.readUnsignedShort();
            
            infos[i] = new LocalVariableTypeInfo( startOffset, offsetLength,
                                                  pool.getUTF8Value( nameIndex ),
                                                  pool.getUTF8Value( sigIndex ),
                                                  varIndex );
        }
        
        return new LocalVariableTypeTableAttribute( infos );
    }
    
    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.println( name + " {" );
        out.indent();
        
        for ( LocalVariableTypeInfo info : localVariables ) {
            info.dump( out );
            out.println();
        }
        
        out.unindent();
        out.println( "}" );
    }
}
