package org.epistem.jvm.io.parser;

import static org.epistem.jvm.io.Opcodes.*;
import static org.epistem.jvm.io.PrimitiveArrayType.*;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.epistem.io.CountingDataInput;
import org.epistem.jvm.attributes.LineNumberTableAttribute;
import org.epistem.jvm.code.*;
import org.epistem.jvm.code.instructions.*;
import org.epistem.jvm.code.instructions.ConditionalBranch.Condition;
import org.epistem.jvm.code.instructions.MethodCall.CallType;
import org.epistem.jvm.code.instructions.NumericOp.Operation;
import org.epistem.jvm.io.ConstantPool;
import org.epistem.jvm.io.ConstantPool.FieldRefEntry;
import org.epistem.jvm.io.ConstantPool.MethodRefEntry;
import org.epistem.jvm.io.ConstantPool.NameAndTypeEntry;
import org.epistem.jvm.type.*;

/**
 * Parser for the code attribute
 *
 * @author nickmain
 */
public class CodeParser {
    
    private final ConstantPool pool;
    private final DataInput    in;
    private final InstructionList instructions;
    
    private CountingDataInput instructionData;    
    private boolean isWide; //whether last parsed instruction was WIDE
    private int address; //of current instruction
    
    private final Map<String, Instruction> instructionsByLabel = new HashMap<String, Instruction>();
    private final Set<String> referencedLabels = new HashSet<String>();
    
    /**
     * Parse the bytecode
     * 
     * @param pool the class constant pool
     * @param in the bytecode data
     * @param instructions the instruction list to populate
     */
    public CodeParser( ConstantPool pool, DataInput in, InstructionList instructions ) 
        throws IOException {
        
        this.pool = pool;
        this.in   = in;
        this.instructions = instructions;
        
        parseInstructions();
        parseExceptionHandlers();
        insertLabels();
    }
    
    /**
     * Apply line numbers
     */
    public void applyLineNumbers( LineNumberTableAttribute lineNumAttr ) {
        int[] lineNumbers = lineNumAttr.lineNumbers;
        int[] offsets     = lineNumAttr.offsets;
        
        for( int i = 0; i < offsets.length; i++ ) {
            int offset = offsets[i];
            int number = lineNumbers[i];
            
            Instruction after = instructionsByLabel.get( "" + offset );
            if( after == null ) throw new RuntimeException( "missing target label" );
            
            LineNumber ln = new LineNumber( number );
            ln.offset = offset; 
            
            instructions.insertAfter( after.prev(), ln );
        }
    }
    
    /**
     * Parse the instructions 
     */
    private void parseInstructions( ) throws IOException {

        int codeSize = in.readInt();
        byte[] bytecode = new byte[ codeSize ];
        in.readFully( bytecode );
        
        instructionData = new CountingDataInput( 
                                 new DataInputStream( 
                                         new ByteArrayInputStream( bytecode )));

        while( instructionData.count < codeSize ) {
            parseInstruction();
        }
    }
    
    /**
     * Parse the exception handlers
     */
    private void parseExceptionHandlers() throws IOException {
    
        //exception handlers
        int handlerCount = in.readUnsignedShort();
        for (int i = 0; i < handlerCount; i++) {
            String start   = "" + in.readUnsignedShort();
            String end     = "" + in.readUnsignedShort();
            String handler = "" + in.readUnsignedShort();
            int typeIndex  = in.readUnsignedShort();
            
            ObjectType type = ObjectType.THROWABLE;
            if( typeIndex > 0 ) {
                type = ObjectType.fromName( pool.getClassName( typeIndex ));
            }
            
            instructions.exceptionHandlers.add( 
               new ExceptionHandler( start, end, handler, type ) );
            
            referencedLabels.add( start );
            referencedLabels.add( end );
            referencedLabels.add( handler );
        }
    }
    
    /**
     * Insert label intructions for all referenced labels
     */
    private void insertLabels() {        
        for( String target : referencedLabels ) {            
            Instruction after = instructionsByLabel.get( target );
            if( after == null ) throw new RuntimeException( "missing target label" );
            
            instructions.insertAfter( after.prev(), new Label( target ) );
        }        
    }
    
