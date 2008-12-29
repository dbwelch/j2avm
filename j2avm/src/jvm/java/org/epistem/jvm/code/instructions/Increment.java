package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.PrimitiveType;

/**
 * A variable increment
 *
 * @author nickmain
 */
public class Increment extends VarAccess {

    /**
     * The increment value
     */
    public final int value;
    
    public Increment( int index, int value ) {
        super( index, PrimitiveType.INT );
        this.value = value;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitIncrement( this );
    }

}
