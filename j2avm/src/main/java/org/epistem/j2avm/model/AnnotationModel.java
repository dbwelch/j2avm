package org.epistem.j2avm.model;

import java.util.HashMap;

import org.epistem.j2avm.translator.ClassTranslation;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.tree.AnnotationNode;

/**
 * A model of an annotation as a map.
 * 
 * The map values can be Byte, Boolean, Character, Short, Integer, Long, Float, 
 * Double, String, Type, List, Anno or FieldModel (an Enum)
 *
 * @author nickmain
 */
public class AnnotationModel extends HashMap<String,Object> {

    /**
     * The annotation type
     */
    public final ClassTranslation annotationClass;
    
    /**
     * @param annotationClass the annotation type
     * @param node the parsed annotation instance
     */
    public AnnotationModel( ClassTranslation annotationClass, AnnotationNode node ) {
        this.annotationClass = annotationClass;
        
        //TODO:
    }
    
    
}
