package org.epistem.j2avm.generator;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * ANT task to generate Java representations of the classes in a SWF
 *
 * @author nickmain
 */
public class FlashNativeGeneratorTask extends Task {

    private File swf;
    private File dir;
    
    /**
     * The SWF file to read
     */
    public void setSwf( File swf ) {
        this.swf = swf;
    }
    
    /**
     * The target dir for the generated Java files
     */
    public void setDir( File dir ) {
        this.dir = dir;
    }

    /** @see org.apache.tools.ant.Task#execute() */ 
    @Override
    public void execute() throws BuildException {
        
        if( swf == null || ! swf.exists() || ! swf.isFile() ) {
            throw new BuildException( "No valid SWF file" );
        }
        
        if( dir == null || ! dir.exists() || ! dir.isDirectory() ) {
            throw new BuildException( "No valid destination dir" );
        }
        
        FlashNativeGenerator fng = new FlashNativeGenerator();
        
        try {
            if( ! fng.readSWF( swf ) ) {
                throw new BuildException( "No code could be found in SWF" );            
            }

            log( "Generating Java classes from " + swf );        
            fng.generateJava( dir );
        } catch( BuildException e ) {
            throw e;
        } catch( Exception e ) {
            e.printStackTrace();
            throw new BuildException( e );   
        }    
    }
    
    
}
