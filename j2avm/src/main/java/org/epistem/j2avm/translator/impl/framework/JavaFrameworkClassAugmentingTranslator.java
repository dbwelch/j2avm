package org.epistem.j2avm.translator.impl.framework;

import org.epistem.j2avm.annotations.runtime.Name;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.impl.MemberTranslatorBase;
import org.epistem.j2avm.translator.impl.flash.FlashNativeDummyMethodTranslator;
import org.epistem.j2avm.translator.impl.java.JavaClassTranslator;
import org.epistem.j2avm.translator.impl.java.JavaMethodTranslator;
import org.epistem.j2avm.translator.impl.java.JavaStaticInitMethodTranslator;
import org.epistem.j2avm.translator.impl.java.JavaTranslator;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.code.instructions.InstanceOf;
import org.epistem.jvm.code.instructions.New;

import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Translator for Java framework classes that augments existing Flash
 * classes with extra methods.
 *
 * @author nickmain
 */
public class JavaFrameworkClassAugmentingTranslator extends JavaFrameworkClassTranslator {

    private final AVM2QName targetClass;
    
    public JavaFrameworkClassAugmentingTranslator( TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass );
        targetClass = extractName( jvmClass );
    }
    
    /**
     * Extract the target Flass class
     */
    private AVM2QName extractName( JVMClass jvmClass ) {
        try {
            JavaAnnotation annot = jvmClass.attributes.annotation( Name.class.getName() );
            
            String name;
            if( annot != null ) {
                name = annot.stringValue( "value" );
            }
            else {
                name = jvmClass.name.name;
                if( name.startsWith( "j2avm." ) ) name = name.substring( 6 );
            }
            
            return new AVM2QName( name );
        }
        catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }
    
    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#getSuperclass() */
    @Override
    public ClassTranslator getSuperclass() {
        
        //Object has no superclass
        if( jvmClass.name.name.equals( "j2avm.java.lang.Object" ) ) {
            return null;
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
        if( method.name.equals( JavaClassTranslator.CLINIT_NAME ) ) {
            throw new RuntimeException( "Cannot have a static initializer" );
        }

        if( method.name.equals( JavaClassTranslator.INIT_NAME ) ) {
            return new FlashNativeDummyMethodTranslator( this, method );
        }        
        
        return new JavaFrameworkMethodTranslator( this, method );
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateImplementation(Code))
     */
    public void translateImplementation( Code code ) {       
        
//        codeClass = new CodeClass() {
//            
//        }
//        
//        translateMembers();
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
