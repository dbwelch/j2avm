package org.epistem.j2avm.annotations.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.epistem.j2avm.translator.impl.flash.FlashGetterTranslator;

/**
 * Annotation on methods to indicate a getter method 
 *
 * @author nickmain
 */
@Retention( RetentionPolicy.RUNTIME )
@Target({ ElementType.METHOD })
@DefaultTranslator( FlashGetterTranslator.class )
public @interface Getter {
    //nada
}
