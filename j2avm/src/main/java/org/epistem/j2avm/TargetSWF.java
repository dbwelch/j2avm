package org.epistem.j2avm;

import java.io.File;
import java.io.IOException;

import org.epistem.j2avm.annotations.swf.SWF;
import org.epistem.j2avm.translator.ClassTranslation;
import org.epistem.j2swf.swf.SWFFile;
import org.epistem.j2swf.swf.code.Code;

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
     * @param fileName the target file name
     */
    public TargetSWF( String fileName ) {
        this.file = new File( fileName );
    }

    /**
     * Specify a main class. A SWF annotation on the class will set the
     * relevant swf attributes.
     * 
     * @param fileName the target file name
     * @param mainClass the main class for the SWF
     */
    public TargetSWF( String fileName, 
                      ClassTranslation mainClass ) {
        this( fileName );
     
        //get swf params from annotation
        SWF swfDef = mainClass.getAnnotation( SWF.class );
        if( swfDef != null ) {
            swf.setWidth     ( swfDef.width() );
            swf.setHeight    ( swfDef.height() );
            swf.setBackground( new Color( swfDef.background() ));
            swf.setFrameRate ( swfDef.frameRate() );
            swf.setVersion   ( swfDef.version() );
            compressed = swfDef.compressed();
        }

        //TODO: need way to set the script limits
        
        Code code = new Code( "test" );
        mainClass.translate( code );
        
        swf.setMainClass( mainClass.getAVM2Class() );
        swf.timeline.getFrame( 0 ).addTag( code );
    }
    
    /**
     * Write the SWF file
     */
    public void write() throws IOException {
        swf.write( file, compressed );
    }
}
