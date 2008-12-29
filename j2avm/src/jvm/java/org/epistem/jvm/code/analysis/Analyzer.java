package org.epistem.jvm.code.analysis;

import java.util.*;

import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.attributes.CodeAttribute;
import org.epistem.jvm.code.*;
import org.epistem.jvm.code.instructions.*;
import org.epistem.jvm.code.instructions.MethodCall.CallType;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.PrimitiveType;
import org.epistem.jvm.type.ValueType;
import org.epistem.jvm.type.VoidType;

/**
 * Determines the execution contexts for all the instructions in a list
 *
 * @author nickmain
 */
public class Analyzer extends CodeWalker<ExecutionContext> {

    private final Executor exec = new Executor();
    
    //var reads and the values read
    private final Map<VarAccess, Value> varReads = new HashMap<VarAccess, Value>();
    
    private final This thisInstruction;
    private final Argument[] arguments;    
    
    /**
     * @param method the method to analyze. The method must have a code attribute.
     */
    public Analyzer( JVMMethod method ) {
        super( method.attribute( CodeAttribute.class ).instructions );
        
        thisInstruction = ( method.flags.contains( MethodFlag.MethodIsStatic ) ) ?
                              null :
                              new This( method.containerClass.name );
        
        //set up args
        int idx = (thisInstruction == null) ? 0 : 1;
        arguments = new Argument[ method.signature.paramTypes.length ];
        for( int i = 0; i < arguments.length; i++ ) {
            ValueType argType = method.signature.paramTypes[i];
            arguments[i] = new Argument( idx, argType );
            idx += argType.is64Bit() ? 2 : 1;
        }

        //seed the visit queue
        ExecutionContext ctxt = ExecutionContext.forMethod( thisInstruction, arguments ); 
        enqueue( list.first(), ctxt );
        
        analyze();
    }

    /**
     * Get the variable representing the "this" value
     * @return null if the method is static
     */
    public Variable getThis() {
        if( thisInstruction == null ) return null;
        return thisInstruction.variable;
    }
    
    /**
     * Get the variable representing the given argument value
     * @param index the arg index
     */
    public Variable getArg( int index ) {
        return arguments[ index ].variable;
    }
    
    /**
     * Perform the analysis 
     */
    private void analyze() {
        execute();
        
        //TODO: exception handlers
        
        makeVariables();
        
    }
    
    /**
     * Determine and make the variable instances
     */
    private void makeVariables() {
        //map from var-write to set of intersecting var-writes
        Map<VarAccess, Set<VarAccess>> writes = new HashMap<VarAccess, Set<VarAccess>>();
        
        for( Value readValue : varReads.values() ) {
            Set<Instruction> writeInsns = readValue.producers( null );
            Set<VarAccess> commonWrites = new HashSet<VarAccess>();
            LinkedList<VarAccess> queue = new LinkedList<VarAccess>();
            for( Instruction i : writeInsns ) queue.add( (VarAccess) i );
            
            //process queue of writes - in order to build commonWrites set
            while( ! queue.isEmpty() ) {
                VarAccess write = queue.removeFirst();                
                if( commonWrites.contains( write ) ) continue;
                commonWrites.add( write );
                
                //chase the writes that are common to this one
                Set<VarAccess> otherWrites = writes.get( write );
                if( otherWrites != null ) queue.addAll( otherWrites );
            }

            //point all the common writes at the commonWrites set
            for( VarAccess write : commonWrites ) writes.put( write, commonWrites );
        }        
        
        //make variables
        Map<Set<VarAccess>, Variable> variables = new HashMap<Set<VarAccess>, Variable>();
        for( Set<VarAccess> writeSet : writes.values() ) {
            Variable var = variables.get( writeSet );
            if( var == null ) {
                VarAccess aWrite = writeSet.iterator().next();
                var = new Variable( aWrite.index, aWrite.type );
                var.name = "var" + aWrite.offset;
                var.writers.addAll( writeSet );
                variables.put( writeSet, var );
                
                //set the var on all the writes
                for( VarAccess write : writeSet ) {
                    write.variable = var;
                }
            }            
        }
        
        //set the vars for the reads
        for( VarAccess read : varReads.keySet() ) {
            Set<Instruction> writeInsns = varReads.get( read ).producers( null );
            
            read.variable = ((VarAccess) writeInsns.iterator().next()).variable;
        }
        
        //rename this var
        if( thisInstruction != null && thisInstruction.variable != null ) {
            thisInstruction.variable.name = "this";
        }
        
        //rename argument vars
        for( int i = 0; i < arguments.length; i++ ) {
            if( arguments[i].variable != null ) {
                arguments[i].variable.name = "arg" + i;
            }            
        }
    }
    
