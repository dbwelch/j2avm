package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.JVMType;

/**
 * Push a type constant
 *
 * @author nickmain
 */
public class ConstantType extends Instruction {

    public final JVMType type; 
    
    public ConstantType( JVMType type ) {
        this.type = type;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitConstant( this );
    }
}
