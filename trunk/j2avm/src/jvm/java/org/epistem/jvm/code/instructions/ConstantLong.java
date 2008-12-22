package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * Push a long value
 *
 * @author nickmain
 */
public class ConstantLong extends Instruction {

    public final long value; 
    
    public ConstantLong( long value ) {
        this.value = value;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitConstant( this );
    }

}
