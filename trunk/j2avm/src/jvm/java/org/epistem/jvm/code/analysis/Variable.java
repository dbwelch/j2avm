package org.epistem.jvm.code.analysis;

import java.util.HashSet;
import java.util.Set;

import org.epistem.jvm.code.instructions.VarAccess;
import org.epistem.jvm.type.ValueType;

/**
 * A local variable
 *
 * @author nickmain
 */
public class Variable {

    /**
     * The local var index
     */
    public final int index;
    
    /**
     * The variable name - may not be unique within a statement list. May be
     * null.
     */
    public String name;
    
    /**
     * The variable type
     */
    public final ValueType type;
    
    /**
     * The instructions that read this variable
     */
    public final Set<VarAccess> readers = new HashSet<VarAccess>();

    /**
     * The instructions that write this variable
     */
    public final Set<VarAccess> writers = new HashSet<VarAccess>();
    
    /*pkg*/ Variable( int index, ValueType type ) {
        this.index = index;
        this.type  = type;
    }

    /**
     * Makes instance identity explicit.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals( Object obj ) {
        return super.equals( obj );
    }
}
