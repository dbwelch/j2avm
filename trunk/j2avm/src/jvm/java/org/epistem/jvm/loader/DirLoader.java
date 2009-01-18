package org.epistem.jvm.loader;

import java.io.*;

import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.type.ObjectType;

/**
 * A JVMClassLoader that looks for class files within a given filesystem
 * directory.
 *
 * @author nickmain
 */
public final class DirLoader extends JVMClassLoader {

    public final File basePath;

    /**
     * @param dir the directory base
     */
    public DirLoader( File dir, JVMClassLoader parent ) {
        super( parent );
        this.basePath = dir;
    }

    /**
     * @see org.epistem.jvm.JVMClassLoader#loadClass(ObjectType, ObjectType)
     */
    @Override
    protected final JVMClass loadClass(ObjectType className, ObjectType realName) throws IOException {        
        try {
            File classFile = new File( basePath, makeFilePath(className.name) );
            InputStream in = new FileInputStream( classFile );
            return loadClass(className,realName,in);
            
        } catch( FileNotFoundException nfe ) {
            return null;
        }
    }
}
