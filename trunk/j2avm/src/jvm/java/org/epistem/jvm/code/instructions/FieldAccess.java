package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.ValueType;

/**
 * Instructions that access a field
 *
 * @author nickmain
 */
public class FieldAccess extends Instruction {

    /**
     * The owner of the field
     */
    public final ObjectType owner;
    
    /**
     * The field name
     */
    public final String name;
    
    /**
     * The field type
     */
    public final ValueType type;
    
    /**
     * True if the operation is a write, false if a read
     */
    public final boolean isWrite;
    
    /**
     * True if the field is static
     */
    public final boolean isStatic;
    
    public FieldAccess( ObjectType owner, String name, ValueType type,
                        boolean isWrite, boolean isStatic ) {
        this.owner = owner;
        this.name  = name;
        this.type  = type;
        this.isStatic = isStatic;
        this.isWrite  = isWrite;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitField( this );
    }
}
