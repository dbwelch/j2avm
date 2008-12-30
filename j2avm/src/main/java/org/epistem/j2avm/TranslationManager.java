package org.epistem.j2avm;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.epistem.j2avm.translator.ClassTranslation;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.type.ObjectType;

/**
 * Caches ClassTranslations and manages the translation
 *
 * @author nickmain
 */
public class TranslationManager {

    /**
     * The loader being used
     */
    public final JVMClassLoader loader;
    
    private final Map<String, ClassTranslation> classes = 
        new HashMap<String, ClassTranslation>();
    
    //java classes to be translated
    private final LinkedList<String> translationQueue = new LinkedList<String>();
    
    /**
     * @param loader the loader to use to find Java classes
     */
    public TranslationManager( JVMClassLoader loader ) {
        this.loader = loader;
    }
    
    /**
     * Get the translation wrapper for a Java class
     * @param name the fully qualified Java class name
     * 
     * @throws ClassNotFoundException if the class cannot be found
     * @throws IOException if there is a problem parsing the class
     */
    public ClassTranslation getClassTranslation( String name ) 
        throws ClassNotFoundException, IOException {
        
        ClassTranslation trans = classes.get( name );
        
        if( trans == null ) {
            JVMClass jvmClass = loader.getClass( new ObjectType( name ) );
            trans = new ClassTranslation( this, jvmClass );
            classes.put( name, trans );
        }        
        
        return trans;
    }
}