    /**
     * Parse a single instruction
     */
    private void parseInstruction() throws IOException {
        
        if( ! isWide ) {
            address = instructionData.count;
        }
        
        int bytecode = instructionData.readUnsignedByte();
        
        switch( bytecode ) {
            case NOP             : nop            (); break;             
            case ACONST_NULL     : aconst_null    (); break;     
            case ICONST_M1       : iconst_m1      (); break;       
            case ICONST_0        : iconst_0       (); break;        
            case ICONST_1        : iconst_1       (); break;        
            case ICONST_2        : iconst_2       (); break;        
            case ICONST_3        : iconst_3       (); break;        
            case ICONST_4        : iconst_4       (); break;        
            case ICONST_5        : iconst_5       (); break;        
            case LCONST_0        : lconst_0       (); break;        
            case LCONST_1        : lconst_1       (); break;        
            case FCONST_0        : fconst_0       (); break;        
            case FCONST_1        : fconst_1       (); break;        
            case FCONST_2        : fconst_2       (); break;        
            case DCONST_0        : dconst_0       (); break;        
            case DCONST_1        : dconst_1       (); break;        
            case BIPUSH          : bipush         ( i8() ); break;          
            case SIPUSH          : sipush         ( i16() ); break;          
            case LDC             : ldc            ( constant( u8() )); break;             
            case LDC_W           : ldc_w          ( constant( u16() )); break;           
            case LDC2_W          : ldc2_w         ( constant( u16() )); break;          
            case ILOAD           : iload          ( u8_w() ); break;           
            case LLOAD           : lload          ( u8_w() ); break;           
            case FLOAD           : fload          ( u8_w() ); break;           
            case DLOAD           : dload          ( u8_w() ); break;           
            case ALOAD           : aload          ( u8_w() ); break;           
            case ILOAD_0         : iload_0        (); break;         
            case ILOAD_1         : iload_1        (); break;         
            case ILOAD_2         : iload_2        (); break;         
            case ILOAD_3         : iload_3        (); break;         
            case LLOAD_0         : lload_0        (); break;         
            case LLOAD_1         : lload_1        (); break;         
            case LLOAD_2         : lload_2        (); break;         
            case LLOAD_3         : lload_3        (); break;         
            case FLOAD_0         : fload_0        (); break;         
            case FLOAD_1         : fload_1        (); break;         
            case FLOAD_2         : fload_2        (); break;         
            case FLOAD_3         : fload_3        (); break;         
            case DLOAD_0         : dload_0        (); break;         
            case DLOAD_1         : dload_1        (); break;         
            case DLOAD_2         : dload_2        (); break;         
            case DLOAD_3         : dload_3        (); break;         
            case ALOAD_0         : aload_0        (); break;         
            case ALOAD_1         : aload_1        (); break;         
            case ALOAD_2         : aload_2        (); break;         
            case ALOAD_3         : aload_3        (); break;         
            case IALOAD          : iaload         (); break;          
            case LALOAD          : laload         (); break;          
            case FALOAD          : faload         (); break;          
            case DALOAD          : daload         (); break;          
            case AALOAD          : aaload         (); break;          
            case BALOAD          : baload         (); break;          
            case CALOAD          : caload         (); break;          
            case SALOAD          : saload         (); break;  
            case ISTORE          : istore         ( u8_w() ); break;          
            case LSTORE          : lstore         ( u8_w() ); break;          
            case FSTORE          : fstore         ( u8_w() ); break;          
            case DSTORE          : dstore         ( u8_w() ); break;          
            case ASTORE          : astore         ( u8_w() ); break;          
            case ISTORE_0        : istore_0       (); break;        
            case ISTORE_1        : istore_1       (); break;        
            case ISTORE_2        : istore_2       (); break;        
            case ISTORE_3        : istore_3       (); break;        
            case LSTORE_0        : lstore_0       (); break;        
            case LSTORE_1        : lstore_1       (); break;        
            case LSTORE_2        : lstore_2       (); break;        
            case LSTORE_3        : lstore_3       (); break;        
            case FSTORE_0        : fstore_0       (); break;        
            case FSTORE_1        : fstore_1       (); break;        
            case FSTORE_2        : fstore_2       (); break;        
            case FSTORE_3        : fstore_3       (); break;        
            case DSTORE_0        : dstore_0       (); break;        
            case DSTORE_1        : dstore_1       (); break;        
            case DSTORE_2        : dstore_2       (); break;        
            case DSTORE_3        : dstore_3       (); break;        
            case ASTORE_0        : astore_0       (); break;        
            case ASTORE_1        : astore_1       (); break;        
            case ASTORE_2        : astore_2       (); break;        
            case ASTORE_3        : astore_3       (); break;        
            case IASTORE         : iastore        (); break;         
            case LASTORE         : lastore        (); break;         
            case FASTORE         : fastore        (); break;         
            case DASTORE         : dastore        (); break;         
            case AASTORE         : aastore        (); break;         
            case BASTORE         : bastore        (); break;         
            case CASTORE         : castore        (); break;         
            case SASTORE         : sastore        (); break;         
            case POP             : pop            (); break;             
            case POP2            : pop2           (); break;            
            case DUP             : dup            (); break;             
            case DUP_X1          : dup_x1         (); break;          
            case DUP_X2          : dup_x2         (); break;          
            case DUP2            : dup2           (); break;            
            case DUP2_X1         : dup2_x1        (); break;         
            case DUP2_X2         : dup2_x2        (); break;         
            case SWAP            : swap           (); break;            
            case IADD            : iadd           (); break;            
            case LADD            : ladd           (); break;            
            case FADD            : fadd           (); break;            
            case DADD            : dadd           (); break;            
            case ISUB            : isub           (); break;            
            case LSUB            : lsub           (); break;            
            case FSUB            : fsub           (); break;            
            case DSUB            : dsub           (); break;            
            case IMUL            : imul           (); break;            
            case LMUL            : lmul           (); break;            
            case FMUL            : fmul           (); break;            
            case DMUL            : dmul           (); break;            
            case IDIV            : idiv           (); break;            
            case LDIV            : ldiv           (); break;            
            case FDIV            : fdiv           (); break;            
            case DDIV            : ddiv           (); break;            
            case IREM            : irem           (); break;            
            case LREM            : lrem           (); break;            
            case FREM            : frem           (); break;            
            case DREM            : drem           (); break;            
            case INEG            : ineg           (); break;            
            case LNEG            : lneg           (); break;            
            case FNEG            : fneg           (); break;            
            case DNEG            : dneg           (); break;            
            case ISHL            : ishl           (); break;            
            case LSHL            : lshl           (); break;            
            case ISHR            : ishr           (); break;            
            case LSHR            : lshr           (); break;            
            case IUSHR           : iushr          (); break;           
            case LUSHR           : lushr          (); break;           
            case IAND            : iand           (); break;            
            case LAND            : land           (); break;            
            case IOR             : ior            (); break;             
            case LOR             : lor            (); break;             
            case IXOR            : ixor           (); break;            
            case LXOR            : lxor           (); break;            
            case IINC            : iinc           ( u8_w(), i8_w() ); break;            
            case I2L             : i2l            (); break;             
            case I2F             : i2f            (); break;             
            case I2D             : i2d            (); break;             
            case L2I             : l2i            (); break;             
            case L2F             : l2f            (); break;             
            case L2D             : l2d            (); break;             
            case F2I             : f2i            (); break;             
            case F2L             : f2l            (); break;             
            case F2D             : f2d            (); break;             
            case D2I             : d2i            (); break;             
            case D2L             : d2l            (); break;             
            case D2F             : d2f            (); break;             
            case I2B             : i2b            (); break;             
            case I2C             : i2c            (); break;             
            case I2S             : i2s            (); break;             
            case LCMP            : lcmp           (); break;            
            case FCMPL           : fcmpl          (); break;           
            case FCMPG           : fcmpg          (); break;           
            case DCMPL           : dcmpl          (); break;           
            case DCMPG           : dcmpg          (); break;           
            case IFEQ            : ifeq           ( address + i16() ); break;            
            case IFNE            : ifne           ( address + i16() ); break;            
            case IFLT            : iflt           ( address + i16() ); break;            
            case IFGE            : ifge           ( address + i16() ); break;            
            case IFGT            : ifgt           ( address + i16() ); break;            
            case IFLE            : ifle           ( address + i16() ); break;            
            case IF_ICMPEQ       : if_icmpeq      ( address + i16() ); break;       
            case IF_ICMPNE       : if_icmpne      ( address + i16() ); break;       
            case IF_ICMPLT       : if_icmplt      ( address + i16() ); break;       
            case IF_ICMPGE       : if_icmpge      ( address + i16() ); break;       
            case IF_ICMPGT       : if_icmpgt      ( address + i16() ); break;       
            case IF_ICMPLE       : if_icmple      ( address + i16() ); break;       
            case IF_ACMPEQ       : if_acmpeq      ( address + i16() ); break;       
            case IF_ACMPNE       : if_acmpne      ( address + i16() ); break;       
            case GOTO            : goto_          ( address + i16() ); break;            
            case JSR             : jsr            ( address + i16() ); break;
            case RET             : ret            ( u8_w() ); break;
            case IRETURN         : ireturn        (); break;         
            case LRETURN         : lreturn        (); break;         
            case FRETURN         : freturn        (); break;         
            case DRETURN         : dreturn        (); break;         
            case ARETURN         : areturn        (); break;         
            case RETURN          : return_        (); break;          
            case NEW             : new_           ( (ObjectType) class_() ); break;             
            case NEWARRAY        : newarray       ( primArrayType( u8() ) ); break;        
            case ANEWARRAY       : anewarray      ( (ObjectType) class_() ); break;       
            case ARRAYLENGTH     : arraylength    (); break;     
            case ATHROW          : athrow         (); break;          
            case CHECKCAST       : checkcast      ( (ObjectOrArrayType) class_() ); break;       
            case INSTANCEOF      : instanceof_    ( (ObjectOrArrayType) class_() ); break;      
            case MONITORENTER    : monitorenter   (); break;    
            case MONITOREXIT     : monitorexit    (); break;
            case MULTIANEWARRAY  : multianewarray ( (ArrayType) class_(), u8() ); break;  
            case IFNULL          : ifnull         ( address + i16() ); break;          
            case IFNONNULL       : ifnonnull      ( address + i16() ); break;       
            case GOTO_W          : goto_w         ( address + i32() ); break;          
            case JSR_W           : jsr_w          ( address + i32() ); break;

            case WIDE            : {
                isWide = true; 
                parseInstruction(); 
                isWide = false; 
                break;            
            }

            case GETSTATIC       : //fall through     
            case PUTSTATIC       : //fall through        
            case GETFIELD        : //fall through   
            case PUTFIELD        : {
                FieldRefEntry fref = (FieldRefEntry) pool.getEntry( u16() );
                ObjectType       class_   = ObjectType.fromName( pool.getClassName( fref.classIndex ));
                NameAndTypeEntry nt       = fref.getNameAndTypeEntry();
                String           name     = nt.getNameEntry().value;
                String           typeName = nt.getTypeEntry().value;
                ValueType        type     = ValueType.fromName( ConstantPool.decodeTypeName( typeName ));
                
                switch( bytecode ) {
                    case GETSTATIC: getstatic ( class_, name, type ); break;       
                    case PUTSTATIC: putstatic ( class_, name, type ); break;       
                    case GETFIELD : getfield  ( class_, name, type ); break;        
                    case PUTFIELD : putfield  ( class_, name, type ); break;                
                }
                
                break;
            }            
            
            case INVOKESPECIAL   : //fall through   
            case INVOKESTATIC    : //fall through    
            case INVOKEINTERFACE : //fall through                          
            case INVOKEVIRTUAL   : {
                int index = u16();
                MethodRefEntry   mref   = (MethodRefEntry) pool.getEntry( index );
                ObjectType       class_ = ObjectType.fromName( pool.getClassName( mref.classIndex ));
                NameAndTypeEntry nt     = mref.getNameAndTypeEntry();
                String           name   = nt.getNameEntry().value;
                String           sig    = nt.getTypeEntry().value;
                
                String[] ss = ConstantPool.readSignature( sig );
                
                JVMType returnType = JVMType.fromName( ss[0] );
                ValueType[] paramTypes = new ValueType[ ss.length - 1 ];
                for( int i = 0; i < paramTypes.length; i++ ) {
                    paramTypes[i] = ValueType.fromName( ss[i+1] );
                }
                
                Signature signature = new Signature( name, paramTypes );
                
                switch( bytecode ) {
                    case INVOKEVIRTUAL:   invokevirtual  ( class_, signature, returnType ); break;
                    case INVOKEINTERFACE: invokeinterface( class_, signature, returnType ); u16(); break;
                    case INVOKESPECIAL:   invokespecial  ( class_, signature, returnType ); break;
                    case INVOKESTATIC:    invokestatic   ( class_, signature, returnType ); break;
                }
                
                break;   
            }
            
            case TABLESWITCH     : {
                align32();
                int defAddr = address + i32();
                int low     = i32();
                int high    = i32();
                int[] addrs = new int[ high - low + 1 ];
                
                for( int i = 0; i < addrs.length; i++ ) {
                    addrs[i] = address + i32();
                }
                
                tableswitch( defAddr, low, high, addrs ); 
                break;     
            }
            
            case LOOKUPSWITCH    : {
                align32();
                int defAddr   = address + i32();
                int count     = u32();                
                int[][] cases = new int[count][];
                
                for( int i = 0; i < cases.length; i++ ) {
                    cases[i] = new int[]{ i32(), address + i32() };
                }
                
                lookupswitch( defAddr, cases ); 
                break;    
            }

            default: throw new IOException( "Unknown bytecode: " + bytecode );
        }
    }

