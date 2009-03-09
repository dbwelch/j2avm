package org.epistem.j2avm.translator.impl.framework;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.impl.java.JavaMethodTranslator;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * A translator for Java framework methods
 *
 * @author nickmain
 */
public class JavaFrameworkMethodTranslator extends JavaMethodTranslator {

    public JavaFrameworkMethodTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method );
    }
    
    /** @see org.epistem.j2avm.translator.impl.MethodTranslatorBase#translateCall(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.MethodCall, boolean) */
    @Override
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {  
        
        
        super.translateCall( method, call, isSuper );
    }

    /** @see org.epistem.j2avm.translator.MethodTranslator#runtimeTrace(java.lang.String) */
    public void runtimeTrace( String message ) {
        super.runtimeTrace( message );
    }

    /** @see org.epistem.j2avm.translator.MemberTranslator#translateImplementation(org.epistem.j2swf.swf.code.CodeClass) */
    public void translateImplementation( CodeClass codeClass ) {
        super.translateImplementation( codeClass ); 
    }
}
