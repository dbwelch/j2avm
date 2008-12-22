package org.epistem.jvm.code.analysis;

import java.util.Collection;
import java.util.HashSet;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.type.ValueType;

/**
 * A computed value
 *
 * @author nickmain
 */
public class Value {

    /**
     * The value type
     */
    public final ValueType type;
    
    /**
     * The instructions that produce this value
     */
    public final Collection<Instruction> producers = new HashSet<Instruction>();
    
    /**
     * @param type the value type
     */
    public Value( ValueType type ) {
        this.type = type;
    }
}
