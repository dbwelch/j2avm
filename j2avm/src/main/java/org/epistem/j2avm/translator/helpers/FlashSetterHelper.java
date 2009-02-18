package org.epistem.j2avm.translator.helpers;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslationHelper;
import org.epistem.j2avm.translator.TranslationState;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * Helper for calls to setter methods
 *
 * @author nickmain
 */
public class FlashSetterHelper extends FlashNativeHelper {

    /** @see TranslationHelper#translateMethodCall(TranslationState,MethodTranslator,MethodCall) */
    @Override
    public void translateMethodCall( TranslationState state, MethodTranslator method, MethodCall call ) {
        
        String propName = NameUtils.nameFromAccessor( method.jvmMethod.name );
        state.codeMethod.code().setProperty( propName );
        
        //TODO: verify that this can set a property defined by a superclass
    }
}
