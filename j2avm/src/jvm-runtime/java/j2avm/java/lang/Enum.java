package j2avm.java.lang;

import org.epistem.j2avm.J2AVM;

import j2avm.java.io.Serializable;

/**
 * Support for Enums
 *
 * @author nickmain
 */
public abstract class Enum<E extends Enum<E>>
    implements Comparable<E>, Serializable {
    
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
    
//    public static <T extends Enum<T>> T valueOf(Class<T> enumType,
//                                                java.lang.String name) {
//        T result = enumType.enumConstantDirectory().get(name);
//        if (result != null)
//            return result;
//        if (name == null)
//            throw new NullPointerException("Name is null");
//        throw new IllegalArgumentException(
//            "No enum const " + enumType +"." + name);
//    }
}
