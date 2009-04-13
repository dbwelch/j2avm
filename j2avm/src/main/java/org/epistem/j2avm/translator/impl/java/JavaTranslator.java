package org.epistem.j2avm.translator.impl.java;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.InstanceOf;
import org.epistem.jvm.flags.ClassFlag;

import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Base for Java class and interface translators
 *
 * @author nickmain
 */
public abstract class JavaTranslator extends ClassTranslatorBase {

    /**
     * Make a translator appropriate for the given class
     */
    public static JavaTranslator forClass( JVMClass clazz, TranslatorManager manager ) {
        if( clazz.flags.contains( ClassFlag.IsInterface ) ) {
            return new JavaInterfaceTranslator( manager, clazz );
        }
        
        return new JavaClassTranslator( manager, clazz );
    }
    
    protected CodeClass codeClass;
    
    protected JavaTranslator( TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass );        
    }
    
    protected JavaTranslator( TranslatorManager manager, JVMClass jvmClass, AVM2QName avm2name ) {
        super( manager, jvmClass, avm2name, null, null, null );
    }
    
    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultFieldTranslator(org.epistem.jvm.JVMField) */
    @Override
    public FieldTranslator defaultFieldTranslator( JVMField field ) {
        return new JavaFieldTranslator( this, field );
    }

    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultMethodTranslator(org.epistem.jvm.JVMMethod) */
    @Override
    public MethodTranslator defaultMethodTranslator( JVMMethod method ) {
        if( method.name.equals( "<clinit>" ) ) {
            return new JavaStaticInitMethodTranslator( this, method );
        }
        
        return new JavaMethodTranslator( this, method );
    }
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#translateInstanceOf(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.InstanceOf) */
    public void translateInstanceOf( MethodTranslator method, InstanceOf instOfInsn ) {
        method.getCode().code().isType( avm2name );
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#translateStaticPush(org.epistem.j2avm.translator.MethodTranslator) */
    public void translateStaticPush( MethodTranslator method ) {
        // TODO this would be the place to implement ClassLoader support ?
        
        method.getCode().code().getLex( avm2name );
    }
    
    /**
     * Add the implemented interfaces
     */
    protected void addImplementedInterfaces() {
        for( ClassTranslator trans : getInterfaces() ) {
            codeClass.avm2class.addInterface( trans.getAVM2Name() );            
        }
    }
    
    /** 
     * Translate the members - fields then methods (order assumption is important)
     */
    protected void translateMembers() {
        for( FieldTranslator field : fields.values() ) {
            field.translateImplementation( codeClass );
        }
        for( MethodTranslator method : methods.values() ) {            
            method.translateImplementation( codeClass );
        }
    }
}
