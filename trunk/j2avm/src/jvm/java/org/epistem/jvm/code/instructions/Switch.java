package org.epistem.jvm.code.instructions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import java.util.List;

import org.epistem.jvm.code.AbrubtFlow;
import org.epistem.jvm.code.BranchInstruction;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * A switch - represents both table-switch and lookup-switch
 *
 * @author nickmain
 */
public class Switch extends BranchInstruction implements AbrubtFlow {

    /**
     * A switch case
     */
    public class Case {
        /**
         * The value to match
         */
        public final int value;
        
        /**
         * The branch target
         */
        public final String target;
        
        Case( int value, String target ) {
            this.target = target;
            this.value  = value;
        }
    }
    
    /**
     * The cases
     */
    public final List<Case> cases = new ArrayList<Case>();
    
    /**
     * @param defaultTarget the default case
     */
    public Switch( String defaultTarget ) {
        super( defaultTarget );
    }
    
    /** @see java.lang.Iterable#iterator() */
    public Iterator<Case> iterator() {
        return cases.iterator();
    }

    /**
     * Add a case. (Fluent method)
     * 
     * @param value the case value
     * @param target the target label
     * @return this
     */
    public Switch addCase( int value, String target ) {
        cases.add( new Case( value, target ) );
        return this;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitSwitch(  this );
    }

    /** @see org.epistem.jvm.code.LabelTargetter#gatherLabels(java.util.Collection) */
    public void gatherLabels( Collection<String> labels ) {
        labels.add( defaultTarget );
        
        for( Case c : this.cases ) {
            labels.add( c.target );
        }
    }
}
