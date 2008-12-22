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
public class SignatureAttribute extends JVMAttribute {
    
    /** The signature */
    public final String signature;
    
    public SignatureAttribute( String signature ) {
        super( JVMAttribute.Name.Signature.name() );
        this.signature = signature;
    }
    
    public static SignatureAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {        
        int index = in.readUnsignedShort();
        return new SignatureAttribute( pool.getUTF8Value( index ));
    }
    
    /** Dump for debug purposes */
    public final void dump( IndentingPrintWriter out ) {
        out.println( name + " = " + signature );
    }
}
