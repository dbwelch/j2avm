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
    
    /**
     * Implementation that delegates to another
     */
    public static class Delegator implements InstructionVisitor {
        protected InstructionVisitor delegee;
        
        /**
         * @param delegee may be null
         */
        public Delegator( InstructionVisitor delegee ) {
            this.delegee = delegee;
        }

        public void visitArrayAccess( ArrayAccess arrayAccess ) {
            if( delegee != null ) delegee .visitArrayAccess( arrayAccess );
        }

        public void visitArrayLength( ArrayLength arrayLength ) {
            if( delegee != null ) delegee .visitArrayLength( arrayLength );
        }

        public void visitCall( MethodCall call ) {
            if( delegee != null ) delegee .visitCall( call );
        }

        public void visitCheckCast( CheckCast cast ) {
            if( delegee != null ) delegee .visitCheckCast( cast );
        }

        public void visitConditional( ConditionalBranch branch ) {
            if( delegee != null ) delegee .visitConditional( branch );
        }

        public void visitConstant( ConstantDouble constDouble ) {
            if( delegee != null ) delegee .visitConstant( constDouble );
        }

        public void visitConstant( ConstantFloat constFloat ) {
            if( delegee != null ) delegee .visitConstant( constFloat );
        }

        public void visitConstant( ConstantInt constInt ) {
            if( delegee != null ) delegee .visitConstant( constInt );
        }

        public void visitConstant( ConstantLong constLong ) {
            if( delegee != null ) delegee .visitConstant( constLong );
        }

        public void visitConstant( ConstantString constString ) {
            if( delegee != null ) delegee .visitConstant( constString );
        }

        public void visitConstant( ConstantType constType ) {
            if( delegee != null ) delegee .visitConstant( constType );
        }

        public void visitConstantNull( ConstantNull constNull ) {
            if( delegee != null ) delegee .visitConstantNull( constNull );
        }

        public void visitContext( ExecutionContext context ) {
            if( delegee != null ) delegee .visitContext( context );
        }

        public void visitConvert( Convert convert ) {
            if( delegee != null ) delegee .visitConvert( convert );
        }

        public void visitDup( Dup dup ) {
            if( delegee != null ) delegee .visitDup( dup );
        }

        public void visitEnd() {
            if( delegee != null ) delegee .visitEnd();
        }

        public void visitField( FieldAccess fieldAccess ) {
            if( delegee != null ) delegee .visitField( fieldAccess );
        }

        public void visitGoto( UnconditionalBranch branch ) {
            if( delegee != null ) delegee .visitGoto( branch );
        }

        public void visitHandler( ExceptionHandler handler ) {
            if( delegee != null ) delegee .visitHandler( handler );
        }

        public void visitHandlers() {
            if( delegee != null ) delegee .visitHandlers();
        }

        public void visitIncrement( Increment increment ) {
            if( delegee != null ) delegee .visitIncrement( increment );
        }

        public void visitInstanceOf( InstanceOf instanceOf ) {
            if( delegee != null ) delegee .visitInstanceOf( instanceOf );
        }

        public void visitLabel( Label label ) {
            if( delegee != null ) delegee .visitLabel( label );
        }

        public void visitLineNumber( LineNumber lineNumber ) {
            if( delegee != null ) delegee .visitLineNumber( lineNumber );
        }

        public void visitMonitor( Monitor monitor ) {
            if( delegee != null ) delegee .visitMonitor( monitor );
        }

        public void visitNew( New newInstance ) {
            if( delegee != null ) delegee .visitNew( newInstance );
        }

        public void visitNewArray( NewArray newArray ) {
            if( delegee != null ) delegee .visitNewArray( newArray );
        }

        public void visitNop( Nop nop ) {
            if( delegee != null ) delegee .visitNop( nop );
        }

        public void visitNumericOp( NumericOp op ) {
            if( delegee != null ) delegee .visitNumericOp( op );
        }

        public void visitPop( Pop pop ) {
            if( delegee != null ) delegee .visitPop( pop );
        }

        public void visitReturn( Return ret ) {
            if( delegee != null ) delegee .visitReturn( ret );
        }

        public void visitStart( InstructionList list ) {
            if( delegee != null ) delegee .visitStart( list );
        }

        public void visitSwap( Swap swap ) {
            if( delegee != null ) delegee .visitSwap( swap );
        }

        public void visitSwitch( Switch switchBranch ) {
            if( delegee != null ) delegee .visitSwitch( switchBranch );
        }

        public void visitThrow( Throw throwOp ) {
            if( delegee != null ) delegee .visitThrow( throwOp );
        }

        public void visitVarAccess( VarAccess varAccess ) {
            if( delegee != null ) delegee .visitVarAccess( varAccess );
        }        
    }
}
