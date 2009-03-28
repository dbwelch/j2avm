package test;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.j2avm.J2AVM;
import org.epistem.jvm.loader.ClassLoaderLoader;
import org.epistem.jvm.type.ObjectType;

import com.anotherbigidea.flash.writers.SWFTagDumper;

/**
 * A temporary driver class for development purposes
 *
 * @author nickmain
 */
public class RunTest {

    public static void main( String[] args ) throws Exception {
        
        ClassLoaderLoader loader = ClassLoaderLoader.forLoader( RunTest.class.getClassLoader()); 
        loader.getClass( new ObjectType( "test.Test$Painter" ) ).dump( IndentingPrintWriter.SYSOUT );
        
        J2AVM.translate( "test.Test", "eclipse-build/test.swf", loader );
        
        SWFTagDumper.main( new String[] { "eclipse-build/test.swf", "acts" } );
        //SWFTagDumper.main( new String[] { "flex-cases/test.swf" } );
    }
}
