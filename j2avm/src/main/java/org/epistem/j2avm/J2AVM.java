package org.epistem.j2avm;

import java.io.IOException;
import java.util.logging.Logger;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.j2avm.translator.ClassTranslation;
import org.epistem.jvm.JVMClassLoader;

/**
 * Static utilities for invoking J2AVM
 *
 * @author nickmain
 */
public class J2AVM {

    /**
     * The default logger
     */
    public static final Logger log = Logger.getLogger( "J2AVM" );
    
    /**
     * Translate a class to a SWF. The class must extend the Flash MovieClip
     * class. If the class is annotated with the SWF annotation then that will
     * affect the SWF that is generated.
     * 
     * @param className the fully qualified class name
     * @param targetFile the SWF file to create or overwrite
     * @param loader the loader used to find the class data
     * @throws ClassNotFoundException if the class is not found
     */
    public static void translate( String className, String targetFile, JVMClassLoader loader ) 
        throws ClassNotFoundException, IOException {
        
        TranslationManager translator = new TranslationManager( loader );
        ClassTranslation mainClass = translator.getClassTranslation( className );
        
        //TODO: debugging
        mainClass.jvmClass.dump( IndentingPrintWriter.SYSOUT );

        TargetSWF swf = new TargetSWF( targetFile, mainClass );
        swf.write();
    }
}
