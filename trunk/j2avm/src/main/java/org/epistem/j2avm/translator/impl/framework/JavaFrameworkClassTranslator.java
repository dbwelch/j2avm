package org.epistem.j2avm.translator.impl.framework;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.translator.impl.java.JavaClassTranslator;
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
public class JavaFrameworkClassTranslator extends JavaClassTranslator {

    public JavaFrameworkClassTranslator( TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass, makeName( jvmClass.name ) );
    }
    
    //strip off the prefix
    private static AVM2QName makeName( ObjectType type ) {
        String name = type.name;
        if( name.startsWith( "j2avm." ) ) name = name.substring( 6 );
        return new AVM2QName( name );
    }
    
    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#getSuperclass() */
    @Override
    public ClassTranslator getSuperclass() {
        //make sure that Flash Object is superclass of Java Object
        if( jvmClass.name.name.equals( "j2avm.java.lang.Object" ) ) {
            return manager.translatorForClass( new ObjectType( FlashObject.class.getName() ));
        }
        
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

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateImplementation(Code))
     */
    public void translateImplementation( Code code ) {
        super.translateImplementation( code );
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateInstanceOf(MethodTranslator, InstanceOf)
     */
    public void translateInstanceOf( MethodTranslator method, InstanceOf instOfInsn ) {
        super.translateInstanceOf( method, instOfInsn );
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateInstantiation(MethodTranslator, New)
     */
    public void translateInstantiation( MethodTranslator method, New newInsn ) {
        super.translateInstantiation( method, newInsn ); 
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateStaticPush(MethodTranslator)
     */
    public void translateStaticPush( MethodTranslator method ) {
        super.translateStaticPush( method );
    }
}
