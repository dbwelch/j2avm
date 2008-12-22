package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.code.InstructionList;
import org.epistem.jvm.io.ConstantPool;
import org.epistem.jvm.io.parser.ClassFileParser;
import org.epistem.jvm.io.parser.CodeParser;
import org.epistem.jvm.io.print.CodePrinter;

/**
 * The Code attribute.
 *
 * @author nickmain
 */
public class CodeAttribute extends JVMAttribute {

    /** Mutable map of attributes by name */
    public final Map<String,JVMAttribute> attributes = new HashMap<String,JVMAttribute>();
        
    /** The code */
    public final InstructionList instructions = new InstructionList(); 
    
    public CodeAttribute() {
        super( JVMAttribute.Name.Code.name() );
    }
    
    public static CodeAttribute parse( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        in.readUnsignedShort(); //max stack
        in.readUnsignedShort(); //max locals
        
        CodeAttribute code = new CodeAttribute();
        CodeParser parser = new CodeParser( pool, in, code.instructions );
        
        //attributes
        int attrCount = in.readUnsignedShort();
        for (int i = 0; i < attrCount; i++) {
            JVMAttribute attr = ClassFileParser.parseAttribute( in, loader, pool );
            
            //apply the line number attribute directly to the statements
            if( attr instanceof LineNumberTableAttribute ) {
                parser.applyLineNumbers( (LineNumberTableAttribute) attr );
                continue;
            }
            
            //TODO: local var attr
            
            code.attributes.put( attr.name, attr );
        }
        
        return code;
    }
    
    /**
     * Dump for debug purposes
     */
    public void dump( IndentingPrintWriter out ) {
        out.println( name + " {" );
        out.indent();
        
        instructions.accept( new CodePrinter( out ) );
        
        if( ! attributes.isEmpty() ) {
            out.println();
            out.println( "attributes {" );
            out.indent();
            
            for( JVMAttribute attr : attributes.values() ) {
                attr.dump( out );
            }
            
            out.unindent();
            out.println( "}" );
        }
        
        out.unindent();
        out.println( "}" );
    }
}
