package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * A stack swap
 *
 * @author nickmain
 */
public class Swap extends Instruction {

    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitSwap( this );
    }

}
