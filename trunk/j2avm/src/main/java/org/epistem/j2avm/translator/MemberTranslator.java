package org.epistem.j2avm.translator;

import org.epistem.j2swf.swf.code.CodeClass;

import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Base for field and method translators
 *
 * @author nickmain
 */
public interface MemberTranslator {

    /**
     * Get the class that owns this field
     */
    public ClassTranslator getClassTranslator();
    
    /**
     * Write the implementation into the given class
     * 
     * @param codeClass the target avm2 class
     */
    public void translateImplementation( CodeClass codeClass );
        
    /**
     * Get the JVM name of the member
     */
    public String getJVMName();
    
    /**
     * Get the AVM2 name for the member
     */
    public AVM2QName getAVM2Name();
    
    /**
     * Get the AVM2 type for the member 
     */
    public AVM2QName getAVM2Type();
}
