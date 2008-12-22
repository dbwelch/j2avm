package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.PrimitiveType;

/**
 * A numerical operation
 *
 * @author nickmain
 */
public class NumericOp extends Instruction {

    /**
     * The operation types
     */
    public static enum Operation {
        Add, Subtract, Multiply, Divide, Remainder, Negate,
        ShiftLeft, ShiftRight, ShiftRightUnsigned,
        And, Or, Xor,
        Compare, CompareG, CompareL
    }
    
    /**
     * The operation type
     */
    public final Operation operation; 
    
    /**
     * The type of the operand(s)
     */
    public final PrimitiveType type;
    
    public NumericOp( Operation operation, PrimitiveType type ) {
        this.operation = operation;
        this.type      = type;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitNumericOp( this );
    }
}
