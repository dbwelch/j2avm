package org.epistem.j2avm.runtime;

import java.io.IOException;

import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.type.ObjectType;

/**
 * Classloader that looks for classes in the J2AVM runtime support package
 * tree. The loaded classes are renamed to match those being requested.
 *
 * @author nickmain
 */
public class RuntimeLoader extends JVMClassLoader {

    private static final String RUNTIME_PACKAGE = "j2avm.";
    
    /**
     * @param parent the parent loader
     */
    public RuntimeLoader( JVMClassLoader parent ) {
        super( parent );
    }

    @Override
    protected JVMClass loadClass( ObjectType className, ObjectType realName ) throws IOException {
        
        ObjectType runtimeName = new ObjectType( RUNTIME_PACKAGE + className.name );
        
        try {
            return parentLoader.getClass( runtimeName, className );     
            
        } catch( ClassNotFoundException cnfe ) {
            try {
                return parentLoader.getClass( className );
            } catch( ClassNotFoundException cnfe2 ) {
                return null;
            }
        }
    }
}
