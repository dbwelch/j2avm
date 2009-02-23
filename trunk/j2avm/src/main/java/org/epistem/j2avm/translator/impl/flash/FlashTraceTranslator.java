package org.epistem.j2avm.translator.impl.flash;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * Helper for the static trace() method
 *
 * @author nickmain
 */
public class FlashTraceTranslator extends FlashNativeMethodTranslator {

    FlashTraceTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method );
    }

    /** @see org.epistem.j2avm.translator.impl.flash.FlashNativeMethodTranslator#translateCall(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.MethodCall) */
    @Override
    public void translateCall( MethodTranslator method, MethodCall call ) {
        method.getCode().code().trace();
        
        // TODO Auto-generated method stub
        super.translateCall( method, call );
    }

    
}
