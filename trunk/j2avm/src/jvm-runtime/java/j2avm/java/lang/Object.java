package j2avm.java.lang;

import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassTranslator;

import flash.FlashObject;

/**
 * Runtime support for Object
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassTranslator.class )
public class Object extends FlashObject {

}
