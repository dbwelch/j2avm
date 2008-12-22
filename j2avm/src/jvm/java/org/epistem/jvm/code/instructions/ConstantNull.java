package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * Push a null
 *
 * @author nickmain
 */
public class ConstantNull extends Instruction {

    /** @see org.epistem.jvm.code.Instruction#accept(org.epistem.jvm.code.InstructionVisitor) */
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitConstantNull( this );
    }
}
