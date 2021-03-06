package org.epistem.j2avm.asm;


import org.epistem.j2avm.translator.transformers.AVM2_ASM_Transformer;

/**
 * Static methods that generate AVM2 code. Calls to these methods can be
 * placed in runtime support classes and will be executed during code generation
 * for those methods. This simulates the ability to insert inline AVM2 assembly.
 * 
 *
 * @see AVM2_ASM_Transformer
 * @author nickmain
 */
public class AVM2_ASM {

    /** Append a value to a string */
    public static native String appendInt( String s, int value );
    
    /** Append a value to a string */
    public static native String appendDouble( String s, double value );

    /** Append a value to a string */
    public static native String appendString( String s, String value );

    /** Append a value to a string */
    public static native String appendObject( String s, Object value );
    
    /** Access stack top, assuming it is int */
    public static native int popInt();

    /** Access stack top, assuming it is boolean */
    public static native int popBoolean();

    /** Access stack top, assuming it is double */
    public static native double popDouble();

    /** Access stack top, assuming it is String */
    public static native String popString();

    /** Access stack top, assuming it is Object */
    public static native Object popObject();

    /** Push a value onto the stack */
    public static native void push( int value );

    /** Push a value onto the stack */
    public static native void push( boolean value );

    /** Push a value onto the stack */
    public static native void push( double value );

    /** Push a value onto the stack */
    public static native void push( String value );
    
    /** Push a value onto the stack */
    public static native void push( Object value );
    
    /** Get a public property from an object and leave it on the stack */
    public static native void getPublicProperty( Object target, Object name );

    /** Set a public property on an object */
    public static native void setPublicProperty( Object target, Object name, Object value );

    /** Set a public property on an object */
    public static native void setPublicProperty( Object target, Object name, int value );

    /** Set a public property on an object */
    public static native void setPublicProperty( Object target, Object name, double value );

    /** Set a public property on an object */
    public static native void setPublicProperty( Object target, Object name, boolean value );
    
    /** Call a void no-arg function */
    public static native void callVoidFunction( Object target, Object funcName );

    /** Call a no-arg function */
    public static native void callFunction( Object target, Object funcName );
    
    /** Test whether two objects are equal (non-strict) */
    public static native boolean equal( Object a, Object b );
    
    /** Do nothing - just bypass the Java type system */
    public static native <T> T retype( Object a );
    
    /** Duplicate and trace stack top */
    public static native void traceStackTop();
}
