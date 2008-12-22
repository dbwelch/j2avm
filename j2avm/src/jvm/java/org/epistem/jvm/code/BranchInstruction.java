package org.epistem.jvm.code;

import java.util.Collection;

/**
 * Base for branch instructions
 *
 * @author nickmain
 */
public abstract class BranchInstruction extends Instruction 
                                        implements LabelTargetter {

    /**
     * The default target label
     */
    public final String defaultTarget;
    
    /**
     * @param defaultTarget the default target label
     */
    protected BranchInstruction( String defaultTarget ) {
        this.defaultTarget = defaultTarget;
    }
    
    /** @see org.epistem.jvm.code.LabelTargetter#gatherLabels(java.util.Collection) */
    public void gatherLabels( Collection<String> labels ) {
        labels.add( defaultTarget );
    }
}
