package org.epistem.j2avm.translator.impl.framework;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.impl.MethodTranslatorBase;
import org.epistem.j2avm.translator.impl.java.JavaMethodTranslator;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.j2swf.swf.code.CodeMethod;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;

/**
 * A translator for Java framework methods
 *
 * @author nickmain
 */
public class JavaFrameworkMethodTranslator extends MethodTranslatorBase {

    public JavaFrameworkMethodTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method, JavaMethodTranslator.makeAVM2Name( classTrans, method ));
    }
    
    /** @see org.epistem.j2avm.translator.impl.MethodTranslatorBase#translateCall(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.MethodCall, boolean) */
    @Override
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {
        
        if( classTranslator.getJVMType().equals( ObjectType.OBJECT )
         && isSuper
         && call.signature.name.equals( Signature.INITIALIZER_NAME ) ) {
            
            //call to Object.<init> - drop the call
            method.getCode().code().pop();
            return;
        }
        
        throw new RuntimeException( "UNIMPLEMENTED call to " + jvmName );//TODO:
    }

    /** @see org.epistem.j2avm.translator.MethodTranslator#getCode() */
    public CodeMethod getCode() {
        return null;
    }

    /** @see org.epistem.j2avm.translator.MethodTranslator#runtimeTrace(java.lang.String) */
    public void runtimeTrace( String message ) {
        throw new RuntimeException( "UNIMPLEMENTED" );//TODO:
    }

    /** @see org.epistem.j2avm.translator.MemberTranslator#translateImplementation(org.epistem.j2swf.swf.code.CodeClass) */
    public void translateImplementation( CodeClass codeClass ) {
        throw new RuntimeException( "UNIMPLEMENTED" );//TODO: 
    }
}
