package org.epistem.j2avm.translator;

import org.epistem.jvm.code.instructions.FieldAccess;
import org.epistem.jvm.code.instructions.MethodCall;
import org.epistem.jvm.code.instructions.New;

import com.sun.org.apache.xml.internal.utils.UnImplNode;

/**
 * Extended by translation helpers. Use the Translator annotation to 
 * associate an implementation of this class with a class, field or method.
 *
 * @author nickmain
 */
public abstract class TranslationHelper {

    /**
     * Exception thrown when a helper cannot be found
     */
    @SuppressWarnings("serial")
    public static class NotFoundException extends RuntimeException {
        NotFoundException( String msg ) { super( msg ); }
    }
    
    /**
     * Translate a method call
     * 
     * @param state the translation context
     * @param call the method call
     */
    public void translateMethodCall( TranslationState state, MethodCall call ) {
        throw new UnsupportedOperationException( "Cannot translate." );
    }

    /**
     * Translate a class allocation
     * 
     * @param state the translation state
     * @param newInsn the instruction
     */
    public void translateNew( TranslationState state, New newInsn ) {
        throw new UnsupportedOperationException( "Cannot translate." );
    }
    
    /**
     * Translate a field read
     * 
     * @param state the translation state
     * @param access the instruction
     */
    public void translateLoad( TranslationState state, FieldAccess access ) {
        throw new UnsupportedOperationException( "Cannot translate." );
    }

    /**
     * Translate a field write
     * 
     * @param state the translation state
     * @param access the instruction
     */
    public void translateStore( TranslationState state, FieldAccess access ) {
        throw new UnsupportedOperationException( "Cannot translate." );
    }
}
