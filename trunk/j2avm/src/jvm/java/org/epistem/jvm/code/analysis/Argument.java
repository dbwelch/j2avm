package org.epistem.jvm.code.analysis;

import org.epistem.jvm.code.instructions.VarAccess;
import org.epistem.jvm.type.ValueType;

/**
 * A pseudo instruction representing the producer of a given method argument.
 *
 * @author nickmain
 */
public class Argument extends VarAccess {
    public Argument( int index, ValueType type ) {
        super( index, type, true );
        // TODO Auto-generated constructor stub
    }
}
