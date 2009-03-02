package org.epistem.j2avm.translator.impl.framework;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.FieldTranslatorBase;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.code.instructions.FieldAccess;

/**
 * Translator for Java framework fields
 *
 * @author nickmain
 */
public class JavaFrameworkFieldTranslator extends FieldTranslatorBase {

    public JavaFrameworkFieldTranslator( ClassTranslator classTrans, JVMField field ) {
        super( classTrans, field );
    }

    /** @see org.epistem.j2avm.translator.FieldTranslator#translateRead(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.FieldAccess, boolean) */
    public void translateRead( MethodTranslator method, FieldAccess access, boolean isSuper ) {
        throw new RuntimeException( "UNIMPLEMENTED" );//TODO: 
    }

    /** @see org.epistem.j2avm.translator.FieldTranslator#translateWrite(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.FieldAccess, boolean) */
    public void translateWrite( MethodTranslator method, FieldAccess access, boolean isSuper ) {
        throw new RuntimeException( "UNIMPLEMENTED" );//TODO:
    }

    /** @see org.epistem.j2avm.translator.MemberTranslator#translateImplementation(org.epistem.j2swf.swf.code.CodeClass) */
    public void translateImplementation( CodeClass codeClass ) {
        throw new RuntimeException( "UNIMPLEMENTED" );//TODO: 
    }
}
