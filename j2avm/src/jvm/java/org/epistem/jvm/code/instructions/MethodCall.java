package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.JVMType;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;

/**
 * The method invocation instructions
 *
 * @author nickmain
 */
public class MethodCall extends Instruction {

    /**
     * Call types
     */
    public static enum CallType {
        Virtual,
        Special,
        Static,
        Interface        
    }

    /**
     * The method owner
     */
    public final ObjectType owner;
    
    /**
     * The method signature
     */
    public final Signature signature;
    
    /**
     * The method return type
     */
    public final JVMType returnType;
    
    /**
     * The type of call
     */
    public final CallType callType;
    
    public MethodCall( ObjectType owner, Signature signature,
                       JVMType returnType, CallType callType ) {
        this.signature  = signature;
        this.returnType = returnType;
        this.callType   = callType;
        this.owner      = owner;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitCall( this );
    }
}
