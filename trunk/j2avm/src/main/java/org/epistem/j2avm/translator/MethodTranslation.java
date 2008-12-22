package org.epistem.j2avm.translator;

import org.objectweb.asm.tree.MethodNode;

import com.anotherbigidea.flash.avm2.model.AVM2MethodSlot;

/**
 * Translation of a Java method, constructor or static initializer to AVM2.
 *
 * @author nickmain
 */
public class MethodTranslation {

    private final ClassTranslation classTranslation;
    private final MethodNode       methodNode;
    
    private AVM2MethodSlot   methodTrait;
 
    /*pkg*/ MethodTranslation( ClassTranslation classTranslation, 
                               MethodNode methodNode ) {
        
        this.classTranslation = classTranslation;
        this.methodNode       = methodNode;
    }
}
