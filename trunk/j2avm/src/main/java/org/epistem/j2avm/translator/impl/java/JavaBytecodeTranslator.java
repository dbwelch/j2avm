package org.epistem.j2avm.translator.impl.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.epistem.code.LocalValue;
import org.epistem.j2avm.annotations.runtime.Getter;
import org.epistem.j2avm.annotations.runtime.Setter;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.*;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.code.ExceptionHandler;
import org.epistem.jvm.code.InstructionList;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.code.Label;
import org.epistem.jvm.code.analysis.Analyzer;
import org.epistem.jvm.code.analysis.ExecutionContext;
import org.epistem.jvm.code.analysis.Variable;
import org.epistem.jvm.code.instructions.*;
import org.epistem.jvm.code.instructions.MethodCall.CallType;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.type.*;

import com.anotherbigidea.flash.avm2.instruction.Instruction;
import com.anotherbigidea.flash.avm2.model.*;

public class JavaBytecodeTranslator implements InstructionVisitor {

    private final TranslationState state;
    private final TranslatorManager manager;
    private final AVM2Code code;
    
    //mapping from JVM locals to AVM2 locals
    private final Map<Variable, LocalValue<Instruction>> localValues = 
        new HashMap<Variable, LocalValue<Instruction>>();
    
    JavaBytecodeTranslator( TranslationState state ) {
        this.state   = state;
        this.code    = state.codeMethod.code();
        this.manager = state.manager;
        
        //set up the variables for "this" and the arguments
        Analyzer analyzer = state.methodTranslator.jvmMethod.analyzer();
        Variable thisVar = analyzer.getThis();
        if( thisVar != null ) localValues.put( thisVar, code.thisValue );
        
        Variable[] args = analyzer.getArgs();
        for( int i = 0; i < args.length; i++ ) {
            localValues.put( args[i], code.paramValues[i] );
        }
    }

    private LocalValue<Instruction> getLocal( Variable jvmVar ) {
        LocalValue<Instruction> local = localValues.get( jvmVar );
        if( local == null ) {
            local = code.newLocal();
            localValues.put( jvmVar, local );
        }        
        return local;
    }
    
