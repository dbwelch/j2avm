package test;

import java.io.File;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.util.ABCMindMapDumper;
import org.epistem.jvm.loader.ClassLoaderLoader;
import org.epistem.jvm.type.ObjectType;

import com.anotherbigidea.flash.avm2.model.AVM2ABCFile;
import com.anotherbigidea.flash.avm2.utils.AVM2ABCFileLoader;
import com.anotherbigidea.flash.writers.SWFTagDumper;

/**
 * A temporary driver class for development purposes
 *
 * @author nickmain
 */
public class RunTest {

    public static void main( String[] args ) throws Exception {
        
        ClassLoaderLoader loader = ClassLoaderLoader.forLoader( RunTest.class.getClassLoader()); 
        loader.getClass( new ObjectType( "test.Test" ) ).dump( IndentingPrintWriter.SYSOUT, true );
        
        J2AVM.translate( "test.Test", "eclipse-build/test.swf", loader );
        
        SWFTagDumper.main( new String[] { "eclipse-build/test.swf", "acts" } );
        //SWFTagDumper.main( new String[] { "flex-cases/test.swf" } );

        AVM2ABCFile abc = AVM2ABCFileLoader.abcFilesFromExistingSWF( new File( "eclipse-build/test.swf" ) ).iterator().next();
        ABCMindMapDumper dumper = new ABCMindMapDumper( "eclipse-build/test.mm" );
        dumper.dump( abc );
    }
}
