package flash;

import org.epistem.j2avm.annotations.runtime.FlashNativeClass;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.flash.DelegateTranslator;
import org.epistem.j2avm.translator.impl.flash.FlashTraceTranslator;

/**
 * Flash utilities
 *
 * @author nickmain
 */
@FlashNativeClass
public final class Flash {

    private Flash() {} //prevent instantiation
    
    /**
     * The global trace() function
     * 
     * @param message the message to write
     */
    @Translator( FlashTraceTranslator.class )
    public static void trace( Object message ) {
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
