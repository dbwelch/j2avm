package j2avm.java.lang;

import org.epistem.j2avm.annotations.runtime.FlashTargetClass;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassAugmentingTranslator;

import flash.FlashString;

/**
 * Runtime support for String
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassAugmentingTranslator.class )
@FlashTargetClass( FlashString.class )
public class String {

    
    public static java.lang.String valueOf( boolean b ) {
        return b ? "true" : "false";
    }
}
