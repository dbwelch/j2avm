package org.epistem.j2avm.translator.impl.flash;

import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.MethodCall;

import com.anotherbigidea.flash.avm2.model.AVM2Code;
import com.anotherbigidea.flash.avm2.model.AVM2LateMultiname;
import com.anotherbigidea.flash.avm2.model.AVM2Namespace;

/**
 * Helper for the static delegate method
 *
 * @author nickmain
 */
public class DelegateTranslator extends FlashNativeMethodTranslator {

    public DelegateTranslator( ClassTranslatorBase classTrans, JVMMethod method ) {
        super( classTrans, method );
    }

    /** @see org.epistem.j2avm.translator.impl.flash.FlashNativeMethodTranslator#translateCall(MethodTranslator, MethodCall, boolean) */
    @Override
    public void translateCall( MethodTranslator method, MethodCall call, boolean isSuper ) {
        AVM2Code code = method.getCode().code();
        code.getLocal( code.thisValue );
        code.swap();
        code.getProperty( new AVM2LateMultiname( AVM2Namespace.publicNamespace ) );
    }
}
