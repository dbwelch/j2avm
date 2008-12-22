package org.epistem.j2avm.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

/**
 * A model of a Java class
 *
 * @author nickmain
 */
public class ClassModel extends AnnotatedModel {

    /**
     * The fully qualified class name 
     */
    public final String name;
    
    /**
     * The factory that loaded this model
     */
    public final ClassModelFactory factory;
    
    private final ClassNode classNode = new ClassNode();
    
    /**/ ClassModel( ClassModelFactory factory, String name, InputStream in ) 
        throws IOException {
        
        this.factory = factory;
        this.name    = name;
        
        ClassReader reader = new ClassReader( in );
        reader.accept( classNode, 0 );
    }

    /** @see org.epistem.j2avm.model.AnnotatedModel#gatherAnnotationNodes(java.util.List) */
    @Override
    protected void gatherAnnotationNodes( List<AnnotationNode> annos ) {        
        annos.addAll( classNode.invisibleAnnotations );
        annos.addAll( classNode.visibleAnnotations );        
    }
    
    
}
