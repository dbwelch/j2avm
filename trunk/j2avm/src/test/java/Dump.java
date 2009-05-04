import java.io.File;

import org.epistem.j2avm.util.ABCMindMapDumper;

import com.anotherbigidea.flash.avm2.model.AVM2ABCFile;
import com.anotherbigidea.flash.avm2.utils.AVM2ABCFileLoader;
import com.anotherbigidea.flash.writers.SWFTagDumper;


public class Dump {
    public static void main( String[] args ) throws Exception {
        SWFTagDumper.main( new String[] {
            //"/Users/nickmain/Documents/workspace/FlexBuilder/test/bin-debug/test.swf",
            "/Users/nickmain/Documents/workspace/ListSpinner/Test10/bin-debug/test/Test10.swf",
            //"/Users/nickmain/Documents/workspace/ListSpinner/J2AVM_TestBed/bin-debug/J2AVM_TestBed.swf",
            "acts"
        });
        
        AVM2ABCFile abc = AVM2ABCFileLoader.abcFilesFromExistingSWF( new File( "/Users/nickmain/Documents/workspace/ListSpinner/Test10/bin-debug/test/Test10.swf" ) ).iterator().next();
        ABCMindMapDumper dumper = new ABCMindMapDumper( "eclipse-build/Test10.mm" );
        dumper.dump( abc );
    }
}
