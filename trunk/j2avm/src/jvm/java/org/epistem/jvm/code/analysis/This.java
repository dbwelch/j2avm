package org.epistem.jvm.code.analysis;

import org.epistem.jvm.code.instructions.VarAccess;
import org.epistem.jvm.type.ObjectType;

/**
 * A pseudo instruction representing the producer of the "this" value
 *
 * @author nickmain
 */
public class This extends VarAccess {
    
    /**
     * @param type the owning class
     */
    public This( ObjectType type ) {
        super( 0, type, true );
    }    
}
