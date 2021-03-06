package test;

import java.io.Serializable;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.j2avm.annotations.swf.SWF;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.attributes.RuntimeVisibleAnnotationsAttribute;
import org.epistem.jvm.io.print.ClassPrinter;
import org.epistem.jvm.loader.ClassLoaderLoader;
import org.epistem.jvm.type.ObjectType;

/**
 * A Pithy Comment
 *
 * @author nickmain
 * @deprecated
 */
@SWF( width  = 100, 
      height = 100,
      background = 0xffff00 )
@Deprecated
public final class TestJVM implements Cloneable, Serializable {
    
    public static final int fieldC = 2131;
    public static int field1 = 8;
    public double field2 = 23;
    
    public static void main( String[] args ) throws Exception {
        JVMClassLoader loader = ClassLoaderLoader.forLoader( TestJVM.class.getClassLoader() );
        
        JVMClass clazz = loader.getClass( ObjectType.fromName( "test.TestJVM" ) );
        
   //     try {
            new ClassPrinter( System.currentTimeMillis() > 400 ? IndentingPrintWriter.SYSOUT : null, true ).print( clazz );
            IndentingPrintWriter.SYSOUT.flush();
  //      } catch( Exception ex ) {
   //         System.err.println( ex.getMessage() );
   //     }
        
        ClassPrinter cp = new ClassPrinter( IndentingPrintWriter.SYSOUT );
        new ClassPrinter( IndentingPrintWriter.SYSOUT );
        
        JavaAnnotation swf = clazz.attributes.annotation( SWF.class.getName() );
        swf.dump( IndentingPrintWriter.SYSOUT );
        IndentingPrintWriter.SYSOUT.flush();
    }
    
    private void array() {
        int[] foo = { 1, 2, 3 };
        System.out.println( foo[2] );
    }
    
    public void loop( int max ) {
        for( int i = 0; i < max; i++ ) {
            System.out.println( this );
        }
    }
    
    public void test() {
        field2++;
        for( int i = 0; i < 10; i++ ) {
            System.out.println( System.getenv( "foo" ) );
            System.out.println( field1 );
            System.out.println( field2 += 2);
        }
    }
}
