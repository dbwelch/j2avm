package org.epistem.server.http.handlers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation on a method parameter to declare the querystring or POST parameter
 * name that is passed via the parameter.
 * This is used with the HTTPAnnotatedPojoHandler.
 *
 * @author nickmain
 */
@Target( ElementType.PARAMETER )
@Retention( RetentionPolicy.RUNTIME )
public @interface ParamName {
    String value();
}
