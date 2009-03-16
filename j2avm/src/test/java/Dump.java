import com.anotherbigidea.flash.writers.SWFTagDumper;


public class Dump {
    public static void main( String[] args ) throws Exception {
        SWFTagDumper.main( new String[] {
            "/Volumes/iDisk/Documents/TestFlex/vector.swf",
            //"/Users/nickmain/Documents/workspace/ListSpinner/J2AVM_TestBed/bin-debug/J2AVM_TestBed.swf",
            "acts"
        });
    }
}
