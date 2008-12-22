package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.ValueType;

/**
 * Read or write an array element
 *
 * @author nickmain
 */
public class ArrayAccess extends Instruction {

    /**
     * The element type
     */
    public final ValueType elementType;
    
    /**
     * True if the access is a write, false if a read
     */
    public final boolean isWrite;
    
    public ArrayAccess( ValueType elementType, boolean isWrite ) {
        this.elementType = elementType;
        this.isWrite     = isWrite;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitArrayAccess( this );
    }
}
