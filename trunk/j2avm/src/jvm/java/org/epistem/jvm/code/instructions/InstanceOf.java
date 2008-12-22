package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.ObjectOrArrayType;

/**
 * An instanceof operation
 *
 * @author nickmain
 */
public class InstanceOf extends Instruction {

    /**
     * The type to check against
     */
    public final ObjectOrArrayType type;
    
    public InstanceOf( ObjectOrArrayType type ) {
        this.type = type;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitInstanceOf( this );
    }

}