    private PrimitiveType primArrayType( int type ) throws IOException {
        switch( type ) {
            case T_BOOLEAN: return PrimitiveType.BOOLEAN; 
            case T_CHAR   : return PrimitiveType.CHAR;
            case T_FLOAT  : return PrimitiveType.FLOAT;
            case T_DOUBLE : return PrimitiveType.DOUBLE;
            case T_BYTE   : return PrimitiveType.BYTE;
            case T_SHORT  : return PrimitiveType.SHORT;
            case T_INT    : return PrimitiveType.INT;
            case T_LONG   : return PrimitiveType.LONG;
                
            default: throw new IOException( "Unknown primitive array type " + type );
        }
    }
    
    // read a class from the cpool
    private JVMType class_() throws IOException {
        return JVMType.fromName( pool.getClassName( u16() ) );
    }
    
    private int i16() throws IOException {
        return instructionData.readShort();
    }
    
    private int u16() throws IOException {
        return instructionData.readUnsignedShort();
    }
    
    private int i8() throws IOException {
        return instructionData.readByte();
    }
    
    private int u8() throws IOException {
        return instructionData.readUnsignedByte();
    }
    
    private int u32() throws IOException {
        return instructionData.readInt();  //full 32-bit unsigned range is never needed in bytecode
    }
    
