package org.epistem.j2avm.bytecode;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.*;

/**
 * MethodVisitor implementation that drives the BytecodeVisitor interface.
 * Only instruction visit methods do anything.
 *
 * @author nickmain
 */
public class BytecodeDriver implements MethodVisitor {

    private static final Type OBJECT = Type.getObjectType( "java/lang/Object" );
    
    private final BytecodeVisitor bytecode;
    
    public BytecodeDriver( BytecodeVisitor bytecode ) {
        this.bytecode = bytecode;
    }

    /** @see org.objectweb.asm.MethodVisitor#visitAnnotation(java.lang.String, boolean) */
    public AnnotationVisitor visitAnnotation( String arg0, boolean arg1 ) {
        // nada
        return null;
    }

    /** @see org.objectweb.asm.MethodVisitor#visitAnnotationDefault() */
    public AnnotationVisitor visitAnnotationDefault() {
        // nada
        return null;
    }

    /** @see org.objectweb.asm.MethodVisitor#visitAttribute(org.objectweb.asm.Attribute) */
    public void visitAttribute( Attribute arg0 ) {
        // nada
        
    }

    /** @see org.objectweb.asm.MethodVisitor#visitCode() */
    public void visitCode() {
        // nada        
    }

    /** @see org.objectweb.asm.MethodVisitor#visitEnd() */
    public void visitEnd() {
        bytecode.end();        
    }

    /** @see org.objectweb.asm.MethodVisitor#visitFieldInsn(int, java.lang.String, java.lang.String, java.lang.String) */
    public void visitFieldInsn( int opcode, String owner, String name, String desc ) {
        
        Type ownerType = Type.getObjectType( owner );
        Type type      = Type.getType( desc );
        
        switch( opcode ) {
            case GETSTATIC: bytecode.getStatic( ownerType, name, type ); break;
            case PUTSTATIC: bytecode.putStatic( ownerType, name, type ); break;
            case GETFIELD:  bytecode.getField ( ownerType, name, type ); break;
            case PUTFIELD:  bytecode.putField ( ownerType, name, type ); break;
            default: throw new RuntimeException();
        }        
    }

    /** @see org.objectweb.asm.MethodVisitor#visitFrame(int, int, java.lang.Object[], int, java.lang.Object[]) */
    public void visitFrame( int arg0, int arg1, Object[] arg2, int arg3, Object[] arg4 ) {
        // nada        
    }

    /** @see org.objectweb.asm.MethodVisitor#visitIincInsn(int, int) */
    public void visitIincInsn( int var, int increment ) {
        bytecode.increment( var, increment );
    }

