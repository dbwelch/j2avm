package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * Push a double value
 *
 * @author nickmain
 */
public class ConstantDouble extends Instruction {

    public final double value; 
    
    public ConstantDouble( double value ) {
        this.value = value;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitConstant( this );
    }

}
