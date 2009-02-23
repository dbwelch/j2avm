package org.epistem.j2avm.annotations.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.epistem.j2avm.translator.impl.flash.FlashSetterTranslator;

/**
 * Annotation on methods to indicate a setter method 
 *
 * @author nickmain
 */
@Retention( RetentionPolicy.RUNTIME )
@Target({ ElementType.METHOD })
@DefaultTranslator( FlashSetterTranslator.class )
public @interface Setter {
    //nada
}
