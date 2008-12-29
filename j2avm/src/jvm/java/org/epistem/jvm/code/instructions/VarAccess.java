package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.code.analysis.Variable;
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
     * The variable being referenced - null before analysis.
     */
    public Variable variable;
    
    /**
     * True if the access is a write
     */
    public final boolean isWrite;

    /**
     * True if the access is a read
     */
    public final boolean isRead;
    
    public VarAccess( int index, ValueType type, boolean isWrite ) {
        this.index   = index;
        this.type    = type;
        this.isWrite = isWrite;
        this.isRead  = ! isWrite;
    }

    protected VarAccess( int index, ValueType type ) {
        this.index   = index;
        this.type    = type;
        this.isWrite = true;
        this.isRead  = true;
    }

    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitVarAccess( this );
    }
}