    /** @see org.epistem.jvm.code.analysis.CodeWalker#visit(org.epistem.jvm.code.Instruction, java.lang.Object) */
    @Override
    protected ExecutionContext visit( Instruction instruction, ExecutionContext context ) {        
        return exec.execute( instruction, context );
    }
    
    /**
     * Simulates instruction execution
     */
    private class Executor extends InstructionVisitor.Impl {

        private ExecutionContext context;
        
        /**
         * Execute the given instruction against the given incoming context
         * and return the resulting context.
         */
        ExecutionContext execute( Instruction instruction, ExecutionContext contextIn ) {
            
            if( instruction instanceof LineNumber ) return contextIn; //skip line numbers
            
            //do not execute if the existing context is the same as the
            //incoming one
            ExecutionContext current = instruction.context;

            //merge incoming with current context
            if( current != null ) {                
                if( ! current.merge( instruction, contextIn ) ) return null;
                //return if the merge caused no significant value changes
            }
            else { //make a new copy of the incoming context
                current = ExecutionContext.copyOf( contextIn );
                instruction.context = current;
            }
            
            //make copy of current context and let the instruction operate on it
            context = ExecutionContext.copyOf( current );            
            instruction.accept( this );
            
            return context;
        }
        
        public void visitArrayAccess( ArrayAccess arrayAccess ) {
            if( arrayAccess.isWrite ) context.pop( 3 );
            else context.pop( 2 ).push( new Value( arrayAccess, arrayAccess.elementType ) );
        }

        public void visitArrayLength( ArrayLength arrayLength ) {
            context.pop( 1 ).push( new Value( arrayLength, PrimitiveType.INT ) );
        }

        public void visitCall( MethodCall call ) {
            context.pop( call.signature.paramTypes.length );
            
            if( call.callType != CallType.Static ) context.pop( 1 ); //instance
            
            if( call.returnType instanceof ValueType ) {
                context.push( new Value( call, (ValueType) call.returnType ) ); 
            }
        }

        public void visitCheckCast( CheckCast cast ) {
            context.pop( 1 ).push( new Value( cast, cast.type ));            
        }

        public void visitConditional( ConditionalBranch branch ) {
            switch( branch.condition ) {
                case IfEq0: 
                case IfNE0: 
                case IfLT0: 
                case IfGE0: 
                case IfGT0: 
                case IfLE0:
                case IfNull: 
                case IfNonNull:
                    context.pop( 1 );
                    break;
                    
                case IfEq:  
                case IfNE:  
                case IfLT:  
                case IfGE:  
                case IfGT:  
                case IfLE:
                case IfSame: 
                case IfNotSame: 
                    context.pop( 2 );
                    break;
            }
        }

        public void visitConstant( ConstantDouble constDouble ) {
            context.push( new Value( constDouble, PrimitiveType.DOUBLE ));            
        }

        public void visitConstant( ConstantFloat constFloat ) {
            context.push( new Value( constFloat, PrimitiveType.FLOAT ));            
        }

        public void visitConstant( ConstantInt constInt ) {
            context.push( new Value( constInt, PrimitiveType.INT ));            
        }

        public void visitConstant( ConstantLong constLong ) {
            context.push( new Value( constLong, PrimitiveType.LONG ));            
        }

        public void visitConstant( ConstantString constString ) {
            context.push( new Value( constString, ObjectType.STRING ));            
        }

        public void visitConstant( ConstantType constType ) {
            context.push( new Value( constType, ObjectType.CLASS ));            
        }

        public void visitConstantNull( ConstantNull constNull ) {
            context.push( new Value( constNull, ObjectType.OBJECT ));            
        }

        public void visitConvert( Convert convert ) {
            context.pop( 1 ).push( new Value( convert, convert.types.toType ));            
        }

