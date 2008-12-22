package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.ObjectOrArrayType;

/**
 * A check-cast
 *
 * @author nickmain
 */
public class CheckCast extends Instruction {

    /**
     * The type to check
     */
    public final ObjectOrArrayType type;
    
    public CheckCast( ObjectOrArrayType type ) {
        this.type = type;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitCheckCast( this );
    }

}
