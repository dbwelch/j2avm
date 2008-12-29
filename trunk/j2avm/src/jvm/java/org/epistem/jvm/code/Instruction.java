package org.epistem.jvm.code;

import org.epistem.jvm.code.analysis.ExecutionContext;

/**
 * Base for instruction classes
 *
 * @author nickmain
 */
public abstract class Instruction {

    /*pkg*/ InstructionList list;
    /*pkg*/ Instruction prev;
    /*pkg*/ Instruction next;
    
    /**
     * The instruction offset - -1 means unknown
     */
    public int offset = -1;
    
    /**
     * The context before this instruction - null if analysis not performed
     */
    public ExecutionContext context;
    
    /**
     * Accept a visitor.
     */
    public abstract void accept( InstructionVisitor visitor );
    
    /**
     * Remove this instruction from the list it is part of
     */
    public final void remove() {
        if( list != null ) list.remove( this );
    }
    
    /**
     * Get the instruction list containing this instruction
     * 
     * @return may be null
     */
    public final InstructionList list() {
        return list;
    }
    
    /**
     * Get the next instruction
     * 
     * @return may be null
     */
    public final Instruction next() {
        return next;
    }
    
    /**
     * Get the previous instruction
     * 
     * @return may be null
     */
    public final Instruction prev() {
        return prev;
    }
}
