package org.epistem.j2avm.translator.impl.flash;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;

/**
 * Translator for Flash native getter methods
 *
 * @author nickmain
 */
public class FlashGetterTranslator extends FlashNativeMethodTranslator {

    public FlashGetterTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method );
    }

    /** @see org.epistem.j2avm.translator.impl.flash.FlashNativeMethodTranslator#translateCall(MethodTranslator, MethodCall, boolean) */
    @Override
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {
        
        String propName = NameUtils.nameFromAccessor( jvmName );
        method.runtimeTrace( "Getter: " + propName );
        method.getCode().code().getProperty( propName );
        
        //TODO: verify that this can get a property defined by a superclass
    }

}
