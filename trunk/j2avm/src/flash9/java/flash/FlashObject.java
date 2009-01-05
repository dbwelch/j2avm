package flash;

import org.epistem.j2avm.annotations.runtime.FlashNativeClass;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.helpers.TraceHelper;

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
    @Translator( TraceHelper.class )
    public static void trace( String message ) {}

}
