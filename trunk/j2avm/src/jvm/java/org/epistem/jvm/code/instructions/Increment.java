package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * A variable increment
 *
 * @author nickmain
 */
public class Increment extends Instruction {

    /**
     * The local variable to increment
     */
    public final int index;
    
    /**
     * The increment value
     */
    public final int value;
    
    public Increment( int index, int value ) {
        this.index = index;
        this.value = value;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitIncrement( this );
    }

}
