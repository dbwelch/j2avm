package org.epistem.j2avm.translator.impl.java;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.transformers.AVM2_ASM_Transformer;
import org.epistem.j2avm.translator.transformers.Transformer;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;

import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Generic translator for Java methods
 *
 * @author nickmain
 */
public class JavaStaticInitMethodTranslator extends JavaMethodTranslator {

    public JavaStaticInitMethodTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method, new AVM2QName( JavaClassTranslator.CLINIT_NAME ) );
    }

    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#translateCall(MethodTranslator, MethodCall, boolean)
     */
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {
        throw new RuntimeException( "Cannot call <clinit>" );
    }

    /*
     * The <clinit> method is now just implemented as a static method of the
     * class and called from the class initialization script after the
     * class slot has been assigned.
     */
    /**
     * @see org.epistem.j2avm.translator.MemberTranslator#translateImplementation(org.epistem.j2swf.swf.code.CodeClass)
     */
//    public void translateImplementation( CodeClass codeClass ) {
//        
//        codeMethod = codeClass.addStaticInitializer();
//        
//        jvmMethod.analyzer(); //make sure analysis has taken place
//        
//        //transform JVM instructions
//        Transformer transformer = new AVM2_ASM_Transformer();
//        jvmMethod.getCode().instructions.accept( transformer );
//        
//        //translate the instructions
//        runtimeTrace( J2AVM.TRACE_PREFIX + " " + classTranslator.getJVMType() + "::<clinit>" );
//                
//        JavaBytecodeTranslator translator = new JavaBytecodeTranslator( this, jvmMethod );
//        jvmMethod.getCode().instructions.accept( translator );
//        
//    }
}
