package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * Push a string value
 *
 * @author nickmain
 */
public class ConstantString extends Instruction {

    public final String value; 
    
    public ConstantString( String value ) {
        this.value = value;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitConstant( this );
    }

}
