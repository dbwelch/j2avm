package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * A duplicate operation.
 *
 * @author nickmain
 */
public class Dup extends Instruction {

    /**
     * Number of 32-bit slots to duplicate - 1 or 2
     */
    public final int dupCount;
    
    /**
     * Number of 32-bit slots to skip over when inserting the duplicate(s) -
     * 0, 1 or 2
     */
    public final int skipCount;
    
    public Dup( int dupCount, int skipCount ) {
        this.dupCount  = dupCount;
        this.skipCount = skipCount;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitDup( this );
    }

}
