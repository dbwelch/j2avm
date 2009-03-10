package org.epistem.j2avm.translator.transformers;

import org.epistem.j2avm.asm.AVM2_ASM;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * An instruction transformer that converts calls to the static methods of
 * AVM2_ASM into CallbackInstrucions. The callback will call the same method
 * on AVM2_ASM with the arguments given in the original bytecode.
 *
 * @see AVM2_ASM
 * @see CallbackInstruction
 * @author nickmain
 */
public class AVM2_ASM_Transformer extends Transformer {

    /** @see org.epistem.jvm.code.InstructionVisitor.Delegator#visitCall(org.epistem.jvm.code.instructions.MethodCall) */
    @Override
    public void visitCall( MethodCall call ) {
        
        //only intercept calls to AVM2_ASM
        if( ! call.owner.equals( AVM2_ASM.class.getName() ) ) {
            super.visitCall( call );
            return;
        }
    
        String name = call.signature.name;
        CallbackInstruction callback = null;
        
        if( name.startsWith( "append" ) ) callback = append( name );
        
        if( callback != null ) {
            call.list().insertAfter( call, callback );
            call.remove();
        }
    }
    
    private CallbackInstruction append( final String name ) {
        return new CallbackInstruction() {
            @Override public void generate( MethodTranslator method ) {
                System.out.println( "Generating" + name );
                method.getCode().code().add();                
            }

            @Override public String toString() { return "avm2#" + name ; }
        };
    }
    
}
