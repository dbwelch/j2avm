package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * Push a float value
 *
 * @author nickmain
 */
public class ConstantFloat extends Instruction {

    public final float value; 
    
    public ConstantFloat( float value ) {
        this.value = value;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitConstant( this );
    }

}
