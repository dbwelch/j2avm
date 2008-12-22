package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.BranchInstruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * A conditional branch
 *
 * @author nickmain
 */
public class ConditionalBranch extends BranchInstruction {

    public static enum Condition {
        IfEq0, IfNE0, IfLT0, IfGE0, IfGT0, IfLE0,
        IfEq,  IfNE,  IfLT,  IfGE,  IfGT,  IfLE,
        IfSame, IfNotSame, IfNull, IfNonNull
    }
    
    /**
     * The condition
     */
    public final Condition condition;
    
    /**
     * @param target the branch target
     * @param condition the branch condition
     */
    public ConditionalBranch( String target, Condition condition ) {
        super( target );
        this.condition = condition;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitConditional( this );
    }
}
