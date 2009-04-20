package j2avm.java.lang;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.asm.AVM2_ASM;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassTranslator;

import j2avm.java.io.Serializable;

/**
 * Support for Enums
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassTranslator.class )
public abstract class Enum<E extends Enum<E>>
    implements java.lang.Comparable<E>, java.io.Serializable {
    
    //keyed by class name
    public static Object enumClasses = new Object();
    
    private final int ordinal;
    private final java.lang.String name;

    public final java.lang.String name() {
        return name;
    }

    public final int ordinal() {
        return ordinal;
    }

    protected Enum( java.lang.String name, int ordinal ) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public java.lang.String toString() {
        return name;
    }

    public final int compareTo( E other ) {
        return ordinal - other.ordinal;
    }

    /* TODO
    public final Class<E> getDeclaringClass() {
        return null;
    }
    */
    
    public static <T extends Enum<T>> T valueOf( java.lang.Class<T> enumType, java.lang.String name ) {
        
        AVM2_ASM.callFunction( enumType, "values()" );
        T[] values = (T[]) AVM2_ASM.popObject();
        
        for( int i = 0; i < values.length; i++ ) {
            if( values[i].name.equals( name ) ) return values[i];
        }

        return null;
        //TODO
//        throw new IllegalArgumentException(
//            "No enum const " + enumType +"." + name);
    }
}
