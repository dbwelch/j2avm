package j2avm.java.lang;

import static org.epistem.j2avm.asm.AVM2_ASM.appendInt;

import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassTranslator;

/**
 * Runtime support for StringBuilder
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassTranslator.class )
public class StringBuilder extends j2avm.java.lang.Object {

    private String s;
    
    public StringBuilder( String s  ) {
        this.s = s;
    }
    
    public StringBuilder append( int value ) {
        s = appendInt( s, value );
        return this;
    }
    
    public String toString() {
        return s;
    }
}