    private int i32() throws IOException {
        return instructionData.readInt();        
    }
    
    //read value that may be wide
    private int u8_w() throws IOException {
        return isWide ? instructionData.readUnsignedShort() : instructionData.readUnsignedByte();
    }

    //read value that may be wide
    private int i8_w() throws IOException {
        return isWide ? instructionData.readShort() : instructionData.readByte();
    }
    
    //align to 32 boundary
    private void align32() throws IOException {
        while( instructionData.count % 4 != 0 ) {
            instructionData.readByte();  
        }
    }
    
    //get a pool constant
    private Object constant( int index ) {
        return pool.getConstant( index );
    }
    
    private void nop(){ append( new Nop() ); }
    
    private void aconst_null(){ append( new ConstantNull() ); }
    
    private void iconst_m1(){ append( new ConstantInt( -1 ) ); }       
    private void iconst_0(){ append( new ConstantInt( 0 ) ); }        
    private void iconst_1(){ append( new ConstantInt( 1 ) ); }        
    private void iconst_2(){ append( new ConstantInt( 2 ) ); }        
    private void iconst_3(){ append( new ConstantInt( 3 ) ); }        
    private void iconst_4(){ append( new ConstantInt( 4 ) ); }        
    private void iconst_5(){ append( new ConstantInt( 5 ) ); }        
    private void lconst_0(){ append( new ConstantLong( 0L ) ); }        
    private void lconst_1(){ append( new ConstantLong( 1L ) ); }        
    private void fconst_0(){ append( new ConstantFloat( 0f ) ); }        
    private void fconst_1(){ append( new ConstantFloat( 1f ) ); }        
    private void fconst_2(){ append( new ConstantFloat( 2f ) ); }        
    private void dconst_0(){ append( new ConstantDouble( 0.0 ) ); }        
    private void dconst_1(){ append( new ConstantDouble( 1.0 ) ); }        
    private void bipush( int value ){ append( new ConstantInt( value ) ); }          
    private void sipush( int value ){ append( new ConstantInt( value ) ); }          
    
