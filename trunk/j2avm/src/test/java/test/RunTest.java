package test;

import org.epistem.j2avm.J2AVM;
import org.epistem.jvm.loader.ClassLoaderLoader;

import com.anotherbigidea.flash.writers.SWFTagDumper;

/**
 * A temporary driver class for development purposes
 *
 * @author nickmain
 */
public class RunTest {

    public static void main( String[] args ) throws Exception {
        
        J2AVM.translate( "test.Test", "eclipse-build/test.swf", 
                         ClassLoaderLoader.forLoader( RunTest.class.getClassLoader()));
        
        SWFTagDumper.main( new String[] { "eclipse-build/test.swf", "acts" } );
        //SWFTagDumper.main( new String[] { "flex-cases/test.swf" } );
    }
}
