package org.epistem.jvm;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.epistem.jvm.JVMAttribute.Name;
import org.epistem.jvm.attributes.Annotations;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.attributes.RuntimeInvisibleAnnotationsAttribute;
import org.epistem.jvm.attributes.RuntimeVisibleAnnotationsAttribute;

/**
 * A container for attributes
 *
 * @author nickmain
 */
public class AttributeContainer {

    private final JVMClassLoader loader;

    //annotation cache for filled-in annotations
    private final Map<String,JavaAnnotation> defaultedAnnotations = new HashMap<String, JavaAnnotation>();

    /**
     * The attributes by name
     */
    public final Map<String, JVMAttribute> byName = new HashMap<String, JVMAttribute>();
        
    /**
     * Get the attribute with the given class
     * 
     * @return null if the attribute does not exist
     */
    public <T extends JVMAttribute> T forClass( Class<T> attrClass ) {
        Name name = Name.forClass( attrClass );
        @SuppressWarnings("unchecked")
        T t = (T) byName.get( name.name() );
        return t;
    }
    
    /**
     * Get the annotation with the given name. If it exists it will be filled
     * in with default values (and cached).
     * 
     * @param name the fully qualified annotation class name
     * @return null if the annotation does not exist
     * @throws ClassNotFoundException if the annotation exists but the annotation
     *         class itself cannot be found
     * @throws IOException if there is a problem parsing the annotation class        
     */
    @SuppressWarnings("unchecked")
    public JavaAnnotation annotation( String name ) throws ClassNotFoundException, IOException {
        
        JavaAnnotation anno = defaultedAnnotations.get( name );
        if( anno != null ) return anno;
        
        lookInAttributes( name, 
                          RuntimeVisibleAnnotationsAttribute.class,
                          RuntimeInvisibleAnnotationsAttribute.class );
        
        return defaultedAnnotations.get( name );
    }
    
    /**
     * Get all the annotations
     */
    @SuppressWarnings("unchecked")
    public Collection<JavaAnnotation> annotations() 
        throws ClassNotFoundException, IOException {
        
        loadAnnotations( RuntimeVisibleAnnotationsAttribute.class,
                         RuntimeInvisibleAnnotationsAttribute.class );
        
        return defaultedAnnotations.values();
    }
    
    private void lookInAttributes( String name, Class<? extends Annotations>... classes ) 
        throws ClassNotFoundException, IOException {
        
        for( int i = 0; i < classes.length; i++ ) {
            Annotations annos = forClass( classes[i] );
            if( annos == null ) continue;
            JavaAnnotation anno = annos.annotations.get( name );
            if( anno == null ) continue;
            
            anno.fillInDefaults( loader );
            defaultedAnnotations.put( name, anno );
            return;
        }
    }

    //load all the annotations in the given attributes
    private void loadAnnotations( Class<? extends Annotations>... classes ) 
        throws ClassNotFoundException, IOException {
    
        for( int i = 0; i < classes.length; i++ ) {
            Annotations annos = forClass( classes[i] );
            if( annos == null ) continue;
            
            for( JavaAnnotation anno : annos.annotations.values() ) {
                anno.fillInDefaults( loader );
                defaultedAnnotations.put( anno.type.name, anno );                
            }
        }
    }
    
    /**
     * @param loader the loader to use for resolving value defaults
     */
    public AttributeContainer( JVMClassLoader loader ) {
        this.loader = loader;
    }
}