    private void ldc( Object constant ){                 
        if( constant == null ) {
            append( new ConstantNull() );
        }
        else if( constant instanceof String ) {
            append( new ConstantString( (String) constant ) );
        }
        else if( constant instanceof Integer ) {
            append( new ConstantInt( (Integer) constant ) );
        }
        else if( constant instanceof Long ) {
            append( new ConstantLong( (Long) constant ) );
        }
        else if( constant instanceof Float ) {
            append( new ConstantFloat( (Float) constant ) );
        }
        else if( constant instanceof Double ) {
            append( new ConstantDouble( (Double) constant ) );
        }
        else if( constant instanceof JVMType ) {
            append( new ConstantType( (JVMType) constant ) );
        }
    }        
    
    private void ldc_w( Object constant ){ ldc( constant ); }           
    private void ldc2_w( Object constant ){ ldc( constant ); }
    
    private void iload( int index ){ load( index, PrimitiveType.INT ); }           
    private void lload( int index ){ load( index, PrimitiveType.LONG ); }                      
    private void fload( int index ){ load( index, PrimitiveType.FLOAT ); }                      
    private void dload( int index ){ load( index, PrimitiveType.DOUBLE ); }               
    private void aload( int index ){ load( index, ObjectType.OBJECT ); }
    
    private void load( int index, ValueType type ) {
        append( new VarAccess( index, type, false ) );
    }
    
    private void iload_0(){ iload( 0 ); }         
    private void iload_1(){ iload( 1 ); }         
    private void iload_2(){ iload( 2 ); }         
    private void iload_3(){ iload( 3 ); }         
    private void lload_0(){ lload( 0 ); }         
    private void lload_1(){ lload( 1 ); }         
    private void lload_2(){ lload( 2 ); }         
    private void lload_3(){ lload( 3 ); }         
    private void fload_0(){ fload( 0 ); }         
    private void fload_1(){ fload( 1 ); }         
    private void fload_2(){ fload( 2 ); }         
    private void fload_3(){ fload( 3 ); }         
    private void dload_0(){ dload( 0 ); }         
    private void dload_1(){ dload( 1 ); }         
    private void dload_2(){ dload( 2 ); }         
    private void dload_3(){ dload( 3 ); }         
    private void aload_0(){ aload( 0 ); }         
    private void aload_1(){ aload( 1 ); }         
    private void aload_2(){ aload( 2 ); }         
    private void aload_3(){ aload( 3 ); }        
    
