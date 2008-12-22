package org.epistem.j2avm.bytecode;

import org.epistem.jvm.type.*;
import org.objectweb.asm.Label;

/**
 * Visitor interface for abstracted JVM bytecode
 *
 * @author nickmain
 */
public interface BytecodeVisitor {

    /**
     * Called when done passing instructions
     */
    public void end();
    
    /**
     * Define a label that can be used as the target of branches.
     * The label applies to the immediately following instruction.
     * A label may also be applied after the last instruction and before end()
     * for use with exception handlers.
     */
    public void label( Label label );

    public void nop();
    
    public void constantNull();
    
    public void constant( int value );
    public void constant( long value );
    public void constant( float value );
    public void constant( double value );
    public void constant( String value );
    public void constant( JavaType type );
    
    public void load ( ValueType type, int localVar );
    public void store( ValueType type, int localVar );

    public void arrayLength();
    public void loadElement ( ValueType type );
    public void storeElement( ValueType type );

    public void pop( int count );
    public void dup( int count, int skip );
    public void swap();
    
    public void add      ( PrimitiveType type );
    public void subtract ( PrimitiveType type );
    public void multiply ( PrimitiveType type );
    public void divide   ( PrimitiveType type );
    public void remainder( PrimitiveType type );
    public void negate   ( PrimitiveType type );
    
    public void shiftLeft( PrimitiveType type );
    public void shiftRight( PrimitiveType type );
    public void shiftRightU( PrimitiveType type );
    
    public void and( PrimitiveType type );
    public void or ( PrimitiveType type );
    public void xor( PrimitiveType type );

    public void increment( int localVar, int increment );
    
    public void convert( PrimitiveType fromType, PrimitiveType toType );
    
    public void compare();
    
    public void compareL( PrimitiveType type );
    public void compareG( PrimitiveType type );
    
    public void ifEq0( Label label );
    public void ifNE0( Label label );
    public void ifLT0( Label label );
    public void ifGE0( Label label );
    public void ifGT0( Label label );
    public void ifLE0( Label label );
    public void ifEq ( Label label );
    public void ifNE ( Label label );
    public void ifLT ( Label label );
    public void ifGE ( Label label );
    public void ifGT ( Label label );
    public void ifLE ( Label label );
    
    public void ifSame   ( Label label );
    public void ifNotSame( Label label );
    public void ifNull   ( Label label );
    public void ifNonNull( Label label );
    
    public void goto_( Label label );
    
    public void switch_( Label defaultLabel, int[] cases, Label[] targets );

    public void return_( JavaType type );

    public void getStatic( ObjectType owner, String name, ValueType type );
    public void putStatic( ObjectType owner, String name, ValueType type );
    public void getField ( ObjectType owner, String name, ValueType type );
    public void putField ( ObjectType owner, String name, ValueType type );

    public void invokeVirtual  ( ObjectType owner, Signature signature, JavaType returnType );
    public void invokeSpecial  ( ObjectType owner, Signature signature, JavaType returnType );
    public void invokeStatic   ( ObjectType owner, Signature signature, JavaType returnType );
    public void invokeInterface( ObjectType owner, Signature signature, JavaType returnType );    
    
    public void newObject( String className );
    public void newArray( ArrayType type, int dimensionCount );
    
    public void throw_();
    
    public void checkCast( ObjectOrArrayType type );
    public void instanceOf( ObjectOrArrayType type );
    
    public void monitorEnter();
    public void monitorExit();
    
    public void lineNumber( int line );
}
