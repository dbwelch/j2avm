package org.epistem.jvm.code.analysis;

import java.util.HashSet;
import java.util.Set;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.type.ValueType;

/**
 * A value that is the merging of two or more other values - for example when
 * two branches of execution flow come together. A merged value is only 
 * produced by a Label.
 *
 * @author nickmain
 */
public class MergedValue extends Value {

    /**
     * The values that merged
     */
    public final Set<Value> values = new HashSet<Value>();
    
    /*pkg*/ MergedValue( Instruction insn, ValueType type, Value...vals ) {
        super( insn, type );
        for( Value v : vals ) mergeWith( v );
    }

    @Override
    public Set<Instruction> producers( Set<Instruction> prods ) {
        if( prods == null ) prods = new HashSet<Instruction>();
        for( Value v : values ) v.producers( prods );
        return prods;
    }
    
    /**
     * Add new value to merge with - unwrapping if a merged value.
     */
    public void mergeWith( Value val ) {
        if( val instanceof MergedValue ) {
            MergedValue mv = (MergedValue) val;            
            for( Value v : mv.values ) mergeWith( v );
            return;
        }
        
        values.add( val );
    }
    
    /**
     * Equal if the sets of merged values are equal
     * @see org.epistem.jvm.code.analysis.Value#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if( obj == null || !( obj instanceof MergedValue ) ) return false;
        MergedValue other = (MergedValue) obj;
        
        return values.equals( other.values );
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append( "{" );
        for( Value v : values ) {
            buff.append( " " );
            buff.append( v );
        }
        buff.append( " }" );        
        return buff.toString();
    }
}
