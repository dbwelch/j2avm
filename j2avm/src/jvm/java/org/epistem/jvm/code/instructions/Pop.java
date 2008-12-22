package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * A stack pop
 *
 * @author nickmain
 */
public class Pop extends Instruction {

    /**
     * The number of 32-bit slots to pop
     */
    public final int count;
    
    public Pop( int count ) {
        this.count = count;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitPop( this );
    }

}
