package org.epistem.j2avm.translator;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.j2swf.swf.code.CodeMethod;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.ValueType;

import com.anotherbigidea.flash.avm2.model.*;

/**
 * Translator for a Java method, constructor or static initializer to AVM2.
 *
 * @author nickmain
 */
public class MethodTranslator {

    public final ClassTranslator classTrans;
    public final JVMMethod       jvmMethod;    
    public final AVM2QName       avm2name; 
 
    /**
     * The helper for calls to this method
     */
    /*pkg*/ final TranslationHelper helper;
    
    /*pkg*/ MethodTranslator( ClassTranslator classTrans, JVMMethod method ) {        
        this.classTrans = classTrans;
        this.jvmMethod  = method;        
        this.helper     = classTrans.manager.helperForMethod( method, classTrans );
        
        AVM2Namespace namespace = null;
        if( jvmMethod.flags.contains( MethodFlag.MethodIsPrivate ) ) {
            namespace = classTrans.privateNamespace;
        }
        else if( jvmMethod.flags.contains( MethodFlag.MethodIsProtected ) ) {
            namespace = classTrans.protectedNamespace;
        } 
        else if( jvmMethod.flags.contains( MethodFlag.MethodIsPublic ) ) {
            namespace = AVM2Namespace.publicNamespace;
        } 
        else {
            namespace = classTrans.internalNamespace;
        }        

        //don't name-mangle flash native methods
        if( classTrans.isFlashNative ) {
            avm2name = new AVM2QName( namespace, jvmMethod.name );
            return;
        }
        
        //make method name - TODO: is there a better mangling scheme ?
        StringBuilder buff = new StringBuilder( jvmMethod.name );
        buff.append( "(" );
        
        if( jvmMethod.signature.paramTypes.length > 0 ) {
            int hash = jvmMethod.signature.toString().hashCode();
            buff.append( Integer.toHexString( hash ));
        }
        
        buff.append( ")" );
        
        avm2name = new AVM2QName( namespace, buff.toString() ); 
    }
        
    /**
     * Translate the method implementation
     * 
     * @param state the target context
     */
    /*pkg*/ void translate( TranslationState state ) {
        state.methodTranslator = this;
        
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
        
        BytecodeTranslator translator = new BytecodeTranslator( state );
        jvmMethod.getCode().instructions.accept( translator );        
    }
}
