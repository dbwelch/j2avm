package j2avm.java.lang;

import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassTranslator;

import flash.FlashObject;
import static flash.Flash.*;

/**
 * Runtime support for Object
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassTranslator.class )
public class Object extends FlashObject {
    
    static {
        trace( "Object <clinit>" );
        
    }
    
    /** @see java.lang.Object#equals(java.lang.Object) */
    public boolean equals( j2avm.java.lang.Object obj ) {
        return this == obj;
    }

    /** @see java.lang.Object#hashCode() */
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    /** @see java.lang.Object#toString() */
    public String toString() {
        return "java.lang.Object@" + hashCode();
    }
}
