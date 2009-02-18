package org.epistem.j2avm.translator.helpers;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslationHelper;
import org.epistem.j2avm.translator.TranslationState;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * Translates the call to the Flash trace function
 *
 * @author nickmain
 */
public class TraceHelper extends TranslationHelper {

    /** @see TranslationHelper#translateMethodCall(TranslationState,MethodTranslator,MethodCall) */
    @Override
    public void translateMethodCall( TranslationState state, MethodTranslator method, MethodCall call ) {
        state.codeMethod.code().trace();
    }
}
