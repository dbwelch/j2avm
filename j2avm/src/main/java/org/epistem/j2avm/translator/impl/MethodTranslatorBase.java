package org.epistem.j2avm.translator.impl;

import java.util.ArrayList;
import java.util.List;

import org.epistem.code.LocalValue;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.code.instructions.MethodCall;
import org.epistem.jvm.code.instructions.MethodCall.CallType;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.VoidType;

import com.anotherbigidea.flash.avm2.instruction.Instruction;
import com.anotherbigidea.flash.avm2.model.AVM2Code;
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
    protected final boolean isStatic;
    
    protected MethodTranslatorBase( ClassTranslatorBase classTrans, JVMMethod method, AVM2QName avm2name ) { 
        super( classTrans, 
               method.name, 
               avm2name, 
               NameUtils.qnameForJavaType( method.type ) );
        this.jvmMethod = method;    
        this.isStatic  = jvmMethod.flags.contains( MethodFlag.MethodIsStatic );
    }
        
    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#translateCall(MethodTranslator, MethodCall, boolean)
     */
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {
        int      argCount = call.signature.paramTypes.length;
        boolean  isVoid   = ( call.returnType == VoidType.VOID );        
        AVM2Code code     = method.getCode().code();
        
        //--TODO: implement static calls in a more efficient manner
        if( call.callType == CallType.Static ) {
            //pop all args into locals
            List<LocalValue<Instruction>> args = new ArrayList<LocalValue<Instruction>>();
            for( int i = 0; i < argCount; i++ ) {
                LocalValue<Instruction> local = code.newLocal();
                code.setLocal( local );
                args.add( 0, local );
            }
            
            //find the class
            classTranslator.translateStaticPush( method );
            
            //restore args
            for( LocalValue<Instruction> local : args ) {
                code.getLocal( local );
            }
            
            if( isVoid ) code.callPropVoid( avm2Name, argCount );
            else         code.callProperty( avm2Name, argCount );            
            
            return;
        }
                
        if( call.callType == CallType.Interface ) {
            throw new RuntimeException( "Interface calls not yet implemented" ); //TODO: interface calls
        }
        
        if( isSuper ) {
            if( isVoid ) code.callSuperVoid    ( avm2Name, argCount );
            else         code.callSuperProperty( avm2Name, argCount );            
            return;
        }
        
        //--virtual call
        if( isVoid ) code.callPropVoid( avm2Name, argCount );
        else         code.callProperty( avm2Name, argCount ); 
    }
    
    /**
     * Get a method translator based on any annotations found on the method
     * or, failing that, one created by the class translator
     */
    public static MethodTranslator forMethod( JVMMethod method, ClassTranslatorBase classBase ) {
        JavaAnnotation annot = 
            TranslatorManager.findTranslatorAnnotation( classBase.manager.loader, 
                                                        method.attributes );
        if( annot != null ) {
            ObjectType helperType = (ObjectType) annot.classValue( "value" );
            
            try {
                @SuppressWarnings("unchecked")
                Class<? extends MethodTranslator> ctClass = 
                    (Class<? extends MethodTranslator>) Class.forName( helperType.name );

                return (MethodTranslator) 
                    ctClass.getConstructor( ClassTranslatorBase.class, JVMMethod.class )
                           .newInstance( classBase, method );
            } catch( Exception e ) {
                throw new RuntimeException( e );
            }
        }
        
        return classBase.defaultMethodTranslator( method );
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
