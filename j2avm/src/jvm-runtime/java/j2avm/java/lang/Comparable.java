package j2avm.java.lang;

import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkInterfaceTranslator;

/**
 * Runtime support for Comparable
 * 
 * @author nickmain
 */
@Translator( JavaFrameworkInterfaceTranslator.class )
public interface Comparable<T> {
    public int compareTo( T other );
}