    /** @see org.objectweb.asm.MethodVisitor#visitInsn(int) */
    public void visitInsn( int opcode ) {
        switch( opcode ) {
            case NOP:         bytecode.nop(); break;
            
            case ACONST_NULL: bytecode.constantNull(); break;
            case ICONST_M1:   bytecode.constant( -1 ); break;
            case ICONST_0:    bytecode.constant( 0 ); break;
            case ICONST_1:    bytecode.constant( 1 ); break;
            case ICONST_2:    bytecode.constant( 2 ); break;
            case ICONST_3:    bytecode.constant( 3 ); break;
            case ICONST_4:    bytecode.constant( 4 ); break;
            case ICONST_5:    bytecode.constant( 5 ); break;
            case LCONST_0:    bytecode.constant( 0L ); break;
            case LCONST_1:    bytecode.constant( 1L ); break;
            case FCONST_0:    bytecode.constant( 0f ); break;
            case FCONST_1:    bytecode.constant( 1f ); break;
            case FCONST_2:    bytecode.constant( 2f ); break;
            case DCONST_0:    bytecode.constant( 0.0 ); break;
            case DCONST_1:    bytecode.constant( 1.0 ); break;
            
            case IALOAD:      bytecode.loadElement( Type.INT_TYPE ); break;
            case LALOAD:      bytecode.loadElement( Type.LONG_TYPE ); break;
            case FALOAD:      bytecode.loadElement( Type.FLOAT_TYPE ); break;
            case DALOAD:      bytecode.loadElement( Type.DOUBLE_TYPE ); break;
            case AALOAD:      bytecode.loadElement( OBJECT ); break;
            case BALOAD:      bytecode.loadElement( Type.BYTE_TYPE ); break;
            case CALOAD:      bytecode.loadElement( Type.CHAR_TYPE ); break;
            case SALOAD:      bytecode.loadElement( Type.SHORT_TYPE ); break;
            case IASTORE:     bytecode.storeElement( Type.INT_TYPE ); break;
            case LASTORE:     bytecode.storeElement( Type.LONG_TYPE ); break;
            case FASTORE:     bytecode.storeElement( Type.FLOAT_TYPE ); break;
            case DASTORE:     bytecode.storeElement( Type.DOUBLE_TYPE ); break;
            case AASTORE:     bytecode.storeElement( OBJECT ); break;
            case BASTORE:     bytecode.storeElement( Type.BYTE_TYPE ); break;
            case CASTORE:     bytecode.storeElement( Type.CHAR_TYPE ); break;
            case SASTORE:     bytecode.storeElement( Type.SHORT_TYPE ); break;
            
            case POP:     bytecode.pop( 1 ); break;
            case POP2:    bytecode.pop( 2 ); break;
            case DUP:     bytecode.dup( 1, 0 ); break;
            case DUP_X1:  bytecode.dup( 1, 1 ); break;
            case DUP_X2:  bytecode.dup( 1, 1 ); break;
            case DUP2:    bytecode.dup( 2, 0 ); break;
            case DUP2_X1: bytecode.dup( 2, 1 ); break;
            case DUP2_X2: bytecode.dup( 2, 2 ); break;
            case SWAP:    bytecode.swap(); break;
            
            case IADD: bytecode.add( Type.INT_TYPE ); break;
            case LADD: bytecode.add( Type.LONG_TYPE ); break;
            case FADD: bytecode.add( Type.FLOAT_TYPE ); break;
            case DADD: bytecode.add( Type.DOUBLE_TYPE ); break;
            case ISUB: bytecode.subtract( Type.INT_TYPE ); break;
            case LSUB: bytecode.subtract( Type.LONG_TYPE ); break;
            case FSUB: bytecode.subtract( Type.FLOAT_TYPE ); break;
            case DSUB: bytecode.subtract( Type.DOUBLE_TYPE ); break;
            case IMUL: bytecode.multiply( Type.INT_TYPE ); break;
            case LMUL: bytecode.multiply( Type.LONG_TYPE ); break;
            case FMUL: bytecode.multiply( Type.FLOAT_TYPE ); break;
            case DMUL: bytecode.multiply( Type.DOUBLE_TYPE ); break;
            case IDIV: bytecode.divide( Type.INT_TYPE ); break;
            case LDIV: bytecode.divide( Type.LONG_TYPE ); break;
            case FDIV: bytecode.divide( Type.FLOAT_TYPE ); break;
            case DDIV: bytecode.divide( Type.DOUBLE_TYPE ); break;
            case IREM: bytecode.remainder( Type.INT_TYPE ); break;
            case LREM: bytecode.remainder( Type.LONG_TYPE ); break;
            case FREM: bytecode.remainder( Type.FLOAT_TYPE ); break;
            case DREM: bytecode.remainder( Type.DOUBLE_TYPE ); break;
            case INEG: bytecode.negate( Type.INT_TYPE ); break;
            case LNEG: bytecode.negate( Type.LONG_TYPE ); break;
            case FNEG: bytecode.negate( Type.FLOAT_TYPE ); break;
            case DNEG: bytecode.negate( Type.DOUBLE_TYPE ); break;
            
            case ISHL:  bytecode.shiftLeft( Type.INT_TYPE ); break;
            case LSHL:  bytecode.shiftLeft( Type.LONG_TYPE ); break;
            case ISHR:  bytecode.shiftRight( Type.INT_TYPE ); break;
            case LSHR:  bytecode.shiftRight( Type.LONG_TYPE ); break;
            case IUSHR: bytecode.shiftRightU( Type.INT_TYPE ); break;
            case LUSHR: bytecode.shiftRightU( Type.LONG_TYPE ); break;
            case IAND:  bytecode.and( Type.INT_TYPE ); break;
            case LAND:  bytecode.and( Type.LONG_TYPE ); break;
            case IOR:   bytecode.or( Type.INT_TYPE ); break;
            case LOR:   bytecode.or( Type.LONG_TYPE ); break;
            case IXOR:  bytecode.xor( Type.INT_TYPE ); break;
            case LXOR:  bytecode.xor( Type.LONG_TYPE ); break;
            
            case I2L: bytecode.convert( Type.INT_TYPE, Type.LONG_TYPE ); break;
            case I2F: bytecode.convert( Type.INT_TYPE, Type.FLOAT_TYPE ); break;
            case I2D: bytecode.convert( Type.INT_TYPE, Type.DOUBLE_TYPE ); break;
            case L2I: bytecode.convert( Type.LONG_TYPE, Type.INT_TYPE ); break;
            case L2F: bytecode.convert( Type.LONG_TYPE, Type.FLOAT_TYPE ); break;
            case L2D: bytecode.convert( Type.LONG_TYPE, Type.DOUBLE_TYPE ); break;
            case F2I: bytecode.convert( Type.FLOAT_TYPE, Type.INT_TYPE ); break;
            case F2L: bytecode.convert( Type.FLOAT_TYPE, Type.LONG_TYPE ); break;
            case F2D: bytecode.convert( Type.FLOAT_TYPE, Type.DOUBLE_TYPE ); break;
            case D2I: bytecode.convert( Type.DOUBLE_TYPE, Type.INT_TYPE ); break;
            case D2L: bytecode.convert( Type.DOUBLE_TYPE, Type.LONG_TYPE ); break;
            case D2F: bytecode.convert( Type.DOUBLE_TYPE, Type.FLOAT_TYPE ); break;
            case I2B: bytecode.convert( Type.INT_TYPE, Type.BYTE_TYPE ); break;
            case I2C: bytecode.convert( Type.INT_TYPE, Type.CHAR_TYPE ); break;
            case I2S: bytecode.convert( Type.INT_TYPE, Type.SHORT_TYPE ); break;
            
            case LCMP:  bytecode.compare(); break;
            case FCMPL: bytecode.compareL( Type.FLOAT_TYPE ); break;
            case FCMPG: bytecode.compareG( Type.FLOAT_TYPE ); break;
            case DCMPL: bytecode.compareL( Type.DOUBLE_TYPE ); break;
            case DCMPG: bytecode.compareG( Type.DOUBLE_TYPE ); break;
            
            case IRETURN: bytecode.return_( Type.INT_TYPE ); break;
            case LRETURN: bytecode.return_( Type.LONG_TYPE ); break;
            case FRETURN: bytecode.return_( Type.FLOAT_TYPE ); break;
            case DRETURN: bytecode.return_( Type.DOUBLE_TYPE ); break;
            case ARETURN: bytecode.return_( OBJECT ); break;
            case RETURN:  bytecode.return_( Type.VOID_TYPE ); break;
            
            case ARRAYLENGTH:  bytecode.arrayLength(); break;
            case ATHROW:       bytecode.throw_(); break;
            case MONITORENTER: bytecode.monitorEnter(); break;
            case MONITOREXIT:  bytecode.monitorExit(); break;
            
            default: throw new RuntimeException();
        }                
    }

