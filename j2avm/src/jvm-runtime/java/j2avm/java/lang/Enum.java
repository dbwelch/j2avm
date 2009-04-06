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
    private final String name;

    public final String name() {
        return name;
    }

    public final int ordinal() {
        return ordinal;
    }

    protected Enum( String name, int ordinal ) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public String toString() {
        return name;
    }

    public final int compareTo( E o ) {
        return ordinal - other.ordinal;
    }

    /* TODO
    public final Class<E> getDeclaringClass() {
        return null;
    }
    */
    
    public static <T extends Enum<T>> T valueOf(Class<T> enumType,
                                                String name) {
        T result = enumType.enumConstantDirectory().get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException(
            "No enum const " + enumType +"." + name);
    }
}