    /** @see org.epistem.jvm.code.InstructionVisitor#visitArrayAccess(org.epistem.jvm.code.instructions.ArrayAccess) */
    public void visitArrayAccess( ArrayAccess arrayAccess ) {
        
        //make a name with runtime name resolution
        AVM2Name name = new AVM2LateMultiname( AVM2Namespace.publicNamespace );
        
        if( arrayAccess.isWrite ) {
            code.setProperty( name );
        }
        else {
            code.getProperty( name );
        }
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitArrayLength(org.epistem.jvm.code.instructions.ArrayLength) */
    public void visitArrayLength( ArrayLength arrayLength ) {
        code.getProperty( "length" );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitCall(org.epistem.jvm.code.instructions.MethodCall) */
    public void visitCall( MethodCall call ) {      
        
        ClassTranslator owner = manager.getClassTranslator( call.owner );        
        MethodTranslator methodTranslator = owner.findMethod( call.signature );
        
        state.requireClass( owner );
        methodTranslator.helper.translateMethodCall( state, methodTranslator, call );
        
        //======================
        
        int     argCount = call.signature.paramTypes.length;
        boolean isVoid   = ( call.returnType == VoidType.VOID );
        
        //find the target method
        AVM2QName methodName;
        JVMMethod jvmMethod;
        MethodTranslator methodTrans;
        ClassTranslator classTrans;
        
        try {
            classTrans = state.manager.getClassTranslation( call.owner.name );
            methodTrans = classTrans.findMethod( call.signature );
            methodName = methodTrans.avm2name;
            jvmMethod = methodTrans.jvmMethod;
            
            //--check for and defer to a translation-helper
            JavaAnnotation translator = jvmMethod.attributes.annotation( Translator.class.getName() );
            if( translator != null ) {
                JVMType helperClass = translator.classValue( "value" );
                TranslationHelper helper = (TranslationHelper) 
                    Class.forName( helperClass.name ).newInstance();
                
                if( helper.translateMethodCall( state, call )) return;
            }
            
        } catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
        

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
            code.getLex( classTrans.avm2name );
            
            //restore args
            for( LocalValue<Instruction> local : args ) {
                code.getLocal( local );
            }
            
            if( isVoid ) code.callPropVoid( methodName, argCount );
            else         code.callProperty( methodName, argCount );            
            
            return;
        }
        
        
        if( call.callType == CallType.Interface ) {
            throw new RuntimeException( "Interface calls not yet implemented" );
        }
        
        supercall:
        if( call.callType == CallType.Special ) {
            //if this is a call to a private method then translate as a non-super
            //call
            if( jvmMethod.flags.contains( MethodFlag.MethodIsPrivate ) ) break supercall; 
            
            if( isVoid ) {
                
                //if this is an <init> call to a Flash native superclass then
                //suppress it since that class will not have an <init> method
                if( ( classTrans.isFlashNative || classTrans.name.equals( "java.lang.Object" ) ) 
                 && jvmMethod.name.equals( "<init>" )) {
                    code.pop(); //the instance
                    return;
                }
                //TODO: obviate the need for the above by using a Transformer
                
                //if this is a call to an <init> method on a new object then
                //it should not be a super call
                if( methodTrans.jvmMethod.name.equals( "<init>" )
                 && call.context.peek( argCount ).producer instanceof New ) {
                    break supercall; 
                }                
                
                code.callSuperVoid( methodName, argCount );
            }
            else {
                code.callSuperProperty( methodName, argCount );            
            }
            
            return;
        }
        
        //--virtual call
        if( isVoid ) code.callPropVoid( methodName, argCount );
        else         code.callProperty( methodName, argCount );            
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitCheckCast(org.epistem.jvm.code.instructions.CheckCast) */
    public void visitCheckCast( CheckCast cast ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConditional(org.epistem.jvm.code.instructions.ConditionalBranch) */
    public void visitConditional( ConditionalBranch branch ) {
        
        //push constants required by some comparisons
        switch( branch.condition ) {
            case IfEq0:
            case IfNE0:
            case IfLT0:
            case IfGE0:
            case IfGT0:
            case IfLE0:
                code.pushInt( 0 );
                break;

            case IfNull:
            case IfNonNull:
                code.pushNull();
                break;
                
            default: break;
        }
        
        switch( branch.condition ) {
            case IfEq0:
            case IfEq:
            case IfNull:
                code.ifeq( branch.defaultTarget );
                break;
                
            case IfNE0:
            case IfNE:
            case IfNonNull:
                code.ifne( branch.defaultTarget );
                break;

            case IfLT0:
            case IfLT:
                code.iflt( branch.defaultTarget );
                break;

            case IfGE0:
            case IfGE:
                code.ifge( branch.defaultTarget );
                break;

            case IfGT0:
            case IfGT:
                code.ifgt( branch.defaultTarget );
                break;

            case IfLE0:
            case IfLE:
                code.ifle( branch.defaultTarget );
                break;
            
            case IfSame:
                code.ifstricteq( branch.defaultTarget );
                break;

            case IfNotSame:
                code.ifstrictne( branch.defaultTarget );
                break;
        }
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantDouble) */
    public void visitConstant( ConstantDouble constDouble ) {
        code.pushDouble( constDouble.value );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantFloat) */
    public void visitConstant( ConstantFloat constFloat ) {
        code.pushDouble( constFloat.value ); //no floats in AVM2 - just use doubles
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantInt) */
    public void visitConstant( ConstantInt constInt ) {
        code.pushInt( constInt.value );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantLong) */
    public void visitConstant( ConstantLong constLong ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantString) */
    public void visitConstant( ConstantString constString ) {
        code.pushString( constString.value );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantType) */
    public void visitConstant( ConstantType constType ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstantNull(org.epistem.jvm.code.instructions.ConstantNull) */
    public void visitConstantNull( ConstantNull constNull ) {
        code.pushNull();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitContext(org.epistem.jvm.code.analysis.ExecutionContext) */
    public void visitContext( ExecutionContext context ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConvert(org.epistem.jvm.code.instructions.Convert) */
    public void visitConvert( Convert convert ) {
        
        PrimitiveType to = convert.types.toType;
        
        if( to == PrimitiveType.INT ) {
            code.convertToInt();            
        }
        else if( to == PrimitiveType.DOUBLE ) {
            code.convertToDouble();
        } 
        else if( to == PrimitiveType.FLOAT ) {
            code.convertToDouble();
        } 
        else {
            throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        }
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitDup(org.epistem.jvm.code.instructions.Dup) */
    public void visitDup( Dup dup ) {
        
        int dupCount = dup.dupCount;
        int skipCount = dup.skipCount;
        
        //reduce count if item is a 64-bit value
        if( dupCount == 2 && dup.context.peek( 0 ).type.is64Bit() ) dupCount = 1; 
        
        //reduce count if item is a 64-bit value
        if( skipCount == 2 && dup.context.peek( dupCount ).type.is64Bit() ) skipCount = 1;
        
        //simple dup
        if( dupCount == 1 && skipCount == 0 ) {
            code.dup();
            return;
        }

        //complex dup - save the stack items into locals
        LocalValue<Instruction> dup1 = code.newLocal();
        code.setLocal( dup1 );

        LocalValue<Instruction> dup2 = null;
        if( dupCount == 2 ) {
            dup2 = code.newLocal();
            code.setLocal( dup2 );
        }

        LocalValue<Instruction> skip1 = null;
        if( skipCount > 0 ) {
            skip1 = code.newLocal();
            code.setLocal( skip1 );
        }
        
        LocalValue<Instruction> skip2 = null;
        if( skipCount == 2 ) {
            skip2 = code.newLocal();
            code.setLocal( skip2 );
        }
        
        //rebuild the stack
        if( dup2 != null ) code.getLocal( dup2 );
        code.getLocal( dup1 );
        if( skip2 != null ) code.getLocal( skip2 );
        if( skip1 != null ) code.getLocal( skip1 );
        if( dup2 != null ) code.getLocal( dup2 );
        code.getLocal( dup1 );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitEnd() */
    public void visitEnd() {
        // nada        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitField(org.epistem.jvm.code.instructions.FieldAccess) */
    public void visitField( FieldAccess fieldAccess ) {

        if( fieldAccess.isStatic ) throw new RuntimeException( "Static field access UNIMPLEMENTED" );

        //make sure target class is required
        state.requireClass( fieldAccess.owner.name );
        
        //find the target field
        AVM2QName fieldName = null;
        try {
            ClassTranslator classTrans = state.manager.getClassTranslation( fieldAccess.owner.name );
            FieldTranslator fieldTrans = classTrans.findField( fieldAccess.name ); 
            fieldName = fieldTrans.avm2name;
        } catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
        
        if( fieldAccess.isWrite ) {
            code.setProperty( fieldName );
        }
        else {
            code.getProperty( fieldName );
        }
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitGoto(org.epistem.jvm.code.instructions.UnconditionalBranch) */
    public void visitGoto( UnconditionalBranch branch ) {
        code.jump( branch.defaultTarget );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitHandler(org.epistem.jvm.code.ExceptionHandler) */
    public void visitHandler( ExceptionHandler handler ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitHandlers() */
    public void visitHandlers() {
        //throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitIncrement(org.epistem.jvm.code.instructions.Increment) */
    public void visitIncrement( Increment increment ) {
        Variable var = increment.variable;
        if( var == null ) throw new RuntimeException( "Null var - need analysis" );
        
        LocalValue<Instruction> local = getLocal( var );
        
        code.getLocal( local );
        code.pushInt( increment.value );
        code.addInts();
        code.setLocal( local );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitInstanceOf(org.epistem.jvm.code.instructions.InstanceOf) */
    public void visitInstanceOf( InstanceOf instanceOf ) {
        
        ObjectOrArrayType type = instanceOf.type;
        
        AVM2QName typeName = ( type instanceof ArrayType ) ?
                                 new AVM2QName( "Array" ) :
                                 new AVM2QName( type.name );
        
        //make sure referenced type is translated
        if( type instanceof ObjectType ) state.requireClass( type.name );
        
        code.isType( typeName );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitLabel(org.epistem.jvm.code.Label) */
    public void visitLabel( Label label ) {
        code.target( label.name() );
        code.label();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitLineNumber(org.epistem.jvm.code.instructions.LineNumber) */
    public void visitLineNumber( LineNumber lineNumber ) {
        // TODO: Implement debug flag and line number op    
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitMonitor(org.epistem.jvm.code.instructions.Monitor) */
    public void visitMonitor( Monitor monitor ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNew(org.epistem.jvm.code.instructions.New) */
    public void visitNew( New newInstance ) {
        
        ClassTranslator clazz;
        
        try {
            clazz = state.manager.getClassTranslation( newInstance.type.name );
            
        } catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
        
        if( clazz.isFlashNative ) throw new RuntimeException( "new Flash Native UNIMPLEMENTED" ); // TODO: new Flash native classes
     
        AVM2QName name = clazz.avm2name;
        
        code.findPropStrict( name );
        code.constructProp( name, 0 );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNewArray(org.epistem.jvm.code.instructions.NewArray) */
    public void visitNewArray( NewArray newArray ) {        
        if( newArray.dimensionCount > 1 ) throw new RuntimeException( "Multi-dim arrays UNIMPLEMENTED" ); // TODO: multi-dim arrays
        
        code.findPropStrict( "Array" );
        code.swap();
        code.constructProp( "Array", 1 );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNop(org.epistem.jvm.code.instructions.Nop) */
    public void visitNop( Nop nop ) {
        code.nop();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNumericOp(org.epistem.jvm.code.instructions.NumericOp) */
    public void visitNumericOp( NumericOp op ) {
        
        if( op.type == PrimitiveType.LONG ) throw new RuntimeException( "Long arithmetic UNIMPLEMENTED" ); //TODO: long arithmetic
        
        switch( op.operation ) {
            case Add:
                if( op.type.isIntType ) code.addInts();
                else                    code.add();
                break;
                
            case Subtract:                 
                if( op.type.isIntType ) code.subtractInts();
                else                    code.subtract();
                break;
                
            case Multiply:
                if( op.type.isIntType ) code.multiplyInts();
                else                    code.multiply();
                break;
                
            case Divide:
                code.divide();
                if( op.type.isIntType ) code.convertToInt();
                break;

            case And: code.bitAnd(); break;
            case Or:  code.bitOr();  break;
            case Xor: code.bitXor(); break;
                
            case Remainder: code.modulo(); break;
                
            case Negate :
                if( op.type.isIntType ) code.negateInts();
                else                    code.negate();
                break;

            case ShiftLeft:          code.shiftLeft(); break;
            case ShiftRight:         code.shiftRight(); break;
            case ShiftRightUnsigned: code.shiftRightUnsigned(); break;
            
            
            case CompareG: 
            case CompareL:
            case Compare:  //is a long operation
                throw new RuntimeException( "UNIMPLEMENTED" ); // TODO: arithmetic operations        
        }
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitPop(org.epistem.jvm.code.instructions.Pop) */
    public void visitPop( Pop pop ) {
        
        int popCount = pop.count;
        
        //reduce count if the item is 64 bit
        if( popCount == 2 && pop.context.peek( 0 ).type.is64Bit() ) popCount = 1;
        
        while( popCount-- > 0 ) code.pop();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitReturn(org.epistem.jvm.code.instructions.Return) */
    public void visitReturn( Return ret ) {
        if( ret.type == VoidType.VOID ) code.returnVoid();
        else code.returnValue();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitStart(org.epistem.jvm.code.InstructionList) */
    public void visitStart( InstructionList list ) {
        // nada
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitSwap(org.epistem.jvm.code.instructions.Swap) */
    public void visitSwap( Swap swap ) {
        code.swap();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitSwitch(org.epistem.jvm.code.instructions.Switch) */
    public void visitSwitch( Switch switchBranch ) {

        //TODO: optimization for JVM Tableswitch - use an AVM2 Lookupswitch
        
        //save the value in a local so it can tested multiple times
        LocalValue<Instruction> valueLocal = code.newLocal();
        code.setLocal( valueLocal );
        
        int caseCount = switchBranch.cases.size();
        for( int i = 0; i < caseCount; i++ ) {
            Switch.Case cas = switchBranch.cases.get( i );
            String target = cas.target;
            int     value = cas.value;
            
            code.getLocal( valueLocal );
            code.pushInt( value );
            code.ifstricteq( target );
        }
        
        //default case
        code.jump( switchBranch.defaultTarget );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitThrow(org.epistem.jvm.code.instructions.Throw) */
    public void visitThrow( Throw throwOp ) {
        code.throwException();        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitVarAccess(org.epistem.jvm.code.instructions.VarAccess) */
    public void visitVarAccess( VarAccess varAccess ) {        
        Variable var = varAccess.variable;
        if( var == null ) {
            //if the analyzer never assigned a variable then it means that this 
            //value is never read - throw it away
            code.pop();
            return;
        }
        
        LocalValue<Instruction> local = getLocal( var );
        
        if( varAccess.isWrite ) {
            code.setLocal( local );
        }
        else {
            code.getLocal( local );
        }
    }
}
