package flash;

import org.epistem.j2avm.annotations.runtime.FlashNativeClass;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.flash.DelegateTranslator;
import org.epistem.j2avm.translator.impl.flash.FlashTraceTranslator;

/**
 * Base for Flash player objects
 *
 * @author nickmain
 */
@FlashNativeClass
public class FlashObject {

    /**
     * The global trace() function
     * 
     * @param message the message to write
     */
    @Translator( FlashTraceTranslator.class )
    public static void trace( String message ) {
        System.out.println( "trace > " + message );
    }
    
    /**
     * Create a method delegate function
     * 
     * @param name the method name
     * @return a function that delegates to the given method and the current instance
     */
    @Translator( DelegateTranslator.class )
    public static FlashFunction delegate( String name ) {
        return null;
    }
}