    private void iaload(){ arrayLoad( PrimitiveType.INT ); }          
    private void laload(){ arrayLoad( PrimitiveType.LONG ); }          
    private void faload(){ arrayLoad( PrimitiveType.FLOAT ); }          
    private void daload(){ arrayLoad( PrimitiveType.DOUBLE ); }          
    private void aaload(){ arrayLoad( ObjectType.OBJECT ); }          
    private void baload(){ arrayLoad( PrimitiveType.BYTE ); }          
    private void caload(){ arrayLoad( PrimitiveType.CHAR ); }          
    private void saload(){ arrayLoad( PrimitiveType.SHORT ); } 
    
    private void arrayLoad( ValueType type ) {
        append( new ArrayAccess( type, false ) );
    }
    
    private void istore( int index ){ store( index, PrimitiveType.INT ); }           
    private void lstore( int index ){ store( index, PrimitiveType.LONG ); }                      
    private void fstore( int index ){ store( index, PrimitiveType.FLOAT ); }                      
    private void dstore( int index ){ store( index, PrimitiveType.DOUBLE ); }               
    private void astore( int index ){ store( index, ObjectType.OBJECT ); }
    
    private void store( int index, ValueType type ) {
        append( new VarAccess( index, type, true ) );                
    }
    
    private void istore_0(){ istore( 0 ); }        
    private void istore_1(){ istore( 1 ); }        
    private void istore_2(){ istore( 2 ); }        
    private void istore_3(){ istore( 3 ); }        
    private void lstore_0(){ lstore( 0 ); }        
    private void lstore_1(){ lstore( 1 ); }        
    private void lstore_2(){ lstore( 2 ); }        
    private void lstore_3(){ lstore( 3 ); }        
    private void fstore_0(){ fstore( 0 ); }        
    private void fstore_1(){ fstore( 1 ); }        
    private void fstore_2(){ fstore( 2 ); }        
    private void fstore_3(){ fstore( 3 ); }        
    private void dstore_0(){ dstore( 0 ); }        
    private void dstore_1(){ dstore( 1 ); }        
    private void dstore_2(){ dstore( 2 ); }        
    private void dstore_3(){ dstore( 3 ); }        
    private void astore_0(){ astore( 0 ); }        
    private void astore_1(){ astore( 1 ); }        
    private void astore_2(){ astore( 2 ); }        
    private void astore_3(){ astore( 3 ); }   
    
    private void iastore(){ arrayStore( PrimitiveType.INT ); }         
    private void lastore(){ arrayStore( PrimitiveType.LONG ); }         
    private void fastore(){ arrayStore( PrimitiveType.FLOAT ); }         
    private void dastore(){ arrayStore( PrimitiveType.DOUBLE ); }         
    private void aastore(){ arrayStore( ObjectType.OBJECT ); }         
    private void bastore(){ arrayStore( PrimitiveType.BYTE ); }         
    private void castore(){ arrayStore( PrimitiveType.CHAR ); }         
    private void sastore(){ arrayStore( PrimitiveType.SHORT ); }      
    
    private void arrayStore( ValueType type ) {
        append( new ArrayAccess( type, true ) );
    }
    
    private void pop(){ append( new Pop( 1 ) ); }             
    private void pop2(){ append( new Pop( 2 ) ); }            
    private void swap(){ append( new Swap() ); }            
    
    private void dup()    { dupx( 1, 0 ); }                   
    private void dup_x1() { dupx( 1, 1 ); }          
    private void dup_x2() { dupx( 1, 2 ); }          
    private void dup2()   { dupx( 2, 0 ); }            
    private void dup2_x1(){ dupx( 2, 1 ); }         
    private void dup2_x2(){ dupx( 2, 2 ); }         

    private void dupx( int count, int skip ) {
        append( new Dup( count, skip ) );
    }
    
    private void numop( NumericOp.Operation op, PrimitiveType type ) {
        append( new NumericOp( op, type ));
    }
    
