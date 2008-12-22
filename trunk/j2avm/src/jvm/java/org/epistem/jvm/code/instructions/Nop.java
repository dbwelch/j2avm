package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * A no-op
 *
 * @author nickmain
 */
public class Nop extends Instruction {

    /** @see org.epistem.jvm.code.Instruction#accept(org.epistem.jvm.code.InstructionVisitor) */
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitNop( this );
    }
}
