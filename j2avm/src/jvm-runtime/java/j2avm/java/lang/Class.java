package j2avm.java.lang;

import org.epistem.j2avm.annotations.runtime.FlashTargetClass;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassAugmentingTranslator;

import flash.FlashClass;

/**
 * Runtime support for Class
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassAugmentingTranslator.class )
@FlashTargetClass( FlashClass.class )
public class Class {
    
    /** @see java.lang.Object#toString() */
    public java.lang.String toString() {
        return "java.lang.Class@" + hashCode();
    }
}