    private void iadd() { numop( Operation.Add, PrimitiveType.INT ); }            
    private void ladd() { numop( Operation.Add, PrimitiveType.LONG ); }            
    private void fadd() { numop( Operation.Add, PrimitiveType.FLOAT ); }            
    private void dadd() { numop( Operation.Add, PrimitiveType.DOUBLE ); }            
    private void isub() { numop( Operation.Subtract, PrimitiveType.INT ); }                 
    private void lsub() { numop( Operation.Subtract, PrimitiveType.LONG ); }               
    private void fsub() { numop( Operation.Subtract, PrimitiveType.FLOAT ); }             
    private void dsub() { numop( Operation.Subtract, PrimitiveType.DOUBLE ); }           
    private void imul() { numop( Operation.Multiply, PrimitiveType.INT ); }                 
    private void lmul() { numop( Operation.Multiply, PrimitiveType.LONG ); }               
    private void fmul() { numop( Operation.Multiply, PrimitiveType.FLOAT ); }             
    private void dmul() { numop( Operation.Multiply, PrimitiveType.DOUBLE ); }           
    private void idiv() { numop( Operation.Divide, PrimitiveType.INT ); }                 
    private void ldiv() { numop( Operation.Divide, PrimitiveType.LONG ); }               
    private void fdiv() { numop( Operation.Divide, PrimitiveType.FLOAT ); }             
    private void ddiv() { numop( Operation.Divide, PrimitiveType.DOUBLE ); }           
    private void irem() { numop( Operation.Remainder, PrimitiveType.INT ); }                 
    private void lrem() { numop( Operation.Remainder, PrimitiveType.LONG ); }               
    private void frem() { numop( Operation.Remainder, PrimitiveType.FLOAT ); }             
    private void drem() { numop( Operation.Remainder, PrimitiveType.DOUBLE ); }           
    private void ishl() { numop( Operation.ShiftLeft, PrimitiveType.INT ); }                 
    private void lshl() { numop( Operation.ShiftLeft, PrimitiveType.LONG ); }               
    private void ishr() { numop( Operation.ShiftRight, PrimitiveType.INT ); }             
    private void lshr() { numop( Operation.ShiftRight, PrimitiveType.LONG ); }           
    private void iushr(){ numop( Operation.ShiftRightUnsigned, PrimitiveType.INT ); }                
    private void lushr(){ numop( Operation.ShiftRightUnsigned, PrimitiveType.LONG ); }              
    private void iand() { numop( Operation.And, PrimitiveType.INT ); }             
    private void land() { numop( Operation.And, PrimitiveType.LONG ); }           
    private void ior()  { numop( Operation.Or, PrimitiveType.INT ); }                  
    private void lor()  { numop( Operation.Or, PrimitiveType.LONG ); }                
    private void ixor() { numop( Operation.Xor, PrimitiveType.INT ); }             
    private void lxor() { numop( Operation.Xor, PrimitiveType.LONG ); }         
    private void ineg() { numop( Operation.Negate, PrimitiveType.INT ); }            
    private void lneg() { numop( Operation.Negate, PrimitiveType.LONG ); }            
    private void fneg() { numop( Operation.Negate, PrimitiveType.FLOAT ); }            
    private void dneg() { numop( Operation.Negate, PrimitiveType.DOUBLE ); }            
    private void lcmp (){ numop( Operation.Compare, PrimitiveType.LONG ); }            
    private void fcmpl(){ numop( Operation.CompareL, PrimitiveType.FLOAT ); }           
    private void fcmpg(){ numop( Operation.CompareG, PrimitiveType.FLOAT ); }           
    private void dcmpl(){ numop( Operation.CompareL, PrimitiveType.DOUBLE ); }           
    private void dcmpg(){ numop( Operation.CompareG, PrimitiveType.DOUBLE ); }

    private void iinc( int index, int value ){
        append( new Increment( index, value ) );
    }    
    
    private void i2l(){ convert( Convert.Types.I2L ); }             
    private void i2f(){ convert( Convert.Types.I2F ); }             
    private void i2d(){ convert( Convert.Types.I2D ); }             
    private void l2i(){ convert( Convert.Types.L2I ); }             
    private void l2f(){ convert( Convert.Types.L2F ); }             
    private void l2d(){ convert( Convert.Types.L2D ); }             
    private void f2i(){ convert( Convert.Types.F2I ); }             
    private void f2l(){ convert( Convert.Types.F2L ); }             
    private void f2d(){ convert( Convert.Types.F2D ); }             
    private void d2i(){ convert( Convert.Types.D2I ); }             
    private void d2l(){ convert( Convert.Types.D2L ); }             
    private void d2f(){ convert( Convert.Types.D2F ); }             
    private void i2b(){ convert( Convert.Types.I2B ); }             
    private void i2c(){ convert( Convert.Types.I2C ); }             
    private void i2s(){ convert( Convert.Types.I2S ); }     
    
    private void convert( Convert.Types types ) {
        append( new Convert( types ) );
    }
        
    private void ifeq     ( int address ){ if_( address, Condition.IfEq0 ); }
    private void ifne     ( int address ){ if_( address, Condition.IfNE0 ); }
    private void iflt     ( int address ){ if_( address, Condition.IfLT0 ); }   
    private void ifge     ( int address ){ if_( address, Condition.IfGE0 ); }   
    private void ifgt     ( int address ){ if_( address, Condition.IfGT0 ); }   
    private void ifle     ( int address ){ if_( address, Condition.IfLE0 ); }  
    private void if_icmpeq( int address ){ if_( address, Condition.IfEq ); }       
    private void if_icmpne( int address ){ if_( address, Condition.IfNE ); }       
    private void if_icmplt( int address ){ if_( address, Condition.IfLT ); }       
    private void if_icmpge( int address ){ if_( address, Condition.IfGE ); }       
    private void if_icmpgt( int address ){ if_( address, Condition.IfGT ); }       
    private void if_icmple( int address ){ if_( address, Condition.IfLE ); }       
    private void if_acmpeq( int address ){ if_( address, Condition.IfSame ); }
    private void if_acmpne( int address ){ if_( address, Condition.IfNotSame ); }       
    private void ifnull   ( int address ){ if_( address, Condition.IfNull ); }          
    private void ifnonnull( int address ){ if_( address, Condition.IfNonNull ); }

