package org.epistem.server.http.handlers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation on a POJO method to declare the URL sub-path regex that targets
 * the method.  This is used with the HTTPAnnotatedPojoHandler.
 *
 * @author nickmain
 */
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface PathRegex {
    String value();
}
