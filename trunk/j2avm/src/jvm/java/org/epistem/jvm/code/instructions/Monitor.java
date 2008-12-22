package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * A monitor operation
 *
 * @author nickmain
 */
public class Monitor extends Instruction {

    /**
     * True if this is a monitor exit, false if an enter
     */
    public final boolean isExit;
    
    public Monitor( boolean isExit ) {
        this.isExit = isExit;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitMonitor( this );
    }

}
