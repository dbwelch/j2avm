package org.epistem.jvm.code;

import org.epistem.jvm.code.analysis.ExecutionContext;
import org.epistem.jvm.code.instructions.*;

/**
 * Visitor interface for instructions
 *
 * @author nickmain
 */
public interface InstructionVisitor {

    /**
     * Start visiting an instruction list.
     */
    public void visitStart( InstructionList list );
    
    /**
     * End of the instructions - any exception handlers are passed next
     */
    public void visitHandlers();
    
    /**
     * Called when the end of the instruction list has been reached
     */
    public void visitEnd();
    
    /**
     * Optional - for passing the execution context of the following instruction
     */
    public void visitContext( ExecutionContext context );
    
    public void visitLabel( Label label );

    public void visitNop( Nop nop );
    
    public void visitConstantNull( ConstantNull constNull );
    
    public void visitConstant( ConstantInt constInt );
    public void visitConstant( ConstantDouble constDouble );
    public void visitConstant( ConstantFloat constFloat );
    public void visitConstant( ConstantLong constLong );
    public void visitConstant( ConstantString constString );
    public void visitConstant( ConstantType constType );

    public void visitNumericOp( NumericOp op );
        
    public void visitConditional( ConditionalBranch branch );
    public void visitGoto( UnconditionalBranch branch );    
    public void visitSwitch( Switch switchBranch );
    public void visitReturn( Return ret );
    public void visitThrow( Throw throwOp );
    
    public void visitField( FieldAccess fieldAccess );
    public void visitCall( MethodCall call );
    
    public void visitArrayLength( ArrayLength arrayLength );
    public void visitArrayAccess( ArrayAccess arrayAccess );

    public void visitConvert( Convert convert );
    public void visitCheckCast( CheckCast cast );
    public void visitInstanceOf( InstanceOf instanceOf );
    
    public void visitMonitor( Monitor monitor );

    public void visitNew( New newInstance );
    public void visitNewArray( NewArray newArray );

    public void visitVarAccess( VarAccess varAccess );

    public void visitIncrement( Increment increment );

    public void visitPop( Pop pop );
    public void visitDup( Dup dup );
    public void visitSwap( Swap swap );
    
    public void visitLineNumber( LineNumber lineNumber );
    
    public void visitHandler( ExceptionHandler handler );
    
    /**
     * Implementation of the interface
     */
    public static class Impl implements InstructionVisitor {
        public void visitContext( ExecutionContext context ) {}
        public void visitArrayAccess( ArrayAccess arrayAccess ) {}
        public void visitArrayLength( ArrayLength arrayLength ) {}
        public void visitCall( MethodCall call ) {}
        public void visitCheckCast( CheckCast cast ) {}
        public void visitConditional( ConditionalBranch branch ) {}
        public void visitConstant( ConstantDouble constDouble ) {}
        public void visitConstant( ConstantFloat constFloat ) {}
        public void visitConstant( ConstantInt constInt ) {}
        public void visitConstant( ConstantLong constLong ) {}
        public void visitConstant( ConstantString constString ) {}
        public void visitConstant( ConstantType constType ) {}
        public void visitConstantNull( ConstantNull constNull ) {}
        public void visitConvert( Convert convert ) {}
        public void visitDup( Dup dup ) {}
        public void visitEnd() {}
        public void visitField( FieldAccess fieldAccess ) {}
        public void visitGoto( UnconditionalBranch branch ) {}
        public void visitHandler( ExceptionHandler handler ) {}
        public void visitHandlers() {}
        public void visitIncrement( Increment increment ) {}
        public void visitInstanceOf( InstanceOf instanceOf ) {}
        public void visitLabel( Label label ) {}
        public void visitLineNumber( LineNumber lineNumber ) {}
        public void visitMonitor( Monitor monitor ) {}
        public void visitNew( New newInstance ) {}
        public void visitNewArray( NewArray newArray ) {}
        public void visitNop( Nop nop ) {}
        public void visitNumericOp( NumericOp op ) {}
        public void visitPop( Pop pop ) {}
        public void visitReturn( Return ret ) {}
        public void visitStart( InstructionList list ) {}
        public void visitSwap( Swap swap ) {}
        public void visitSwitch( Switch switchBranch ) {}
        public void visitThrow( Throw throwOp ) {}
        public void visitVarAccess( VarAccess varAccess ) {}
    }
}
