package org.epistem.j2avm;

import java.io.File;
import java.io.IOException;

import org.epistem.j2avm.annotations.swf.SWF;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2swf.swf.SWFFile;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.type.ObjectType;

import com.anotherbigidea.flash.structs.Color;

/**
 * Represents a SWF file that is the target for translated code.
 *
 * @author nickmain
 */
public class TargetSWF {

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

        ClassTranslator mainClass = manager.translatorForClass( new ObjectType( className ));        
        manager.setMainClass( mainClass );
        
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
        manager.translateRequiredClasses( code );
        
        swf.setMainClass( mainClass.getAVM2Name().toQualString() );
        swf.timeline.getFrame( 0 ).addTag( code );
    }
    
    /**
     * Write the SWF file
     */
    public void write() throws IOException {
        swf.write( file, compressed );
    }
}
