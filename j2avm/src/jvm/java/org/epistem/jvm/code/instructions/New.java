package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.ObjectType;

/**
 * A new-instance operation
 *
 * @author nickmain
 */
public class New extends Instruction {

    /**
     * The type to instantiate
     */
    public final ObjectType type;
    
    public New( ObjectType type ) {
        this.type = type;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitNew( this );
    }

}
