package org.epistem.j2avm.translator.impl.framework;

import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.InstanceOf;
import org.epistem.jvm.code.instructions.New;

/**
 * Translator for Java framework classes
 *
 * @author nickmain
 */
public class JavaFrameworkClassTranslator extends ClassTranslatorBase {

    public JavaFrameworkClassTranslator( TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass );
        
        addAllMemberTranslators();
    }
    
    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultFieldTranslator(org.epistem.jvm.JVMField) */
    @Override
    public FieldTranslator defaultFieldTranslator( JVMField field ) {
        return new JavaFrameworkFieldTranslator( this, field );
    }

    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultMethodTranslator(org.epistem.jvm.JVMMethod) */
    @Override
    public MethodTranslator defaultMethodTranslator( JVMMethod method ) {
        return new JavaFrameworkMethodTranslator( this, method );
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateImplementation(Code))
     */
    public void translateImplementation( Code code ) {
        //throw new RuntimeException( "UNIMPLEMENTED" );//TODO:
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateInstanceOf(MethodTranslator, InstanceOf)
     */
    public void translateInstanceOf( MethodTranslator method, InstanceOf instOfInsn ) {
        throw new RuntimeException( "UNIMPLEMENTED" );//TODO:
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateInstantiation(MethodTranslator, New)
     */
    public void translateInstantiation( MethodTranslator method, New newInsn ) {
        throw new RuntimeException( "UNIMPLEMENTED" );//TODO: 
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateStaticPush(MethodTranslator)
     */
    public void translateStaticPush( MethodTranslator method ) {
        throw new RuntimeException( "UNIMPLEMENTED" );//TODO:
    }
}
