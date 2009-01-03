package org.epistem.j2avm.translator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.type.ObjectType;

/**
 * Caches ClassTranslators 
 *
 * @author nickmain
 */
public class TranslatorManager {

    /**
     * The loader being used
     */
    public final JVMClassLoader loader;
    
    private final Map<String, ClassTranslator> classes = new HashMap<String, ClassTranslator>();
    
    /**
     * @param loader the loader to use to find Java classes
     */
    public TranslatorManager( JVMClassLoader loader ) {
        this.loader = loader;
    }
    
    /**
     * Get the translation wrapper for a Java class
     * @param name the fully qualified Java class name
     * 
     * @throws ClassNotFoundException if the class cannot be found
     * @throws IOException if there is a problem parsing the class
     */
    public ClassTranslator getClassTranslation( String name ) 
        throws ClassNotFoundException, IOException {
        
        ClassTranslator trans = classes.get( name );
        
        if( trans == null ) {
            JVMClass jvmClass = loader.getClass( new ObjectType( name ) );
            trans = new ClassTranslator( this, jvmClass );
            classes.put( name, trans );
        }        
        
        return trans;
    }
}
