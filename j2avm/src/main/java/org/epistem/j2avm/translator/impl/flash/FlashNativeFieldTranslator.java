package org.epistem.j2avm.translator.impl.flash;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.FieldTranslatorBase;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.code.instructions.FieldAccess;

/**
 * Translator for Flash native fields
 *
 * @author nickmain
 */
public class FlashNativeFieldTranslator extends FieldTranslatorBase {

    public FlashNativeFieldTranslator( ClassTranslator classTrans, JVMField field ) {
        super( classTrans, field );
    }

    /**
     * @see org.epistem.j2avm.translator.MemberTranslator#translateImplementation(org.epistem.j2swf.swf.code.CodeClass)
     */
    public void translateImplementation( CodeClass codeClass ) {
        // nada
    }

    /**
     * @see org.epistem.j2avm.translator.FieldTranslator#translateRead(MethodTranslator, FieldAccess, boolean)
     */
    public void translateRead( MethodTranslator method, FieldAccess access, boolean isSuper ) {
        if( access.isStatic ) { //push class onto the stack
            classTranslator.translateStaticPush( method );
        }

        if( isSuper ) method.getCode().code().getSuperProperty( avm2Name );
        else method.getCode().code().getProperty( avm2Name );
    }
    
    /**
     * @see org.epistem.j2avm.translator.FieldTranslator#translateWrite(MethodTranslator, FieldAccess, boolean)
     */
    public void translateWrite( MethodTranslator method, FieldAccess access, boolean isSuper ) {
        if( access.isStatic ) {
            classTranslator.translateStaticPush( method );
            method.getCode().code().swap();
        }

        if( isSuper ) method.getCode().code().setSuperProperty( avm2Name );
        else method.getCode().code().setProperty( avm2Name );
    }
}
