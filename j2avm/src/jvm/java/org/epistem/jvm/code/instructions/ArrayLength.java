package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * Get the length of an array
 *
 * @author nickmain
 */
public class ArrayLength extends Instruction {

    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitArrayLength( this );
    }
}
