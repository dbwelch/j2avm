package org.epistem.j2avm.translator.impl.java;

import java.util.EnumSet;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.code.instructions.New;

import com.anotherbigidea.flash.avm2.MethodInfoFlags;
import com.anotherbigidea.flash.avm2.model.AVM2Method;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

public class JavaInterfaceTranslator extends JavaTranslator {
    
    public JavaInterfaceTranslator( TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass );
    }

    protected JavaInterfaceTranslator(  TranslatorManager manager, JVMClass jvmClass, AVM2QName avm2name ) {
        super( manager, jvmClass, avm2name );
    }
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#translateImplementation(org.epistem.j2swf.swf.code.Code) */
    public void translateImplementation( Code code ) {
        J2AVM.log.info( "Translating interface " + name );
        
        codeClass = code.addClass( avm2name.toQualString(), null, true, false, true );
        translateMembers();
        addImplementedInterfaces();
 
        //-- Constructor
        codeClass.avm2class.constructor = 
            new AVM2Method( null, EnumSet.noneOf( MethodInfoFlags.class ));
        
        //TODO: static initializer
        
        //TODO: class annotations
        //TODO: field annotations
        //TODO: method annotations
        //TODO: parameter annotations
        
        //TODO: a bunch of things I forgot
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#translateInstantiation(MethodTranslator, New) */
    public void translateInstantiation( MethodTranslator method, New newInsn ) {
        throw new RuntimeException( "Interfaces cannot be instantiated" );
    }
}
