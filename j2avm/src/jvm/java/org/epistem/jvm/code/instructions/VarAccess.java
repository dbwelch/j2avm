package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.ValueType;

/**
 * A local variable access
 *
 * @author nickmain
 */
public class VarAccess extends Instruction {

    /**
     * The local variable index
     */
    public final int index;
    
    /**
     * The variable type
     */
    public final ValueType type;
    
    /**
     * True if the access is a write, false if a read
     */
    public final boolean isWrite;
    
    public VarAccess( int index, ValueType type, boolean isWrite ) {
        this.index   = index;
        this.type    = type;
        this.isWrite = isWrite;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitVarAccess( this );
    }
}