    /** @see org.objectweb.asm.MethodVisitor#visitIntInsn(int, int) */
    public void visitIntInsn( int opcode, int operand ) {
        switch( opcode ) {
            case BIPUSH: bytecode.constant( operand ); break;
            case SIPUSH: bytecode.constant( operand ); break;
            
            case NEWARRAY: {                
                Type type = null;
                
                switch( operand ) {
                    case T_BOOLEAN: type = Type.BOOLEAN_TYPE; break;
                    case T_CHAR:    type = Type.CHAR_TYPE; break;
                    case T_FLOAT:   type = Type.FLOAT_TYPE; break;
                    case T_DOUBLE:  type = Type.DOUBLE_TYPE; break;
                    case T_BYTE:    type = Type.BYTE_TYPE; break;
                    case T_SHORT:   type = Type.SHORT_TYPE; break;
                    case T_INT:     type = Type.INT_TYPE; break;
                    case T_LONG:    type = Type.LONG_TYPE; break;
                    default: throw new RuntimeException();
                }
                
                bytecode.newArray( type, 1 );
                break;
            }
            
            default: throw new RuntimeException();
        }
    }

    /** @see org.objectweb.asm.MethodVisitor#visitJumpInsn(int, org.objectweb.asm.Label) */
    public void visitJumpInsn( int opcode, Label label ) {
        switch( opcode ) {
            case IFEQ: bytecode.ifEq0( label ); break;
            case IFNE: bytecode.ifNE0( label ); break;
            case IFLT: bytecode.ifLT0( label ); break;
            case IFGE: bytecode.ifGE0( label ); break;
            case IFGT: bytecode.ifGT0( label ); break;
            case IFLE: bytecode.ifLE0( label ); break;
                
            case IF_ICMPEQ: bytecode.ifEq( label ); break;
            case IF_ICMPNE: bytecode.ifNE( label ); break;
            case IF_ICMPLT: bytecode.ifLT( label ); break;
            case IF_ICMPGE: bytecode.ifGE( label ); break;
            case IF_ICMPGT: bytecode.ifGT( label ); break;
            case IF_ICMPLE: bytecode.ifLE( label ); break;
                
            case IF_ACMPEQ: bytecode.ifSame( label ); break;
            case IF_ACMPNE: bytecode.ifNotSame( label ); break;
            case IFNULL:    bytecode.ifNull( label ); break;
            case IFNONNULL: bytecode.ifNonNull( label ); break;
                
            case GOTO: bytecode.goto_( label ); break;
                
            case JSR: throw new RuntimeException( "JSR instruction is not supported" );
            default: throw new RuntimeException();
        }          
    }

