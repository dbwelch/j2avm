package org.epistem.j2avm.translator.transformers;

import org.epistem.jvm.code.instructions.MethodCall;

/**
 * A transformer that inserts the class for a static call so that it appears
 * on the stack before the call arguments. This is so that the stack is correct
 * for an AVM2 call to a class static method.
 *
 * @author nickmain
 */
public class StaticCallHandler extends Transformer {

    @Override
    public void visitCall( MethodCall call ) {

        //TODO: need better JVM analysis in order to do this
        
        super.visitCall( call );
    }
}
