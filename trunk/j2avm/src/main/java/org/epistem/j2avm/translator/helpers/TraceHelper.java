package org.epistem.j2avm.translator.helpers;

import org.epistem.j2avm.translator.TranslationHelper;
import org.epistem.j2avm.translator.TranslationState;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * Translates the call to the Flash trace function
 *
 * @author nickmain
 */
public class TraceHelper extends TranslationHelper {

    /** @see org.epistem.j2avm.translator.TranslationHelper#translateMethodCall(org.epistem.j2avm.translator.TranslationState, org.epistem.jvm.code.instructions.MethodCall) */
    @Override
    public void translateMethodCall( TranslationState state, MethodCall call ) {
        state.codeMethod.code().trace();
    }
}
