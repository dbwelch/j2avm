package org.epistem.j2avm.translator.transformers;

import org.epistem.jvm.code.InstructionVisitor;

/**
 * Base for instruction transformers
 *
 * @author nickmain
 */
public abstract class Transformer extends InstructionVisitor.Delegator {

    protected Transformer() {
        super( null );
    }
 
    /**
     * Set the delegee
     */
    public final void delegateTo( InstructionVisitor visitor ) {
        this.delegee = visitor;
    }
}
