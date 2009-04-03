package org.epistem.j2avm.translator.impl.flash;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.impl.MethodTranslatorBase;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.j2swf.swf.code.CodeMethod;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;
import org.epistem.jvm.type.Signature;

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
     * @see org.epistem.j2avm.translator.MethodTranslator#translateCall(MethodTranslator, MethodCall, boolean)
     */
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {
        if( call.signature.name.equals( Signature.INITIALIZER_NAME ) ) {
            if( call.signature.paramTypes.length > 0 ) {
                throw new RuntimeException( "LIMITATION: Can only call no-arg constructor of Flash native class: " + call.owner ); //TODO: 
            }
            
            //drop this call - the Flash object has already been created
            //via the "new" operation.
            method.getCode().code().pop(); //pop the instances
            return;
        }
        
        super.translateCall( method, call, isSuper );
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
