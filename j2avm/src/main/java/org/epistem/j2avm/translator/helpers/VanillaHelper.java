package org.epistem.j2avm.translator.helpers;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslationHelper;
import org.epistem.j2avm.translator.TranslationState;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * The helper for vanilla JVM classes
 *
 * @author nickmain
 */
public class VanillaHelper extends TranslationHelper {


    /** @see TranslationHelper#translateMethodCall(TranslationState,MethodTranslator,MethodCall) */
    @Override
    public void translateMethodCall( TranslationState state, MethodTranslator method, MethodCall call ) {

        switch( call.callType ){
        case Interface: //TODO:
            throw new RuntimeException( "Interface calls not yet implemented" );
            
        case Special:
        case Static:
        case Virtual:
        }
    }
}
