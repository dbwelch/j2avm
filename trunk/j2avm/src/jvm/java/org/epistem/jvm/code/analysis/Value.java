package org.epistem.jvm.code.analysis;

import java.util.HashSet;
import java.util.Set;

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
     * The instruction that produces this value
     */
    public final Instruction producer;
    
    /**
     * @param type the value type
     */
    /*pkg*/ Value( Instruction producer, ValueType type ) {
        this.type = type;
        this.producer = producer;
    }

    /**
     * Values are equal if produced by the same instruction 
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if( obj == null || !( obj instanceof Value ) ) return false;
        Value other = (Value) obj;
        return producer == other.producer;
    }
    
    @Override
    public int hashCode() {
        return producer.hashCode();
    }

    /**
     * Gather all the leaf producers of this value in the given set - that is,
     * if this is a MergedValue then only gather the producers of the merged
     * values.
     * 
     * @param prods if null then create a new set
     * @return the set
     */
    public Set<Instruction> producers( Set<Instruction> prods ) {
        if( prods == null ) prods = new HashSet<Instruction>();
        prods.add( producer );
        return prods;
    }
    
    @Override
    public String toString() {
        if( producer instanceof This ) return "this";
        if( producer instanceof Argument ) return "arg";
        
        return type.abbreviation + "@" + producer.offset;
    }
}
