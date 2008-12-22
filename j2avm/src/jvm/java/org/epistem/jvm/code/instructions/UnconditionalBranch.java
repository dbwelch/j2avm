package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.AbrubtFlow;
import org.epistem.jvm.code.BranchInstruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * An unconditional branch
 *
 * @author nickmain
 */
public class UnconditionalBranch extends BranchInstruction implements AbrubtFlow {

    /**
     * @param target the branch target
     */
    public UnconditionalBranch( String target ) {
        super( target );
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitGoto( this );
    }

    /**
     * @see org.epistem.jvm.code.LabelTargetter#getTargets()
     */
    public String[] getTargets() {
        return new String[] { defaultTarget };
    }
}
