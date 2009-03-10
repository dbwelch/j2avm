package org.epistem.j2avm.annotations.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation on a Java method to override the name mangling and provide an
 * explicit name. This name must be unique within the class.
 *
 * @author nickmain
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface Name {
    String value();
}
