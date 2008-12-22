package org.epistem.j2avm.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A factory and cache for ClassModels
 *
 * @author nickmain
 */
public class ClassModelFactory {

    private final Map<String, ClassModel> classes = new HashMap<String, ClassModel>();    
    private final ClassLoader loader;
    
    /**
     * @param loader the loader for class files
     */
    public ClassModelFactory( ClassLoader loader ) {
        this.loader = loader;
    }
    
    /**
     * Get a class model
     * 
     * @param name the fully qualified class name
     * @return not null
     * @throws ClassNotFoundException if the class cannot be found
     * @throws IOException if the class file could not be read or parsed
     */
    public ClassModel modelForClass( String name ) 
        throws ClassNotFoundException, IOException {
        
        ClassModel model = classes.get( name );
        if( model == null ) {
            String classFile = name.replace( '.', '/' ) + ".class";
            InputStream in = loader.getResourceAsStream( classFile );
            if( in == null ) throw new ClassNotFoundException( name );
            
            model = new ClassModel( this, name, in );
            classes.put( name, model );
        }
        
        return model;
    }
}
