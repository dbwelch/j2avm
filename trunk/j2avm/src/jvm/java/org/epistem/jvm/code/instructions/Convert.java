package org.epistem.jvm.code.instructions;

import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.type.PrimitiveType;
import static org.epistem.jvm.type.PrimitiveType.*;

/**
 * A numeric conversion
 *
 * @author nickmain
 */
public class Convert extends Instruction {

    /**
     * The possible conversion type pairs
     */
    public static enum Types {

        I2L( INT, LONG ),
        I2F( INT, FLOAT ),
        I2D( INT, DOUBLE ),
        L2I( LONG, INT ),
        L2F( LONG, FLOAT ),
        L2D( LONG, DOUBLE ),
        F2I( FLOAT, INT ),
        F2L( FLOAT, LONG ),
        F2D( FLOAT, DOUBLE ),
        D2I( DOUBLE, INT ),
        D2L( DOUBLE, LONG ),
        D2F( DOUBLE, FLOAT ),
        I2B( INT, BYTE ),
        I2C( INT, CHAR ),
        I2S( INT, SHORT );
        
        /**
         * The type to convert from
         */
        public final PrimitiveType fromType;
        
        /**
         * The type to convert to
         */
        public final PrimitiveType toType;
        
        private Types( PrimitiveType fromType, PrimitiveType toType ) {
            this.fromType = fromType;
            this.toType   = toType;
        }
    }
    
    /**
     * The types to convert between
     */
    public final Types types;

    public Convert( Types types ) {
        this.types = types;
    }
    
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitConvert( this );
    }
}
