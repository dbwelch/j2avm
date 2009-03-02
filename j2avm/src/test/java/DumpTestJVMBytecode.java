import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.loader.ClassLoaderLoader;
import org.epistem.jvm.type.ObjectType;

import test.RunTest;

public class DumpTestJVMBytecode {
    
    public static void main( String[] args ) throws Exception {
        JVMClassLoader loader = ClassLoaderLoader.forLoader( RunTest.class.getClassLoader()); 
        
        JVMClass clazz = loader.getClass( new ObjectType( "test.Test" ) );
        for( JVMMethod meth : clazz.methods ) meth.analyzer();
        
        clazz.dump( IndentingPrintWriter.SYSOUT );
    }
}
