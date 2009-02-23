package org.epistem.j2avm.translator.impl;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.flags.MethodFlag;

import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Base for method translators
 *
 * @author nickmain
 */
public abstract class MethodTranslatorBase extends MemberTranslatorBase 
                                           implements MethodTranslator {

    protected final JVMMethod jvmMethod;    
 
    protected MethodTranslatorBase( ClassTranslatorBase classTrans, JVMMethod method, AVM2QName avm2name ) { 
        super( classTrans, 
               method.name, 
               avm2name, 
               NameUtils.qnameForJavaType( method.type ) );
        this.jvmMethod  = method;        
    }
        
    /**
     * Make an AVM2 namespace that matches the visibility of the the JVM method
     */
    protected static AVM2Namespace makeAVM2Namespace( ClassTranslator classTrans, JVMMethod method ) {
        AVM2Namespace namespace = null;
        if( method.flags.contains( MethodFlag.MethodIsPrivate ) ) {
            namespace = classTrans.getAVM2PrivateNamespace();
        }
        else if( method.flags.contains( MethodFlag.MethodIsProtected ) ) {
            namespace = classTrans.getAVM2ProtectedNamespace();
        } 
        else if( method.flags.contains( MethodFlag.MethodIsPublic ) ) {
            namespace = AVM2Namespace.publicNamespace;
        } 
        else {
            namespace = classTrans.getAVM2InternalNamespace();
        }        
        
        return namespace;
    }    
}
