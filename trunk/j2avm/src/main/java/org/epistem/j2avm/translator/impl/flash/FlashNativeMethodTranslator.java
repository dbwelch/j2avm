package org.epistem.j2avm.translator.impl.flash;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.impl.MethodTranslatorBase;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.j2swf.swf.code.CodeMethod;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.Instruction;
import org.epistem.jvm.code.analysis.ExecutionContext;
import org.epistem.jvm.code.analysis.Value;
import org.epistem.jvm.code.instructions.MethodCall;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;
import org.epistem.jvm.type.VoidType;

import com.anotherbigidea.flash.avm2.model.AVM2Code;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Translator for Flash native methods
 *
 * @author nickmain
 */
public class FlashNativeMethodTranslator extends MethodTranslatorBase {
    
    /*pkg*/ FlashNativeMethodTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        this( classTrans, method, new AVM2QName( makeAVM2Namespace( classTrans, method ), method.name ) );
    }

    /*pkg*/ FlashNativeMethodTranslator( ClassTranslatorBase classTrans, JVMMethod method, AVM2QName name ) {
        super( classTrans, method, name );
    }
    
    /**
     * @see org.epistem.j2avm.translator.MethodTranslator#translateCall(MethodTranslator, MethodCall, boolean)
     */
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {
        if( call.signature.name.equals( Signature.INITIALIZER_NAME ) ) {
            AVM2Code code = method.getCode().code();

            //call to Flash-native super constructor is dropped since there is
            // no super init on a flash object
            if( isSuper ) {
                code.pop();
                return;
            }
            
            int argCount = call.signature.paramTypes.length;

            ClassTranslator target = classTranslator.getManager().translatorForClass( call.owner );            
            code.constructProp( target.getAVM2Name(), argCount );

            //get the "new" instruction related to this call
            Value newValue = call.context.stack.get( argCount );
            Instruction newInsn = newValue.producer;
            
            //find other instances of the "new" remaining on the stack
            ExecutionContext ctxt = call.next().getContext(); //context after the <init>
            
            //TODO: this is a temporary kludge - we will just look to see if
            //the stack top is the "new" and that there are no other instances
            //of it deeper in the stack
            int newCount = 0;
            for( Value v : ctxt.stack ) {
                if( v.producer == newInsn ) newCount++;
            }
            
            if( newCount == 0 ) {
                code.pop(); //new instance not used
            }
            else if( newCount == 1 && ctxt.stack.peek().producer == newInsn ) {
                //replace the class with the new instance
                if( call.owner.equals( ObjectType.STRING_BUILDER ) ) {
                    code.trace( "calling " + call.signature + " on " + call.owner );
                }
                
                code.swap();
                code.pop();
            }
            else {
                throw new RuntimeException( "UNIMPLEMENTED - non-trivial Flash native construction" );
            }
            
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
