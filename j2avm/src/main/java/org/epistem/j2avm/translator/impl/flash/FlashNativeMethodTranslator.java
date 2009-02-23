package org.epistem.j2avm.translator.impl.flash;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.impl.MethodTranslatorBase;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.j2swf.swf.code.CodeMethod;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;

import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Translator for Flash native methods
 *
 * @author nickmain
 */
public class FlashNativeMethodTranslator extends MethodTranslatorBase {
    
    /*pkg*/ FlashNativeMethodTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method, new AVM2QName( makeAVM2Namespace( classTrans, method ), method.name ) );
    }

    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#translateCall(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.MethodCall)
     */
    public void translateCall( MethodTranslator method, MethodCall call ) {
        // TODO Auto-generated method stub

    }

    /**
     * @see org.epistem.j2avm.translator.MemberTranslator#translateImplementation(org.epistem.j2swf.swf.code.CodeClass)
     */
    public void translateImplementation( CodeClass codeClass ) {
        // nada
    }

    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#getCode()
     */
    public CodeMethod getCode() {
        return null;
    }

    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#runtimeTrace(java.lang.String)
     */
    public void runtimeTrace( String message ) {
        // nada        
    }
}
