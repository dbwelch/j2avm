package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * Push an int value
 *
 * @author nickmain
 */
public class ConstantInt extends Instruction {

    public final int value; 
    
    public ConstantInt( int value ) {
        this.value = value;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitConstant( this );
    }

}