    private void if_( int address, ConditionalBranch.Condition cond ){
        append( new ConditionalBranch( "" + address, cond ) );
    }  
            
    private void goto_w( int address ){ goto_( address ); }
    private void goto_( int address ){ append( new UnconditionalBranch( "" + address ) ); }          
    
    private void jsr( int address ){ throw new RuntimeException( "JSR instruction is not supported." ); }
    private void ret( int index ){ throw new RuntimeException( "RET instruction is not supported." ); }
    private void jsr_w( int address ){ throw new RuntimeException( "JSR_W instruction is not supported." ); }
    
    private void tableswitch( int defAddress, int low, int high, int[] addresses ){ 
        Switch sw = new Switch( "" + defAddress );
        for( int i = low; i <= high; i++ ) {
            sw.addCase( i, "" + addresses[ i - low ] );
        }
        
        append( sw );
    }     
    
    private void lookupswitch( int defAddress, int[][] cases ){ 
        Switch sw = new Switch( "" + defAddress );
        for( int i = 0; i <= cases.length; i++ ) {
            sw.addCase( cases[i][0], "" + cases[i][1] );
        }
        
        append( sw );        
    }   
    
    private void ireturn(){ append( new Return( PrimitiveType.INT )); }         
    private void lreturn(){ append( new Return( PrimitiveType.LONG )); }         
    private void freturn(){ append( new Return( PrimitiveType.FLOAT )); }         
    private void dreturn(){ append( new Return( PrimitiveType.DOUBLE )); }         
    private void areturn(){ append( new Return( ObjectType.OBJECT ) ); }         
    private void return_(){ append( new Return( VoidType.VOID ) ); }
    
    private void getstatic( ObjectType class_, String name, ValueType type ){ 
        field( class_, name, type, false, true );        
    }       
    
    private void putstatic( ObjectType class_, String name, ValueType type ){
        field( class_, name, type, true, true );        
    }       

    private void getfield( ObjectType class_, String name, ValueType type ){
        field( class_, name, type, false, false );        
    }        

    private void putfield( ObjectType class_, String name, ValueType type ){
        field( class_, name, type, true, false );        
    }
    
    private void field( ObjectType owner, String name, ValueType type,
                        boolean isWrite, boolean isStatic ) {
        append( new FieldAccess( owner, name, type, isWrite, isStatic ) );
    }
    
    private void invokevirtual( ObjectType class_, Signature signature, JVMType returnType ){ 
        invoke( class_, signature, returnType, CallType.Virtual );
    }   
    private void invokespecial( ObjectType class_, Signature signature, JVMType returnType ){ 
        invoke( class_, signature, returnType, CallType.Special );
    }   
    private void invokeinterface( ObjectType class_, Signature signature, JVMType returnType ){
        invoke( class_, signature, returnType, CallType.Interface );
    }   
    private void invokestatic( ObjectType class_, Signature signature, JVMType returnType ){
        invoke( class_, signature, returnType, CallType.Static );
    }    
    
    private void invoke( ObjectType owner, Signature signature, JVMType returnType, CallType callType ) {
        append( new MethodCall( owner, signature, returnType, callType ) );
    }
    
    private void new_( ObjectType type ){ 
        append( new New( type ) );        
    }             
    
    private void newarray( PrimitiveType type ){
        multianewarray( new ArrayType( type, 1 ), 1 );
    }
    
    private void anewarray( ObjectType type ){ 
        multianewarray( new ArrayType( type, 1 ), 1 );
    }
    
    private void multianewarray( ArrayType type, int dimCount ){
        append( new NewArray( type, dimCount ) );
    }  
    
    private void arraylength(){ append( new ArrayLength() ); }     
    private void athrow(){ append( new Throw() ); }          
    private void checkcast( ObjectOrArrayType type ){ append( new CheckCast( type ) ); }       
    private void instanceof_( ObjectOrArrayType type ){ append( new InstanceOf( type ) ); }      

    private void monitorenter(){ append( new Monitor( false ) ); }    
    private void monitorexit (){ append( new Monitor( true ) ); }
        
    //all instructions added to the list must go through this method
    private void append( Instruction instruction ) {
        instructionsByLabel.put( "" + address, instruction );
        
        if( instruction instanceof LabelTargetter ) {
            ((LabelTargetter) instruction).gatherLabels( referencedLabels );
        }
        
        instruction.offset = address;
        instructions.append( instruction );
    }
}
