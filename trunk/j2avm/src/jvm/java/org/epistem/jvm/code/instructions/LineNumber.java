package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * Represents the start of a new line in the original source
 *
 * @author nickmain
 */
public class LineNumber extends Instruction {

    /**
     * The line number
     */
    public final int lineNumber;
    
    public LineNumber( int lineNumber ) {
        this.lineNumber = lineNumber;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitLineNumber( this );
    }
}
