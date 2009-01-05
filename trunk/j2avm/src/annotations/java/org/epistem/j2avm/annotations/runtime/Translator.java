package org.epistem.j2avm.annotations.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.epistem.j2avm.translator.TranslationHelper;

/**
 * Annotation on classes, methods or fields to indicate the TranslationHelper
 * that will translate a call or access
 *
 * @author nickmain
 */
@Retention( RetentionPolicy.RUNTIME )
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
public @interface Translator {
    Class<? extends TranslationHelper> value();
}
