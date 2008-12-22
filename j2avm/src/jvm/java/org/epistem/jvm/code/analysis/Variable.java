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
     * The local var index, -1 if not known
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
     * The instructions that read or write this variable
     */
    public final Set<VarAccess> accessors = new HashSet<VarAccess>();
    
    /*pkg*/ final Set<VariableRef> references = new HashSet<VariableRef>();
    
    public Variable( int index, ValueType type ) {
        this.index = index;
        this.type  = type;
    }
}
