package org.epistem.j2avm.annotations.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.epistem.j2avm.translator.impl.flash.FlashNativeClassTranslator;

/**
 * Annotation on Java classes that represent Flash player native classes
 *
 * @author nickmain
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
@DefaultTranslator( FlashNativeClassTranslator.class )
public @interface FlashNativeClass {
    //nada
}
