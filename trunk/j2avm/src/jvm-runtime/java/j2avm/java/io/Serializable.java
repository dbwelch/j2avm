package j2avm.java.io;

import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkInterfaceTranslator;

/**
 * Runtime support for Serializable
 *
 * @author nickmain
 */
@Translator( JavaFrameworkInterfaceTranslator.class )
public interface Serializable {
}
