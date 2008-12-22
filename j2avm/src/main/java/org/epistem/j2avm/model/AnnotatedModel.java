package org.epistem.j2avm.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.tree.AnnotationNode;

/**
 * Base for model classes that can contain annotations
 *
 * @author nickmain
 */
public abstract class AnnotatedModel {

    //the annotations by fully qualified annotation class name
    private Map<String, AnnotationModel> annotations = new HashMap<String, AnnotationModel>();
    
    /**
     * Get an annotation
     * 
     * @param name the fully qualified annotation class name
     * @return null if the annotation does not exist
     */
    public final AnnotationModel getAnnotation( String name ) {
        AnnotationModel anno = annotations.get( name );
        if( anno == null ) {
         
            
        }
        
        return anno;
    }
    
    /**
     * Gather all the annotations into the given list
     */
    protected abstract void gatherAnnotationNodes( List<AnnotationNode> annos );
}
