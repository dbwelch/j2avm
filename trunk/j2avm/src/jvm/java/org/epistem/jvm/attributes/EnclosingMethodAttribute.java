package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;
import org.epistem.jvm.type.JVMType;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;
import org.epistem.jvm.type.ValueType;

/**
 * The synthetic attribute
 *
 * @author nickmain
 */
public class EnclosingMethodAttribute extends JVMAttribute {

    /** The enclosing class */
    public final ObjectType enclosingClass;
    
    /** The enclosing method (may be null) */
    public final Signature enclosingMethod;
    
    /** The method return type */
    public final JVMType returnType;
    
    public EnclosingMethodAttribute( ObjectType enclosingClass, Signature enclosingMethod, JVMType returnType ) {
        super( JVMAttribute.Name.EnclosingMethod.name() );
        this.enclosingMethod = enclosingMethod;
        this.enclosingClass  = enclosingClass;
        this.returnType      = returnType;
    }
    
    public static EnclosingMethodAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {        
        int classIndex    = in.readUnsignedShort();
        int nameTypeIndex = in.readUnsignedShort();
        
        ObjectType enclosingClass  = (ObjectType) ObjectType.fromName( pool.getClassName( classIndex ));
        Signature  enclosingMethod = null;
        JVMType   returnType = null;
        
        if( nameTypeIndex != 0 ) {
            ConstantPool.NameAndTypeEntry nameType = 
                (ConstantPool.NameAndTypeEntry) pool.getEntry( nameTypeIndex );
            
            String methodSig   = pool.getUTF8Value( nameType.typeIndex );
            String methodName  = pool.getUTF8Value( nameType.nameIndex );
            
            String[] types = ConstantPool.readSignature( methodSig );
            returnType = JVMType.fromName( types[0] );
            
            ValueType[] paramTypes = new ValueType[ types.length - 1 ];
            for (int i = 0; i < paramTypes.length; i++) {
                paramTypes[i] = ValueType.fromName( types[i+1] );
            }
            
            enclosingMethod = new Signature( methodName, paramTypes );
        }
        
        return new EnclosingMethodAttribute( enclosingClass, enclosingMethod, returnType );
    }
    
    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.print( name + " = " );
        
        if( enclosingMethod != null ) {
            out.print( enclosingMethod.toString() );
        } else {
            out.print( enclosingClass );
        }
        
        out.println();
    }
}
