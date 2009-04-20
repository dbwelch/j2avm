package org.epistem.j2avm.translator.impl.framework;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.translator.impl.java.JavaClassTranslator;
import org.epistem.j2avm.translator.impl.java.JavaInterfaceTranslator;
import org.epistem.j2avm.translator.impl.java.JavaStaticInitMethodTranslator;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.InstanceOf;
import org.epistem.jvm.code.instructions.New;
import org.epistem.jvm.type.ObjectType;

import com.anotherbigidea.flash.avm2.model.AVM2QName;

import flash.FlashObject;

/**
 * Translator for Java framework classes
 *
 * @author nickmain
 */
public class JavaFrameworkInterfaceTranslator extends JavaInterfaceTranslator {

    public JavaFrameworkInterfaceTranslator( TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass, JavaFrameworkClassTranslator.makeName( jvmClass.name ) );
    }
    
    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#getSuperclass() */
    @Override
    public ClassTranslator getSuperclass() {
        return super.getSuperclass();
    }

    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultFieldTranslator(org.epistem.jvm.JVMField) */
    @Override
    public FieldTranslator defaultFieldTranslator( JVMField field ) {
        return new JavaFrameworkFieldTranslator( this, field );
    }

    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultMethodTranslator(org.epistem.jvm.JVMMethod) */
    @Override
    public MethodTranslator defaultMethodTranslator( JVMMethod method ) {
        if( method.name.equals( "<clinit>" ) ) {
            return new JavaStaticInitMethodTranslator( this, method );
        }
        
        return new JavaFrameworkMethodTranslator( this, method );
    }
}
