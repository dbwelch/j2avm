package org.epistem.j2avm.translator;

import org.epistem.j2swf.swf.code.CodeMethod;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * Translator for a Java method, constructor or static initializer to AVM2.
 *
 * @author nickmain
 */
public interface MethodTranslator extends MemberTranslator {

    /**
     * Write the code to call this method
     * 
     * @param method the method being written
     * @param call the call instruction
     * @param isSuper is a super call
     */
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper );
    
    /**
     * Get the code of the method implementation
     * 
     * @return null if this method is not translatable
     */
    public CodeMethod getCode();
    
    /**
     * Insert a trace message into the method implementation - taking any
     * debug settings into account.
     */
    public void runtimeTrace( String message );
}
