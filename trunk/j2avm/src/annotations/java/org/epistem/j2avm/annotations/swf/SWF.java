package org.epistem.j2avm.annotations.swf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for defining the attributes of a SWF file.
 *
 * @author nickmain
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
public @interface SWF {
    int width()  default 400;
    int height() default 450;
    int frameRate() default 12;
    int version() default 9;
    int background() default 0xffffff;
    boolean compressed() default false;    
}
