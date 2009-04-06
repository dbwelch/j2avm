package j2avm.java.lang;

import org.epistem.j2avm.annotations.runtime.FlashTargetClass;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassAugmentingTranslator;

/**
 * Runtime support for Object
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassAugmentingTranslator.class )
@FlashTargetClass( "Object" )
public class Object {
    
    /** @see java.lang.Object#equals(java.lang.Object) */
    public boolean equals( j2avm.java.lang.Object obj ) {
        return this == obj;
    }

    /** @see java.lang.Object#hashCode() */
    public int hashCode() {
        // TODO implement hashcode
        return 45;
    }

    /** @see java.lang.Object#toString() */
    public String toString() {
        return "java.lang.Object@" + hashCode();
    }
}
