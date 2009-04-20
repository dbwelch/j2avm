package org.epistem.j2avm.translator.transformers;

import org.epistem.j2avm.asm.AVM2_ASM;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.jvm.code.instructions.MethodCall;

import com.anotherbigidea.flash.avm2.model.AVM2LateMultiname;
import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2StandardNamespace;

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
        
        if     ( name.startsWith( "append" ) ) callback = append( name );
        else if( name.startsWith( "pop"    ) ) callback = nothing( name );
        else if( name.startsWith( "push"   ) ) callback = nothing( name );
        else if( name.startsWith( "retype" ) ) callback = nothing( name );
        else if( name.startsWith( "trace"  ) ) callback = trace( name );
        else if( name.startsWith( "equal"  ) ) callback = equal( name );
        else if( name.startsWith( "setPublicProperty" ) ) callback = setPubProp( name );
        else if( name.startsWith( "getPublicProperty" ) ) callback = getPubProp( name );
        else if( name.startsWith( "callVoidFunction"  ) ) callback = callVoid( name );
        else if( name.startsWith( "callFunction"      ) ) callback = call( name );
        
        if( callback != null ) {
            call.list().insertAfter( call, callback );
            call.remove();
        }
    }

    private CallbackInstruction getPubProp( final String name ) {
        return new CallbackInstruction() {
            @Override public void generate( MethodTranslator method ) {
                System.out.println( "Generating " + name );
                method.getCode().code().getProperty( new AVM2LateMultiname( AVM2Namespace.publicNamespace ) );                
            }

            @Override public String toString() { return "avm2#" + name ; }
        };
    }

    private CallbackInstruction setPubProp( final String name ) {
        return new CallbackInstruction() {
            @Override public void generate( MethodTranslator method ) {
                System.out.println( "Generating " + name );
                method.getCode().code().setProperty( new AVM2LateMultiname( AVM2Namespace.publicNamespace ) );                
            }

            @Override public String toString() { return "avm2#" + name ; }
        };
    }
    
    private CallbackInstruction append( final String name ) {
        return new CallbackInstruction() {
            @Override public void generate( MethodTranslator method ) {
                System.out.println( "Generating " + name );
                method.getCode().code().add();                
            }

            @Override public String toString() { return "avm2#" + name ; }
        };
    }

    private CallbackInstruction equal( final String name ) {
        return new CallbackInstruction() {
            @Override public void generate( MethodTranslator method ) {
                System.out.println( "Generating " + name );
                method.getCode().code().equals();                
            }

            @Override public String toString() { return "avm2#" + name ; }
        };
    }

    private CallbackInstruction trace( final String name ) {
        return new CallbackInstruction() {
            @Override public void generate( MethodTranslator method ) {
                System.out.println( "Generating " + name );
                method.getCode().code().traceTop( "STACK TOP ==> " );                
            }

            @Override public String toString() { return "avm2#" + name ; }
        };
    }

    private CallbackInstruction callVoid( final String name ) {
        return new CallbackInstruction() {
            @Override public void generate( MethodTranslator method ) {
                System.out.println( "Generating " + name );
                method.getCode().code().callPropVoid( 
                   new AVM2LateMultiname( 
                           AVM2StandardNamespace.EmptyPackage.namespace ), 0 );                
            }

            @Override public String toString() { return "avm2#" + name ; }
        };
    }

    private CallbackInstruction call( final String name ) {
        return new CallbackInstruction() {
            @Override public void generate( MethodTranslator method ) {
                System.out.println( "Generating " + name );
                method.getCode().code().callProperty( 
                   new AVM2LateMultiname( 
                           AVM2StandardNamespace.EmptyPackage.namespace ), 0 );                
            }

            @Override public String toString() { return "avm2#" + name ; }
        };
    }

    private CallbackInstruction nothing( final String name ) {
        return new CallbackInstruction() {
            @Override public void generate( MethodTranslator method ) {
                System.out.println( "Generating " + name );
                //do nothing                
            }

            @Override public String toString() { return "avm2#" + name ; }
        };
    }

}
