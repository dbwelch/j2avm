package org.epistem.j2avm.translator;

import java.util.HashMap;
import java.util.Map;

import org.epistem.code.LocalValue;
import org.epistem.j2avm.annotations.runtime.Getter;
import org.epistem.j2avm.annotations.runtime.Setter;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.JVMMethod;
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
import org.epistem.jvm.type.PrimitiveType;
import org.epistem.jvm.type.ValueType;
import org.epistem.jvm.type.VoidType;

import com.anotherbigidea.flash.avm2.instruction.Instruction;
import com.anotherbigidea.flash.avm2.model.AVM2Code;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

public class BytecodeTranslator implements InstructionVisitor {

    private final TranslationState state;
    private final AVM2Code code;
    
    //mapping from JVM locals to AVM2 locals
    private final Map<Variable, LocalValue<Instruction>> localValues = 
        new HashMap<Variable, LocalValue<Instruction>>();
    
    BytecodeTranslator( TranslationState state ) {
        this.state  = state;
        this.code   = state.codeMethod.code();
        
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
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitArrayLength(org.epistem.jvm.code.instructions.ArrayLength) */
    public void visitArrayLength( ArrayLength arrayLength ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitCall(org.epistem.jvm.code.instructions.MethodCall) */
    public void visitCall( MethodCall call ) {        
        if( call.callType == CallType.Static ) throw new RuntimeException( "Static calls not yet implemented" );
        if( call.callType == CallType.Interface ) throw new RuntimeException( "Interface calls not yet implemented" );
        
        int argCount = call.signature.paramTypes.length;
        
        //make sure target class is required
        state.requireClass( call.owner.name );
        
        //find the target method
        AVM2QName methodName;
        JVMMethod jvmMethod;
        MethodTranslator methodTrans;
        ClassTranslator classTrans;
        boolean isGetter = false;
        boolean isSetter = false;
        
        try {
            classTrans = state.manager.getClassTranslation( call.owner.name );
            methodTrans = classTrans.findMethod( call.signature );
            methodName = methodTrans.avm2name;
            jvmMethod = methodTrans.jvmMethod;
            
            isGetter = jvmMethod.attributes.annotation( Getter.class.getName() ) != null;
            isSetter = jvmMethod.attributes.annotation( Setter.class.getName() ) != null;
        
        } catch( Exception ex ) {
            throw new RuntimeException( ex );
        }

        //turn getter/setter calls into field accesses
        if( isGetter || isSetter ) {
            AVM2QName fieldName = new AVM2QName( methodTrans.avm2name.namespace, 
                                                 NameUtils.nameFromAccessor( jvmMethod.name ));
            
            if( isGetter ) code.getProperty( fieldName );
            else code.setProperty( fieldName );
                
            return;
        }
        
        supercall:
        if( call.callType == CallType.Special ) {
            //if this is a call to a private method then translate as a non-super
            //call
            if( jvmMethod.flags.contains( MethodFlag.MethodIsPrivate ) ) break supercall; 
            
            if( call.returnType == VoidType.VOID ) {
                
                //if this is an <init> call to a Flash native superclass then
                //suppress it since that class will not have an <init> method
                if( classTrans.isFlashNative && jvmMethod.name.equals( "<init>" )) {
                    code.pop(); //the instance
                    return;
                }
                
                code.callSuperVoid( methodName, argCount );
            }
            else {
                code.callSuperProperty( methodName, argCount );            
            }
            
            return;
        }
        
        //virtual call
        if( call.returnType == VoidType.VOID ) {
            code.callPropVoid( methodName, argCount );
        }
        else {
            code.callProperty( methodName, argCount );            
        }
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitCheckCast(org.epistem.jvm.code.instructions.CheckCast) */
    public void visitCheckCast( CheckCast cast ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConditional(org.epistem.jvm.code.instructions.ConditionalBranch) */
    public void visitConditional( ConditionalBranch branch ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
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
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
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
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitInstanceOf(org.epistem.jvm.code.instructions.InstanceOf) */
    public void visitInstanceOf( InstanceOf instanceOf ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitLabel(org.epistem.jvm.code.Label) */
    public void visitLabel( Label label ) {
        code.target( label.name() );
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
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNewArray(org.epistem.jvm.code.instructions.NewArray) */
    public void visitNewArray( NewArray newArray ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNop(org.epistem.jvm.code.instructions.Nop) */
    public void visitNop( Nop nop ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNumericOp(org.epistem.jvm.code.instructions.NumericOp) */
    public void visitNumericOp( NumericOp op ) {
        
        switch( op.operation ) {
            case Add:
                if( op.type.isIntType ) {
                    code.addInts();
                    return;
                }
                
            case Subtract:
            case Multiply:
            case Divide:
            case Remainder:
            case Negate :
            case ShiftLeft:
            case ShiftRight:
            case ShiftRightUnsigned:
            case And:
            case Or:
            case Xor:
            case Compare:
            case CompareG:
            case CompareL:
                break;
        }
        
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitPop(org.epistem.jvm.code.instructions.Pop) */
    public void visitPop( Pop pop ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
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
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitSwitch(org.epistem.jvm.code.instructions.Switch) */
    public void visitSwitch( Switch switchBranch ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitThrow(org.epistem.jvm.code.instructions.Throw) */
    public void visitThrow( Throw throwOp ) {
        throw new RuntimeException( "UNIMPLEMENTED" ); // TODO Auto-generated method stub
        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitVarAccess(org.epistem.jvm.code.instructions.VarAccess) */
    public void visitVarAccess( VarAccess varAccess ) {        
        Variable var = varAccess.variable;
        if( var == null ) throw new RuntimeException( "Null var - need analysis" );
        
        LocalValue<Instruction> local = getLocal( var );
        
        if( varAccess.isWrite ) {
            code.setLocal( local );
        }
        else {
            code.getLocal( local );
        }
    }
}
