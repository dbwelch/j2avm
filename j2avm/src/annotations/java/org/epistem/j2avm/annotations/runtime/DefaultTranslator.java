package org.epistem.j2avm.annotations.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.epistem.j2avm.translator.TranslationHelper;

/**
 * Annotation on annotations to provide the default Translation Helper for
 * the elements that annotation is on.
 *
 * @author nickmain
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.ANNOTATION_TYPE )
public @interface DefaultTranslator {
    Class<? extends TranslationHelper> value();
}
