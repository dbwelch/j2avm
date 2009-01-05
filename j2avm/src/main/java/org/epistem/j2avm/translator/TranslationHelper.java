package org.epistem.j2avm.translator;

import org.epistem.jvm.code.instructions.MethodCall;

/**
 * Extended by translation helpers. Use the Translator annotation to 
 * associate an implementation of this class with a class, field or method.
 *
 * @author nickmain
 */
public abstract class TranslationHelper {

    /**
     * Translate a method call
     * 
     * @param state the translation context
     * @param call the method call
     * 
     * @return true if the call was handled
     */
    public boolean translateMethodCall( TranslationState state, MethodCall call ) {
        return false;
    }
    
    //TODO: whole class help
    
    //TODO: field help
}
