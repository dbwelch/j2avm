package org.epistem.jvm;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.epistem.jvm.io.parser.ClassFileParser;
import org.epistem.jvm.loader.SystemLoader;
import org.epistem.jvm.type.ObjectType;

/**
 * Extended by loaders and creators of JVMClasses.
 * 
 * JVMClassLoaders have parents, the top parent in any chain is the
 * SystemLoader.
 * 
 * @author nickmain
 */
public abstract class JVMClassLoader {
     
    /**
     * The parent loader is delegated to if this loader cannot find the class.
     * Note: this is unlike the normal ClassLoader behavior of going to the
     * parent first.
     */
    protected final JVMClassLoader parentLoader;
    
    private final Map<ObjectType,JVMClass> cache = new HashMap<ObjectType, JVMClass>();
    /*pkg*/ final void cacheClass( JVMClass jclass ) { cache.put( jclass.name, jclass ); }    
    
    /**
     * @param parent the parent classloader - set to system loader if null
     */
    protected JVMClassLoader( JVMClassLoader parent ) {
        if( parent == null && !( this instanceof SystemLoader)) {
            parent = SystemLoader.instance;
        }
        
        this.parentLoader = parent;
    }

    /**
     * Load a class or get it from a cache.
     * 
     * @param className the name of the class to load
     * @return the class
     * @throws IOException if the class was found but could not be loaded
     * @throws ClassNotFoundException if the class could not be found
     */
    public final JVMClass getClass( ObjectType className ) 
        throws IOException, ClassNotFoundException {
    
        return getClass( className, null );
    }

    
    /**
     * Load a class or get it from a cache.
     * 
     * @param className the name of the class to load
     * @param realName the real name for the class - null for none
     * @return the class
     * @throws IOException if the class was found but could not be loaded
     * @throws ClassNotFoundException if the class could not be found
     */
    public final JVMClass getClass( ObjectType className, ObjectType realName ) 
        throws IOException, ClassNotFoundException {
        
        JVMClass clazz = getWithoutLoading( className );
        if( clazz != null ) return clazz;
        
        clazz = loadClass( className, realName );
        if( clazz != null ) {
            cacheClass( clazz );
        }
        else if( parentLoader != null ) { 
            try {
                clazz = parentLoader.getClass( className );
            } catch( ClassNotFoundException ignored ) {}
        }
        
        if( clazz == null ) throw new ClassNotFoundException( className.name );
        
        return clazz;
    }
    
    /**
     * Load a class.
     * 
     * @param className the name of the class to load
     * @param realName the real name for the class - null for none
     * @return null if the class was not found
     * @throws IOException if the class was found but could not be loaded
     */
    protected abstract JVMClass loadClass( ObjectType className, ObjectType realName ) throws IOException;
    
    /**
     * Create a JClass from data in the given input
     * @param name the class name
     * @param realName the real name for the class - null for none
     * @param in the class file
     * @return the JClass
     * @throws IOException if the class file data could not be read
     */
    protected final JVMClass loadClass( ObjectType name, ObjectType realName, InputStream in ) 
        throws IOException {
        //System.out.println( "LOADING CLASS: " + name );

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buff = new byte[1000];
        int count = 0;
        
        while((count = in.read( buff )) >= 0 ) {
            bout.write( buff, 0, count );
        }        
        in.close();
        
        byte[] classData = bout.toByteArray();
        DataInputStream dataIn = new DataInputStream( new ByteArrayInputStream( classData ));
        ClassFileParser parser = new ClassFileParser( dataIn, this, realName );
        return parser.parseClass();
    }
    
    /**
     * Make a file path from a class name
     */
    protected final String makeFilePath( String className ) {
        return className.replace( '.', '/' ) + ".class";
    }
    
    // get class from the cache, or parent cache, without attempting to load
    private JVMClass getWithoutLoading( ObjectType className ) {
        if( parentLoader != null ) {
            JVMClass jclass = parentLoader.getWithoutLoading( className );
            if( jclass != null ) return jclass;
        }
        
        return cache.get( className );
    }
}
