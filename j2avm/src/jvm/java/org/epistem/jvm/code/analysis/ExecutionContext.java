package org.epistem.jvm.code.analysis;

import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.type.ValueType;

/**
 * The execution context at particular point
 *
 * @author nickmain
 */
public class ExecutionContext {

    /**
     * The stack - top is at the start
     */
    public final Value[] stack;
    
    /**
     * The local vars
     */
    public final VariableRef[] locals;
    
    /**
     * Create the initial context for a method
     */
    public static ExecutionContext forMethod( JVMMethod method ) {
        ValueType[] paramTypes = method.signature.paramTypes;
        boolean isStatic = method.flags.contains( MethodFlag.MethodIsStatic );
                
        //--determine the var count based on param types
        int varCount = isStatic ? 0 : 1; //"this"
        for( ValueType valueType : paramTypes ) {
            varCount += valueType.is64Bit() ? 2 : 1;
        }
        
        Value[]    stack  = new Value[0];
        VariableRef[] locals = new VariableRef[ varCount ];
        
        int varIndex = 0;
        if( ! isStatic ) {
            locals[0] = new VariableRef( 
                new Variable( 0, method.containerClass.name )); //"this"
            varIndex = 1;
        }
        for( ValueType valueType : paramTypes ) {
            locals[ varIndex ] = new VariableRef( new Variable( varIndex, valueType ));            
            varIndex += valueType.is64Bit() ? 2 : 1;
        }        
        
        return new ExecutionContext( stack, locals );
    }
    
    private ExecutionContext( Value[] stack, VariableRef[] locals ) {
        this.stack  = stack;
        this.locals = locals;
    }
    
    /**
     * Make a copy
     */
    public static ExecutionContext copyOf( ExecutionContext toCopy ) {
        Value[]       stack  = new Value[ toCopy.stack.length ];
        VariableRef[] locals = new VariableRef[ toCopy.locals.length ];
        
        System.arraycopy( toCopy.stack,  0, stack,  0, stack.length );
        System.arraycopy( toCopy.locals, 0, locals, 0, locals.length );
        
        return new ExecutionContext( stack, locals );
    }  
    
    /**
     * Make a new context from this one by popping a number of items and
     * pushing a value
     * 
     * @param value the value to push
     * @param popCount the number of 32-bit slots to pop
     */
    public ExecutionContext push( Value value, int popCount ) {
        int pushSize = value.type.is64Bit() ? 2 : 1;
        Value[]       stack2  = new Value[ stack.length + pushSize - popCount ];
        VariableRef[] locals2 = new VariableRef[ locals.length ];
        
        stack2[0] = value;
        System.arraycopy( stack, popCount, stack2, pushSize, stack.length - popCount );
        System.arraycopy( locals, 0, locals2, 0, locals.length );
        
        return new ExecutionContext( stack2, locals2 );        
    }
}
