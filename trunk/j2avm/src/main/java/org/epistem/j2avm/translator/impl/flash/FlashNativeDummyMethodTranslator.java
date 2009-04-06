package org.epistem.j2avm.translator.impl.flash;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * Translator for Flash native methods that drops the call
 *
 * @author nickmain
 */
public class FlashNativeDummyMethodTranslator extends FlashNativeMethodTranslator {
    
    public FlashNativeDummyMethodTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method );
    }

    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#translateCall(MethodTranslator, MethodCall, boolean)
     */
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {
        if( call.signature.paramTypes.length > 0 ) {
            throw new RuntimeException( "Too many args: " + call ); //TODO: 
        }
            
        method.getCode().code().pop(); //pop the instances
    }
}
