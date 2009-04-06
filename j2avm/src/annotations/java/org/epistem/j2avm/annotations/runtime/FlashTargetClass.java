package org.epistem.j2avm.annotations.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation on a Java class to specify a Flash native class that it targets -
 * particularly for augmenting a Flash class 
 * 
 * @author nickmain
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
public @interface FlashTargetClass {
    String value();
}
