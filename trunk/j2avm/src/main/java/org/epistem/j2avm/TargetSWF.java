package org.epistem.j2avm;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.epistem.j2avm.annotations.swf.SWF;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.TranslationState;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2swf.swf.SWFFile;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.attributes.JavaAnnotation;

import com.anotherbigidea.flash.structs.Color;

/**
 * Represents a SWF file that is the target for translated code.
 *
 * @author nickmain
 */
public class TargetSWF {

    private final TranslatorManager manager;
    
    /**
     * Classes that have already been translated into this SWF
     */
    private Set<String> translatedClasses = new HashSet<String>();
        
    /**
     * The swf model
     */
    public final SWFFile swf = new SWFFile();
    
    /**
     * The target file
     */
    private final File file;
    
    /**
     * Whether to write a compressed SWF
     */
    public boolean compressed = true;
    
    /**
     * @param manager the translation manager
     * @param fileName the target file name
     * @param className the main class
     */
    public TargetSWF( TranslatorManager manager, String fileName, String className ) 
        throws ClassNotFoundException, IOException {
        
        this.file = new File( fileName );
        this.manager = manager;        

        ClassTranslator mainClass = manager.getClassTranslation( className );
        
        //get swf params from annotation
        JavaAnnotation swfDef = mainClass.getAnnotation( SWF.class.getName() );
        if( swfDef != null ) {
            swf.setWidth     ( swfDef.intValue( "width" ) );
            swf.setHeight    ( swfDef.intValue( "height" ) );
            swf.setBackground( new Color( swfDef.intValue( "background" )));
            swf.setFrameRate ( swfDef.intValue( "frameRate" ));
            swf.setVersion   ( swfDef.intValue( "version" ));
            compressed = swfDef.boolValue( "compressed" );
        }

        //TODO: need way to set the script limits
        
        Code code = new Code( "test" );
        TranslationState state = new TranslationState( manager, code, translatedClasses );
        
        CodeClass avm2Class = mainClass.translate( state );
        translatedClasses.add( className );
        state.translateDependencies();
        
        swf.setMainClass( avm2Class );
        swf.timeline.getFrame( 0 ).addTag( code );
    }
    
    /**
     * Write the SWF file
     */
    public void write() throws IOException {
        swf.write( file, compressed );
    }
}