    /** @see org.objectweb.asm.MethodVisitor#visitLabel(org.objectweb.asm.Label) */
    public void visitLabel( Label label ) {
        bytecode.label( label );
    }

    /** @see org.objectweb.asm.MethodVisitor#visitLdcInsn(java.lang.Object) */
    public void visitLdcInsn( Object value ) {
        if     ( value instanceof Integer ) bytecode.constant( ((Integer) value).intValue() ); 
        else if( value instanceof Float   ) bytecode.constant( ((Float  ) value).floatValue() );
        else if( value instanceof Long    ) bytecode.constant( ((Long   ) value).longValue() );
        else if( value instanceof Double  ) bytecode.constant( ((Double ) value).doubleValue() );
        else if( value instanceof String  ) bytecode.constant( (String) value );
        else if( value instanceof Type    ) bytecode.constant( (Type) value );
        else throw new RuntimeException( "bad constant type" );
    }

    /** @see org.objectweb.asm.MethodVisitor#visitLineNumber(int, org.objectweb.asm.Label) */
    public void visitLineNumber( int line, Label label ) {
        bytecode.lineNumber( line );
    }

    /** @see org.objectweb.asm.MethodVisitor#visitLocalVariable(java.lang.String, java.lang.String, java.lang.String, org.objectweb.asm.Label, org.objectweb.asm.Label, int) */
    public void visitLocalVariable( String arg0, String arg1, String arg2, Label arg3, Label arg4, int arg5 ) {
        // nada        
    }

    /** @see org.objectweb.asm.MethodVisitor#visitLookupSwitchInsn(org.objectweb.asm.Label, int[], org.objectweb.asm.Label[]) */
    public void visitLookupSwitchInsn( Label defLabel, int[] cases, Label[] targets ) {
        bytecode.switch_( defLabel, cases, targets );
    }

    /** @see org.objectweb.asm.MethodVisitor#visitMaxs(int, int) */
    public void visitMaxs( int arg0, int arg1 ) {
        // nada        
    }

