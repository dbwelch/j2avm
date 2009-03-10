package org.epistem.j2avm.translator.impl.java;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.annotations.runtime.Name;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.impl.MethodTranslatorBase;
import org.epistem.j2avm.translator.transformers.AVM2_ASM_Transformer;
import org.epistem.j2avm.translator.transformers.Transformer;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.j2swf.swf.code.CodeMethod;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.code.instructions.MethodCall;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.ValueType;

import com.anotherbigidea.flash.avm2.model.AVM2Name;
import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Generic translator for Java methods
 *
 * @author nickmain
 */
public class JavaMethodTranslator extends MethodTranslatorBase {

    private CodeMethod codeMethod;
    
    protected JavaMethodTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method, makeAVM2Name( classTrans, method ) );
    }

    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#translateCall(MethodTranslator, MethodCall, boolean)
     */
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {
        super.translateCall( method, call, isSuper );
    }

    /**
     * @see org.epistem.j2avm.translator.MemberTranslator#translateImplementation(org.epistem.j2swf.swf.code.CodeClass)
     */
    public void translateImplementation( CodeClass codeClass ) {
        
        jvmMethod.analyzer(); //make sure analysis has taken place
        
        //make sure that all referenced classes are required
        if( jvmMethod.type instanceof ObjectType ) {
            classTranslator.getManager().requireClass( NameUtils.normalize( (ObjectType) jvmMethod.type ));
        }
        for( ValueType type : jvmMethod.signature.paramTypes ) {
            if( type instanceof ObjectType ) {
                classTranslator.getManager().requireClass(NameUtils.normalize( (ObjectType) type ));
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
        
        codeMethod = isStatic ?
            codeClass.addStaticMethod  ( avm2Name, retType, types ) :
            codeClass.addInstanceMethod( avm2Name, retType, isFinal, isOverride, types );
                
        //transform JVM instructions 
        Transformer transformer = new AVM2_ASM_Transformer();
        jvmMethod.getCode().instructions.accept( transformer );
            
        //translate the instructions
        runtimeTrace( J2AVM.TRACE_PREFIX + "method " + classTranslator.getJVMType() + "::" + jvmName );
        
        JavaBytecodeTranslator translator = new JavaBytecodeTranslator( this, jvmMethod );
        jvmMethod.getCode().instructions.accept( translator );        
    }
    
    /**
     * Make an AVM2 name for a Java method
     */
    public static AVM2QName makeAVM2Name( ClassTranslator classTrans, JVMMethod method ) {
        
        //handle an explicit name override
        try {
            JavaAnnotation nameAnnot = method.attributes.annotation( Name.class.getName() );
            if( nameAnnot != null ) {
                String name = nameAnnot.stringValue( "value" );
                return new AVM2QName( name );
            }            
        } catch( Exception ex ) {
            throw new RuntimeException( "While looking for @Name annotation on method " + method.signature, ex );
        }
        
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

    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#getCode()
     */
    public CodeMethod getCode() {
        return codeMethod;
    }

    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#runtimeTrace(java.lang.String)
     */
    public void runtimeTrace( String message ) {
        if( codeMethod == null || ! J2AVM.TRACE_ON ) return;
        
        codeMethod.code().trace( message );
    } 
}
