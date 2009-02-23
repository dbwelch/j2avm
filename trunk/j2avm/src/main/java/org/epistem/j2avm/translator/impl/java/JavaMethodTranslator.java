package org.epistem.j2avm.translator.impl.java;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslationState;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.impl.MethodTranslatorBase;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.j2swf.swf.code.CodeMethod;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.ValueType;

import com.anotherbigidea.flash.avm2.model.AVM2Code;
import com.anotherbigidea.flash.avm2.model.AVM2Name;
import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Generic translator for Java methods
 *
 * @author nickmain
 */
public class JavaMethodTranslator extends MethodTranslatorBase {

    JavaMethodTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method, makeAVM2Name( classTrans, method ) );
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
        
        jvmMethod.analyzer(); //make sure analysis has taken place
        
        //make sure that all referenced classes are required
        if( jvmMethod.type instanceof ObjectType ) {
            state.requireClass( (ObjectType) jvmMethod.type );
        }
        for( ValueType type : jvmMethod.signature.paramTypes ) {
            if( type instanceof ObjectType ) {
                state.requireClass( (ObjectType) type );
            }            
        }
        
        AVM2Name retType = NameUtils.qnameForJavaType( jvmMethod.type );

        //TODO: determine override status
        boolean isOverride = false;
        boolean isFinal = jvmMethod.flags.contains( MethodFlag.MethodIsFinal );

        ValueType[] paramTypes = jvmMethod.signature.paramTypes;
        AVM2Name[] types = new AVM2Name[ paramTypes.length ];
        for( int i = 0; i < paramTypes.length; i++ ) {
            types[i] = NameUtils.qnameForJavaType( paramTypes[i] );
        }
        
        CodeMethod avm2method = jvmMethod.flags.contains( MethodFlag.MethodIsStatic ) ?
            state.codeClass.addStaticMethod( avm2name, retType, types ) :
            state.codeClass.addInstanceMethod( avm2name, retType, isFinal, isOverride, types );
        
        state.codeMethod = avm2method;

        //transform JVM instructions 
        if( state.transformer != null ) {
            jvmMethod.getCode().instructions.accept( state.transformer );
        }
        
        //translate the instructions
        AVM2Code code = avm2method.code();
        if( J2AVM.TRACE_ON ) code.trace( J2AVM.TRACE_PREFIX + "method " + state.classTranslator.name + "::" + avm2name );
        
        JavaBytecodeTranslator translator = new JavaBytecodeTranslator( state );
        jvmMethod.getCode().instructions.accept( translator );        
    }
    
    private static AVM2QName makeAVM2Name( ClassTranslator classTrans, JVMMethod method ) {
        AVM2Namespace namespace = makeAVM2Namespace( classTrans, method );
        
        //make method name - TODO: is there a better mangling scheme ?
        StringBuilder buff = new StringBuilder( method.name );
        buff.append( "(" );
        
        if( method.signature.paramTypes.length > 0 ) {
            int hash = method.signature.toString().hashCode();
            buff.append( Integer.toHexString( hash ));
        }
        
        buff.append( ")" );
        
        return new AVM2QName( namespace, buff.toString() );                 
    } 
}
