package org.epistem.jvm.code.analysis;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.epistem.jvm.code.Instruction;

/**
 * The execution context at particular point
 *
 * @author nickmain
 */
public class ExecutionContext {

    /**
     * The stack - top is at the start. Values are stored as is - no account
     * is made of the difference between 32 and 64-bit values.
     */
    public final LinkedList<Value> stack = new LinkedList<Value>();
    
    /**
     * The local vars - keyed by index
     */
    public Map<Integer, Value> locals = new TreeMap<Integer, Value>();
           
    /**
     * Create the initial context for a method
     */
    public static ExecutionContext forMethod( This thisInstruction,
                                              Argument[] arguments ) {
        ExecutionContext ec = new ExecutionContext();
        
        if( thisInstruction != null ) {
            ec.locals.put( 0, new Value( thisInstruction, thisInstruction.type ) );
        }

        for( Argument arg : arguments ) {
            ec.locals.put( arg.index, new Value( arg, arg.type ));            
        }        
        
        return ec;
    }
    
    private ExecutionContext() { }
    
    /**
     * Pop a given number of items
     */
    public ExecutionContext pop( int count ) {
        while( count-- > 0 ) stack.removeFirst(); 
        return this;
    }
    
    /**
     * Peek at a value in the stack
     *  
     * @param posn zero for stack top, 1 for next down, etc
     */
    public Value peek( int posn ) {
        return stack.get( posn );
    }
    
    /**
     * Pop the top value from the stack
     */
    public Value pop() {
        return stack.removeFirst();
    }
    
    /**
     * Push a number of items such that the last one is on top.
     */
    public ExecutionContext push( Value...values ) {
        for( Value value : values ) stack.addFirst( value );
        return this;
    }
    
    /**
     * Make a copy
     */
    public static ExecutionContext copyOf( ExecutionContext toCopy ) {
        ExecutionContext newEC = new ExecutionContext();
        newEC.stack .addAll( toCopy.stack );
        newEC.locals.putAll( toCopy.locals );
        return newEC;
    }  
        
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if( obj == null || !( obj instanceof ExecutionContext ) ) return false;
        ExecutionContext other = (ExecutionContext) obj;
        
        return locals.equals( other.locals )
            && stack .equals( other.stack );
    }
    
    /**
     * Merge another context into this one.
     * 
     * @return true if this context changed (any values became merged values or
     * a new local value was set)
     */
    public boolean merge( Instruction insn, ExecutionContext ec ) {
        if( ec.stack.size() != stack.size() ) throw new RuntimeException( "Mismatched stack sizes for merge" );
        
        boolean altered = false;
        
        for( Map.Entry<Integer,Value> entry : ec.locals.entrySet() ) {
            Integer index   = entry.getKey();
            Value   other   = entry.getValue();
            Value   thisVal = locals.get( index );
            
            if( thisVal != null ) {
                Value newVal  = merge( insn, thisVal, other );
                
                if( newVal != thisVal ) {
                    altered = true;
                    locals.put( index, newVal );
                }                            
            }
            else {
                altered = true;
                locals.put( index, other );
            }            
        }
        
        int stackSize = stack.size();
        for( int i = 0; i < stackSize; i++ ) {
            Value thisVal = stack.get( i );
            Value newVal  = merge( insn, thisVal, ec.stack.get( i ) );
            if( newVal != thisVal ) {
                altered = true;
                stack.set( i, newVal );
            }            
        }
        
        return altered;
    }
    
    private Value merge( Instruction insn, Value a, Value b ) {
        if( a == null ) return b;
        if( a.equals( b ) ) return a;
        
        if( a instanceof MergedValue ) {
            MergedValue mv = (MergedValue) a;                       
            mv.mergeWith( b );
            return a;
        }
        
        return new MergedValue( insn, a.type, a, b );
    }
    
    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        
        buff.append( "locals:" );
        for( Map.Entry<Integer,Value> entry : locals.entrySet() ) {
            buff.append( "|" );
            buff.append( entry.getKey() );
            buff.append( ":" );
            buff.append( entry.getValue() );
        }        
        
        buff.append( "| stack:" );
        for( Value val : stack ) {
            buff.append( "|" );
            buff.append( val );            
        }
        
        return buff.toString();
    }
}
