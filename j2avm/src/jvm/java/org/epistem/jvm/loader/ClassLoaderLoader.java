package org.epistem.jvm.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.type.ObjectType;

/**
 * A JClassLoader that pulls class files from a given ClassLoader.
 *
 * @author nickmain
 */
public final class ClassLoaderLoader extends JVMClassLoader {

    public final ClassLoader loader;
    
    private static final Map<ClassLoader, ClassLoaderLoader> cache = 
        new HashMap<ClassLoader, ClassLoaderLoader>();
    
    /**
     * Get a (cached) loader for the given ClassLoader
     */
    public static ClassLoaderLoader forLoader( ClassLoader loader ) {
        ClassLoaderLoader cl = cache.get( loader );
        if( cl == null ) {
            cl = new ClassLoaderLoader( loader, null );
        }
        
        return cl;
    }
    
    /**
     * @param loader the loader to use
     * @param parent the parent loader - may be null
     */
    public ClassLoaderLoader( ClassLoader loader, JVMClassLoader parent ) {
        super( parent );
        this.loader = loader;
    }

    /**
     * @see org.epistem.jvm.JVMClassLoader#loadClass(org.epistem.jvm.type.ObjectType)
     */
    @Override
    protected final JVMClass loadClass(ObjectType className) throws IOException {     
        String filename = makeFilePath(className.name);
        InputStream in = loader.getResourceAsStream( filename );
        if( in == null ) return null;
        return loadClass(className,in);
    }
}
