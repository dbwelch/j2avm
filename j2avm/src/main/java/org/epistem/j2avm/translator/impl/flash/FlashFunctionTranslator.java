package org.epistem.j2avm.translator.impl.flash;

import java.util.List;

import org.epistem.code.LocalValue;
import org.epistem.j2avm.annotations.runtime.Function;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;
import org.epistem.jvm.code.instructions.MethodCall.CallType;
import org.epistem.jvm.type.VoidType;

import com.anotherbigidea.flash.avm2.instruction.Instruction;
import com.anotherbigidea.flash.avm2.model.AVM2Code;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Translator for Flash stand-alone native functions
 *
 * @author nickmain
 */
public class FlashFunctionTranslator extends FlashNativeMethodTranslator {

    public FlashFunctionTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method, makeName( method ) );
    }

    private static AVM2QName makeName( JVMMethod method ) {
        try {
            String name = method.attributes.annotation( Function.class.getName() ).stringValue( "value" );
            return new AVM2QName( name );
        } catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }
    
    /** @see org.epistem.j2avm.translator.impl.flash.FlashNativeMethodTranslator#translateCall(MethodTranslator, MethodCall, boolean) */
    @Override
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {

        AVM2Code code = method.getCode().code();
        int      argCount = call.signature.paramTypes.length;
        boolean  isVoid   = ( call.returnType == VoidType.VOID );        

        //--TODO: implement static calls in a more efficient manner
        if( call.callType == CallType.Static ) {
            List<LocalValue<Instruction>> args = storeArgs( code, argCount );
            
            //find the object that holds the function
            code.findPropStrict( avm2Name );
            
            restoreArgs( code, args );
            
            if( isVoid ) code.callPropVoid( avm2Name, argCount );
            else         code.callProperty( avm2Name, argCount );            
            
            return;
        }        
    }
}
