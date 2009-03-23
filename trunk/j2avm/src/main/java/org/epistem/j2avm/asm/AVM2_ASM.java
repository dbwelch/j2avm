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
}
