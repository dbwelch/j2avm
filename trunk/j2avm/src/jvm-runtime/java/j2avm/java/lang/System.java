package j2avm.java.lang;

import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.asm.AVM2_ASM;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassTranslator;

/**
 * Runtime support for System
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassTranslator.class )
public class System {

    /**
     * @see java.lang.System#arraycopy(Object, int, Object, int, int)
     */
    public static void arraycopy( java.lang.Object src, int srcPos, 
                                  java.lang.Object dest, int destPos, int length ) {
        
        Object[] srcA  = AVM2_ASM.retype( src );
        Object[] destA = AVM2_ASM.retype( dest );
        
        int srcEnd = srcPos + length;        
        for( int i = srcPos; i < srcEnd; i++ ) {
            destA[ destPos++ ] = srcA[ i ];
        }
    }
}
