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
     */
    public void translateRead( MethodTranslator method, FieldAccess access );
    
    /**
     * Write the code to set this field
     * 
     * @param method the method being written
     * @param access the accessor instruction
     */
    public void translateWrite( MethodTranslator method, FieldAccess access );
}
