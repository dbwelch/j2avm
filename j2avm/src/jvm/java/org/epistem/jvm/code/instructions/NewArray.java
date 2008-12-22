package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.ArrayType;

/**
 * A new-array operation
 *
 * @author nickmain
 */
public class NewArray extends Instruction {

    /**
     * The array type being created
     */
    public final ArrayType type;
    
    /**
     * The number of dimensions being initialized
     */
    public final int dimensionCount;
    
    public NewArray( ArrayType type, int dimensionCount ) {
        this.dimensionCount = dimensionCount;
        this.type = type;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitNewArray( this );
    }

}
