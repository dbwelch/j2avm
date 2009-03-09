package org.epistem.j2avm.translator.impl.flash;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.InstanceOf;
import org.epistem.jvm.code.instructions.New;

import com.anotherbigidea.flash.avm2.model.AVM2Code;
import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

import flash.FlashObject;

/**
 * Translator for built-in flash classes
 *
 * @author nickmain
 */
public class FlashNativeClassTranslator extends ClassTranslatorBase {

    public FlashNativeClassTranslator( TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass, avm2name( jvmClass ), null, null, null );
    }
    
    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultFieldTranslator(org.epistem.jvm.JVMField) */
    @Override
    public FieldTranslator defaultFieldTranslator( JVMField field ) {
        return new FlashNativeFieldTranslator( this, field );
    }

    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultMethodTranslator(org.epistem.jvm.JVMMethod) */
    @Override
    public MethodTranslator defaultMethodTranslator( JVMMethod method ) {
        return new FlashNativeMethodTranslator( this, method );
    }
    
    /**
     * Get the flash name of the JVM class
     */
    private static AVM2QName avm2name( JVMClass jvmClass ) {
        String pkg  = jvmClass.name.packageName;
        String name = jvmClass.name.simpleName;
        
        if( pkg.equals( "flash" ) && name.startsWith( "Flash" ) ) {
            return new AVM2QName( AVM2Namespace.publicNamespace, name.substring( 5 ) );
        }
        
        return NameUtils.nameForJavaClass( jvmClass.name );
    }
    
    
    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#getSuperclass() */
    @Override
    public ClassTranslator getSuperclass() {
        //make sure that Flash Object is root of hierarchy
        if( jvmClass.name.name.equals( FlashObject.class.getName() ) ) return null;
        
        return super.getSuperclass();
    }
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#translateImplementation(org.epistem.j2swf.swf.code.Code) */
    public void translateImplementation( Code code ) {
        // do nothing - this is a native class        
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#translateInstantiation(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.New) */
    public void translateInstantiation( MethodTranslator method, New newInsn ) {
        AVM2Code code = method.getCode().code();
        
        code.findPropStrict( avm2name );
        code.constructProp( avm2name, 0 );
        
        //This assumes that the subsequent call to <init> will be dropped and
        //that the no-arg constructor is being called
    }
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#translateInstanceOf(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.InstanceOf) */
    public void translateInstanceOf( MethodTranslator method, InstanceOf instOfInsn ) {
        method.getCode().code().isType( avm2name );
    }
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#translateStaticPush(org.epistem.j2avm.translator.MethodTranslator) */
    public void translateStaticPush( MethodTranslator method ) {
        method.getCode().code().getLex( avm2name );
    }
}
