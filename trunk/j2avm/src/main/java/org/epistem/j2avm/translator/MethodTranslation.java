package org.epistem.j2avm.translator;

import org.epistem.jvm.JVMMethod;

import com.anotherbigidea.flash.avm2.model.AVM2MethodSlot;

/**
 * Translation of a Java method, constructor or static initializer to AVM2.
 *
 * @author nickmain
 */
public class MethodTranslation {

    private final ClassTranslation classTranslation;
    private final JVMMethod        method;
    
    private AVM2MethodSlot   methodTrait;
 
    /*pkg*/ MethodTranslation( ClassTranslation classTranslation, JVMMethod method ) {
        
        this.classTranslation = classTranslation;
        this.method           = method;
    }
}
