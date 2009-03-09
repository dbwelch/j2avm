package org.epistem.j2avm.translator.transformers;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * A custom instruction that allows a callback to be embedded into the JVM
 * instructions. 
 *
 * @author nickmain
 */
public abstract class CallbackInstruction extends Instruction {

    /**
     * Called by the bytecode visitor to cause the AVM2 code generation
     * implemented by the subclass of this
     */
    public abstract void generate( MethodTranslator method );
    
    /**
     * @see org.epistem.jvm.code.Instruction#accept(org.epistem.jvm.code.InstructionVisitor)
     */
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitCustom( this );
    }
    
    /** Generate the dump text for this instruction */
    @Override
    public abstract String toString();
}
