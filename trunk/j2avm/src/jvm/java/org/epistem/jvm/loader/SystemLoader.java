package org.epistem.jvm.loader;

import java.io.IOException;

import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.type.ObjectType;

/**
 * The system class loader - equivalent to the JVM system classloader.
 * All primitive classes are loaded via this instance.
 *
 * @author nickmain
 */
public final class SystemLoader extends JVMClassLoader {

    /**
     * The singleton instance
     */
    public static final JVMClassLoader instance = new SystemLoader();
    
    private final JVMClassLoader systemCLwrapper = 
        new ClassLoaderLoader( ClassLoader.getSystemClassLoader(), null );
    
    private SystemLoader() {
        super( null );
    }

    /**
     * @see org.epistem.jvm.JVMClassLoader#loadClass(ObjectType)
     */
    @Override
    protected JVMClass loadClass(ObjectType className) throws IOException {
        try {
            return systemCLwrapper.getClass( className );
        } catch( ClassNotFoundException cnfe ) {
            return null;
        }
    }
}
