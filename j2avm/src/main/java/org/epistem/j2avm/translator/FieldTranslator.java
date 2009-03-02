package org.epistem.j2avm.translator;

import org.epistem.jvm.code.instructions.FieldAccess;

/**
 * The translator for a field
 *
 * @author nickmain
 */
public interface FieldTranslator extends MemberTranslator {
    
    /**
     * Write the code to read this field
     * 
     * @param method the method being written
     * @param access the accessor instruction
     * @param whether the access is "super"
     */
    public void translateRead( MethodTranslator method, FieldAccess access, boolean isSuper );
    
    /**
     * Write the code to set this field
     * 
     * @param method the method being written
     * @param access the accessor instruction
     * @param whether the access is "super"
     */
    public void translateWrite( MethodTranslator method, FieldAccess access, boolean isSuper );
}