        public void visitDup( Dup dup ) {
            int count = dup.dupCount;
            int skip  = dup.skipCount;
            
            Value dup1 = context.pop();
            count -= dup1.type.is64Bit() ? 2 : 1;
            Value dup2 = (count > 0) ? context.pop() : null;
            
            Value skip1 = (skip > 0) ? context.pop() : null;
            skip -= (skip1 != null && skip1.type.is64Bit()) ? 2 : 1;
            Value skip2 = (skip > 0) ? context.pop() : null;
            
            if( dup2 != null ) context.push( dup2 );            
            context.push( dup1 );
            if( skip2 != null ) context.push( skip2 );            
            if( skip1 != null ) context.push( skip1 );            
            if( dup2 != null ) context.push( dup2 );            
            context.push( dup1 );            
        }

        public void visitEnd() {
            // nada            
        }

        public void visitField( FieldAccess fieldAccess ) {
            if( ! fieldAccess.isStatic ) context.pop( 1 ); //instance
            
            if( fieldAccess.isWrite ) {
                context.pop( 1 ); 
            }
            else {
                context.push( new Value( fieldAccess, fieldAccess.type ));
            }            
        }

        public void visitGoto( UnconditionalBranch branch ) {
            // nada            
        }

        public void visitHandler( ExceptionHandler handler ) {
            // nada
        }

        public void visitHandlers() {
            // nada
        }

        public void visitIncrement( Increment increment ) {
            varReads.put( increment, context.locals.get( increment.index ));
            context.locals.put( increment.index, new Value( increment, PrimitiveType.INT ));
        }

        public void visitInstanceOf( InstanceOf instanceOf ) {
            context.pop( 1 ).push( new Value( instanceOf, PrimitiveType.BOOLEAN ));            
        }

        public void visitLabel( Label label ) {
            // nada
        }

        public void visitLineNumber( LineNumber lineNumber ) {
            // nada           
        }

        public void visitMonitor( Monitor monitor ) {
            context.pop( 1 );            
        }

        public void visitNew( New newInstance ) {
            context.push( new Value( newInstance, newInstance.type ));            
        }

        public void visitNewArray( NewArray newArray ) {
            context.pop( newArray.dimensionCount )
                   .push( new Value( newArray, newArray.type ));
        }

        public void visitNop( Nop nop ) {
            // nada
        }

        public void visitNumericOp( NumericOp op ) {
            switch( op.operation ) {
                case Add:
                case Subtract:
                case Multiply:
                case Remainder:
                case Divide: 
                case ShiftLeft:
                case ShiftRight:
                case ShiftRightUnsigned: 
                case And:
                case Or:
                case Xor: {
                    context.pop( 2 ).push( new Value( op, op.type ));
                    break;
                }
                
                case Negate: {
                    context.pop( 1 ).push( new Value( op, op.type ));
                    break;
                }
                                        
                case Compare:
                case CompareG:
                case CompareL:{
                    context.pop( 2 ).push( new Value( op, PrimitiveType.INT ));
                    break;
                }
            }            
        }

        public void visitPop( Pop pop ) {
            if( pop.count == 2 ) {
                if( context.peek( 0 ).type.is64Bit() ) {
                    context.pop( 1 );
                    return;
                }
            }
            
            context.pop( pop.count );
        }

        public void visitReturn( Return ret ) {
            context.pop( (ret.type == VoidType.VOID ) ? 0 : 1 );            
        }

        public void visitStart( InstructionList list ) {
            // nada            
        }

        public void visitSwap( Swap swap ) {
            Value a = context.pop();
            Value b = context.pop();
            
            context.push( a, b );
        }

        public void visitSwitch( Switch switchBranch ) {
            context.pop( 1 );            
        }

        public void visitThrow( Throw throwOp ) {
            context.stack.clear();            
        }

        public void visitVarAccess( VarAccess varAccess ) {
            int index = varAccess.index;            
                                  
            if( varAccess.isWrite ) {                
                context.pop( 1 );
                context.locals.put( index, new Value( varAccess, varAccess.type ) );
            }
            else {
                varReads.put( varAccess, context.locals.get( index ));
                context.push( new Value( varAccess, varAccess.type ));
            }            
        }        
    }    
}
