package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.AbrubtFlow;
import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.JVMType;

/**
 * A method return
 *
 * @author nickmain
 */
public class Return extends Instruction implements AbrubtFlow {

    /**
     * The return type
     */
    public final JVMType type;
    
    public Return( JVMType type ) {
        this.type = type;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitReturn( this );
    }

}
