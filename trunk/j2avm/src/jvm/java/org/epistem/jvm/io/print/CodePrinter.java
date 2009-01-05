package org.epistem.jvm.io.print;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.code.ExceptionHandler;
import org.epistem.jvm.code.InstructionList;
import org.epistem.jvm.code.InstructionVisitor;
import org.epistem.jvm.code.Label;
import org.epistem.jvm.code.analysis.ExecutionContext;
import org.epistem.jvm.code.instructions.*;

/**
 * A pretty-printer code visitor.
 * 
 * @author nickmain
 */
public class CodePrinter implements InstructionVisitor {

    private final IndentingPrintWriter ipw;
    
    /**
     * @param ipw the target output
     */
    public CodePrinter( IndentingPrintWriter ipw ) {
        this.ipw = ipw;
    }
    
    /** @see org.epistem.jvm.code.InstructionVisitor#visitContext(org.epistem.jvm.code.analysis.ExecutionContext) */
    public void visitContext( ExecutionContext context ) {
        ipw.println( context );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitArrayAccess(org.epistem.jvm.code.instructions.ArrayAccess) */
    public void visitArrayAccess( ArrayAccess arrayAccess ) {        
        if( arrayAccess.isWrite ) ipw.println( "load[] " + arrayAccess.elementType );
        else                      ipw.println( "store[] " + arrayAccess.elementType );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitArrayLength(org.epistem.jvm.code.instructions.ArrayLength) */
    public void visitArrayLength( ArrayLength arrayLength ) {
        ipw.println( "array-length" );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitCall(org.epistem.jvm.code.instructions.MethodCall) */
    public void visitCall( MethodCall call ) {
        ipw.println( "call " + call.callType.name().toLowerCase() + " " + call.owner + "::" + call.signature );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitCheckCast(org.epistem.jvm.code.instructions.CheckCast) */
    public void visitCheckCast( CheckCast cast ) {
        ipw.println( "check-cast " + cast.type );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConditional(org.epistem.jvm.code.instructions.ConditionalBranch) */
    public void visitConditional( ConditionalBranch branch ) {
        ipw.println( branch.condition + " " + branch.defaultTarget );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantDouble) */
    public void visitConstant( ConstantDouble constDouble ) {
        ipw.println( "push " + constDouble.value );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantFloat) */
    public void visitConstant( ConstantFloat constFloat ) {
        ipw.println( "push " + constFloat.value + "f" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantInt) */
    public void visitConstant( ConstantInt constInt ) {
        ipw.println( "push " + constInt.value );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantLong) */
    public void visitConstant( ConstantLong constLong ) {
        ipw.println( "push " + constLong.value + "L" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantString) */
    public void visitConstant( ConstantString constString ) {
        ipw.print( "push " );
        ipw.writeDoubleQuotedString( constString.value );
        ipw.println();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstant(org.epistem.jvm.code.instructions.ConstantType) */
    public void visitConstant( ConstantType constType ) {
        ipw.println( "push " + constType.type );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConstantNull(org.epistem.jvm.code.instructions.ConstantNull) */
    public void visitConstantNull( ConstantNull constNull ) {
        ipw.println( "push null" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitConvert(org.epistem.jvm.code.instructions.Convert) */
    public void visitConvert( Convert convert ) {
        ipw.println( "convert " + convert.types.fromType + " -> " + convert.types.toType );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitDup(org.epistem.jvm.code.instructions.Dup) */
    public void visitDup( Dup dup ) {
        ipw.println( "dup count=" + dup.dupCount + ", skip=" + dup.skipCount );        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitEnd() */
    public void visitEnd() {
        // nada        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitField(org.epistem.jvm.code.instructions.FieldAccess) */
    public void visitField( FieldAccess fieldAccess ) {
        if( fieldAccess.isWrite ) ipw.print( "store " );
        else ipw.print( "load " );
        
        if( fieldAccess.isStatic ) ipw.print( "static " );

        ipw.println( fieldAccess.owner + "::" + fieldAccess.name );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitGoto(org.epistem.jvm.code.instructions.UnconditionalBranch) */
    public void visitGoto( UnconditionalBranch branch ) {
        ipw.println( "goto " + branch.defaultTarget );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitHandler(org.epistem.jvm.code.ExceptionHandler) */
    public void visitHandler( ExceptionHandler handler ) {
        ipw.println( "try( " + handler.start + " -> " + handler.end + 
                     " ) catch( " + handler.exceptionType + " ) -> " + handler.handler );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitHandlers() */
    public void visitHandlers() {
        ipw.println();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitIncrement(org.epistem.jvm.code.instructions.Increment) */
    public void visitIncrement( Increment increment ) {
        
        if( increment.variable != null ) {
            ipw.println( increment.variable.name + " += " + increment.value );   
        }
        else {
            ipw.println( "var " + increment.index + " += " + increment.value );
        }        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitInstanceOf(org.epistem.jvm.code.instructions.InstanceOf) */
    public void visitInstanceOf( InstanceOf instanceOf ) {
        ipw.println( "instanceof " + instanceOf.type );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitLabel(org.epistem.jvm.code.Label) */
    public void visitLabel( Label label ) {
        ipw.unindent();
        ipw.println( label.name() + ":" );
        ipw.indent();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitMonitor(org.epistem.jvm.code.instructions.Monitor) */
    public void visitMonitor( Monitor monitor ) {
        if( monitor.isExit ) ipw.println( "monitor-exit" );
        else ipw.println( "monitor-enter" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNew(org.epistem.jvm.code.instructions.New) */
    public void visitNew( New newInstance ) {
        ipw.println( "new " + newInstance.type );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNewArray(org.epistem.jvm.code.instructions.NewArray) */
    public void visitNewArray( NewArray newArray ) {
        ipw.println( "new " + newArray.type + " (" + newArray.dimensionCount + ")" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNop(org.epistem.jvm.code.instructions.Nop) */
    public void visitNop( Nop nop ) {
        ipw.println( "nop" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitNumericOp(org.epistem.jvm.code.instructions.NumericOp) */
    public void visitNumericOp( NumericOp op ) {
        ipw.println( op.operation.name().toLowerCase() + " (" + op.type + ")" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitPop(org.epistem.jvm.code.instructions.Pop) */
    public void visitPop( Pop pop ) {
        ipw.println( "pop " + pop.count );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitReturn(org.epistem.jvm.code.instructions.Return) */
    public void visitReturn( Return ret ) {
        ipw.println( "return" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitStart(org.epistem.jvm.code.InstructionList) */
    public void visitStart( InstructionList list ) {
        // nada        
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitSwap(org.epistem.jvm.code.instructions.Swap) */
    public void visitSwap( Swap swap ) {
        ipw.println( "swap" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitSwitch(org.epistem.jvm.code.instructions.Switch) */
    public void visitSwitch( Switch switchBranch ) {
        ipw.println( "switch {" );
        ipw.indent();
        
        for( Switch.Case c : switchBranch.cases ) {
            ipw.println( c.value + " -> " + c.target );
        }
        
        ipw.println( "default -> " + switchBranch.defaultTarget );
        
        ipw.println( "}" );
        ipw.unindent();
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitThrow(org.epistem.jvm.code.instructions.Throw) */
    public void visitThrow( Throw throwOp ) {
        ipw.println( "throw" );
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitVarAccess(org.epistem.jvm.code.instructions.VarAccess) */
    public void visitVarAccess( VarAccess varAccess ) {
        if( varAccess.isWrite ) ipw.print( "store " );
        else ipw.write( "load " );
        
        if( varAccess.variable != null ) {
            ipw.println( varAccess.variable.name );
        }
        else {
            ipw.println( varAccess.type + "@" + varAccess.index );
        }
    }

    /** @see org.epistem.jvm.code.InstructionVisitor#visitLineNumber(org.epistem.jvm.code.instructions.LineNumber) */
    public void visitLineNumber( LineNumber lineNumber ) {
        ipw.println( "<" + lineNumber.lineNumber + ">" );
    }   
}
