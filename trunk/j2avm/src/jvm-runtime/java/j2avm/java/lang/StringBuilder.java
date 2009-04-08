package j2avm.java.lang;

import static org.epistem.j2avm.asm.AVM2_ASM.appendDouble;
import static org.epistem.j2avm.asm.AVM2_ASM.appendInt;
import static org.epistem.j2avm.asm.AVM2_ASM.appendObject;
import static org.epistem.j2avm.asm.AVM2_ASM.appendString;

import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassTranslator;

/**
 * Runtime support for StringBuilder
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassTranslator.class )
public class StringBuilder {

    private java.lang.String s;
    
    public StringBuilder( java.lang.String s  ) {
        this.s = s;
    }
    
    public StringBuilder append( int value ) {
        s = appendInt( s, value );
        return this;
    }

    public StringBuilder append( double value ) {
        s = appendDouble( s, value );
        return this;
    }

    public StringBuilder append( java.lang.String value ) {
        s = appendString( s, value );
        return this;
    }

    public StringBuilder append( java.lang.Object object ) {
        s = appendObject( s, object );
        return this;
    }
    
    public java.lang.String toString() {
        return s;
    }
}
