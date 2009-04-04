package org.epistem.j2avm.generator;

import java.io.File;
import java.util.Collection;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.epistem.io.IndentingPrintWriter;

import com.anotherbigidea.flash.avm2.model.AVM2ABCFile;
import com.anotherbigidea.flash.avm2.model.AVM2QName;
import com.anotherbigidea.flash.avm2.utils.AVM2ABCFileLoader;

/**
 * ANT task to dump the contents of a given ABC file - the one that contains
 * a given class
 *
 * @author nickmain
 */
public class ABCDumpTask extends Task {

    private File swf;
    private AVM2QName className;
    private IndentingPrintWriter out;    
    private Collection<AVM2ABCFile> abcFiles;
    
    /**
     * The SWF file to read
     */
    public void setSwf( File swf ) {
        this.swf = swf;
    }
    
    /**
     * The name of the class to look for
     */
    public void setClassName( String className ) {
        this.className = new AVM2QName( className );
    }

    /** @see org.apache.tools.ant.Task#execute() */ 
    @Override
    public void execute() throws BuildException {
        
        if( swf == null || ! swf.exists() || ! swf.isFile() ) {
            throw new BuildException( "No valid SWF file" );
        }
        
        if( className == null ) {
            throw new BuildException( "No valid AVM2 class name" );
        }

        try {
            abcFiles = AVM2ABCFileLoader.abcFilesFromExistingSWF( swf );

            for( AVM2ABCFile abc : abcFiles ) {
                if( abc.classes.containsKey( className ) ) {
                    log( "Found " + className );
                    
                    out = IndentingPrintWriter.SYSOUT;
                    abc.dump( out );                 
                    out.flush();
                    return;
                }
            }        
            
            log( "Could not find AVM2 class " + className );
        } catch( BuildException e ) {
            throw e;
        } catch( Exception e ) {
            e.printStackTrace();
            throw new BuildException( e );   
        }    
    }
}
