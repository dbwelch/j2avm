package org.epistem.j2avm.translator.impl.java;

import java.util.HashMap;
import java.util.Map;

import org.epistem.code.LocalValue;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.ExceptionHandler;
import org.epistem.jvm.code.InstructionList;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.code.Label;
import org.epistem.jvm.code.analysis.Analyzer;
import org.epistem.jvm.code.analysis.ExecutionContext;
import org.epistem.jvm.code.analysis.Value;
import org.epistem.jvm.code.analysis.Variable;
import org.epistem.jvm.code.instructions.*;
import org.epistem.jvm.code.instructions.MethodCall.CallType;
import org.epistem.jvm.type.*;

import com.anotherbigidea.flash.avm2.instruction.Instruction;
import com.anotherbigidea.flash.avm2.model.*;

public class JavaBytecodeTranslator implements InstructionVisitor {

    private final TranslatorManager manager;
    private final MethodTranslator method;
    private final AVM2Code code;
    private final JVMMethod jvmMethod;
    
    //mapping from JVM locals to AVM2 locals
    private final Map<Variable, LocalValue<Instruction>> localValues = 
        new HashMap<Variable, LocalValue<Instruction>>();
    
    JavaBytecodeTranslator( MethodTranslator method, JVMMethod jvmMethod ) {
        this.method  = method;
        this.code    = method.getCode().code();
        this.manager = method.getClassTranslator().getManager();
        this.jvmMethod = jvmMethod;
        
        //set up the variables for "this" and the arguments
        Analyzer analyzer = jvmMethod.analyzer();
        Variable thisVar  = analyzer.getThis();
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
        
        ClassTranslator owner = manager.translatorForClass( call.owner );        
        int argCount = call.signature.paramTypes.length;

        MethodTranslator methodTranslator;
        try {
            methodTranslator = owner.getMethodTranslator( call.signature );
        } catch( NoSuchMethodException e ) {
            throw new RuntimeException( e );
        }
        
        //determine whether a super call
        boolean isSuper = false;
        if( call.callType == CallType.Special ) {
            Value instance = call.context.stack.get( argCount );
            ObjectType instType = (ObjectType) instance.type; //only object types have methods
            
            //if the method owner is a superclass of the instance then it is a supercall
            if( ! instType.equals( call.owner )) {
                ClassTranslator instTran = manager.translatorForClass( instType );                
                isSuper = ClassTranslatorBase.isSuperclassOf( instTran, owner );
            }    
            
            //Note - special calls to self are private calls or non-super <init>
        }
        
        manager.requireClass( owner );
        methodTranslator.translateCall( method, call, isSuper );        
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

        //make sure target class is required
        manager.requireClass( fieldAccess.owner );
        
        //find the target field
        ClassTranslator classTrans = manager.translatorForClass( fieldAccess.owner );
        FieldTranslator fieldTrans;
        try {
            fieldTrans = classTrans.getFieldTranslator( fieldAccess.name );
        } catch( NoSuchFieldException e ) {
            throw new RuntimeException( e );
        } 

        //detect whether this is a super access
        boolean isSuper = false;
        if( ! fieldAccess.isStatic ) {
            Value instance = fieldAccess.context.stack.get( fieldAccess.isWrite ? 1 : 0 );
            ObjectType instType = (ObjectType) instance.type; //only object types have fields
            ObjectType targType = fieldAccess.owner;
            
            //if the field owner is a superclass of the instance then it is a super access
            if( ! instType.equals( targType )) {
                ClassTranslator instTran = manager.translatorForClass( instType );                
                isSuper = ClassTranslatorBase.isSuperclassOf( instTran, classTrans );
            }
        }
        
        if( fieldAccess.isWrite ) {
            fieldTrans.translateWrite( method, fieldAccess, isSuper );
        }
        else {
            fieldTrans.translateRead( method, fieldAccess, isSuper );
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
        
        if( type instanceof ArrayType )  {
            //TODO: need to check array type - maybe by tagging the array somehow ?
            code.isType( new AVM2QName( "Array" ));
            return;
        }

        ClassTranslator trans = manager.translatorForClass( (ObjectType) type );
        manager.requireClass( trans );        
        trans.translateInstanceOf( method, instanceOf );        
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
        manager.translatorForClass( newInstance.type )
               .translateInstantiation( method, newInstance );        
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
