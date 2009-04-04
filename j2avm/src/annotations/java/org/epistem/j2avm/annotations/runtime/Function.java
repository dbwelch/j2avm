package org.epistem.j2avm.annotations.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.epistem.j2avm.translator.impl.flash.FlashFunctionTranslator;

/**
 * Annotation on methods to indicate a standalone function
 *
 * @author nickmain
 */
@Retention( RetentionPolicy.RUNTIME )
@Target({ ElementType.METHOD })
@DefaultTranslator( FlashFunctionTranslator.class )
public @interface Function {
    String value(); //the fully qualified function name
}