    /** @see org.objectweb.asm.MethodVisitor#visitMethodInsn(int, java.lang.String, java.lang.String, java.lang.String) */
    public void visitMethodInsn( int opcode, String owner, String name, String desc ) {
        
        Type   ownerType = Type.getObjectType( owner );
        Type   type      = Type.getReturnType( desc );
        Type[] params    = Type.getArgumentTypes( desc );
        
        switch( opcode ) {
            case INVOKEVIRTUAL:   bytecode.invokeVirtual  ( ownerType, name, type, params ); break;
            case INVOKESPECIAL:   bytecode.invokeSpecial  ( ownerType, name, type, params ); break;
            case INVOKESTATIC:    bytecode.invokeStatic   ( ownerType, name, type, params ); break;
            case INVOKEINTERFACE: bytecode.invokeInterface( ownerType, name, type, params ); break;
            default: throw new RuntimeException();
        }         
    }

    /** @see org.objectweb.asm.MethodVisitor#visitMultiANewArrayInsn(java.lang.String, int) */
    public void visitMultiANewArrayInsn( String desc, int dims ) {        
        bytecode.newArray( Type.getType( desc ), dims );
    }

    /** @see org.objectweb.asm.MethodVisitor#visitParameterAnnotation(int, java.lang.String, boolean) */
    public AnnotationVisitor visitParameterAnnotation( int arg0, String arg1, boolean arg2 ) {
        // nada
        return null;
    }

    /** @see org.objectweb.asm.MethodVisitor#visitTableSwitchInsn(int, int, org.objectweb.asm.Label, org.objectweb.asm.Label[]) */
    public void visitTableSwitchInsn( int min, int max, Label defLabel, Label[] targets ) {
        int[] cases = new int[ max - min + 1 ];
        int key = min;
        for( int i = 0; i < cases.length; i++, key++ ) {
            cases[i] = key;
        }
        
        bytecode.switch_( defLabel, cases, targets );
    }

    /** @see org.objectweb.asm.MethodVisitor#visitTryCatchBlock(org.objectweb.asm.Label, org.objectweb.asm.Label, org.objectweb.asm.Label, java.lang.String) */
    public void visitTryCatchBlock( Label arg0, Label arg1, Label arg2, String arg3 ) {
        // nada        
    }

    /** @see org.objectweb.asm.MethodVisitor#visitTypeInsn(int, java.lang.String) */
    public void visitTypeInsn( int opcode, String desc ) {
        Type type = Type.getObjectType( desc );
        
        switch( opcode ) {
            case NEW:        bytecode.newObject( type.getClassName() ); break;
            case ANEWARRAY:  bytecode.newArray( type, 1 ); break;
            case CHECKCAST:  bytecode.checkCast( type ); break;
            case INSTANCEOF: bytecode.instanceOf( type ); break;
            default: throw new RuntimeException();
        } 
    }

    /** @see org.objectweb.asm.MethodVisitor#visitVarInsn(int, int) */
    public void visitVarInsn( int opcode, int var ) {
        
        switch( opcode ) {
            case ILOAD:  bytecode.load( Type.INT_TYPE, var ); break;
            case LLOAD:  bytecode.load( Type.LONG_TYPE, var ); break;
            case FLOAD:  bytecode.load( Type.FLOAT_TYPE, var ); break;
            case DLOAD:  bytecode.load( Type.DOUBLE_TYPE, var ); break;
            case ALOAD:  bytecode.load( OBJECT, var ); break;
            case ISTORE: bytecode.store( Type.INT_TYPE, var ); break;
            case LSTORE: bytecode.store( Type.LONG_TYPE, var ); break;
            case FSTORE: bytecode.store( Type.FLOAT_TYPE, var ); break;
            case DSTORE: bytecode.store( Type.DOUBLE_TYPE, var ); break;
            case ASTORE: bytecode.store( OBJECT, var ); break;
                
            case RET: throw new RuntimeException( "RET instruction is not supported" );
            default: throw new RuntimeException();
        } 
    }    
}

