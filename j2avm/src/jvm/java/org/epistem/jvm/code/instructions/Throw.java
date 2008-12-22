package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.AbrubtFlow;
import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * An exception throw
 *
 * @author nickmain
 */
public class Throw extends Instruction implements AbrubtFlow {

    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitThrow( this );
    }
}
