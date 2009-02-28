package org.epistem.j2avm.translator.impl;

import java.util.HashMap;
import java.util.Map;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.type.Signature;

import com.anotherbigidea.flash.avm2.NamespaceKind;
import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * A translator for a class. A bridge between the Java class and the AVM2 class.
 *
 * @author nickmain
 */
public abstract class ClassTranslatorBase implements ClassTranslator {

    private final String name;
    private final TranslatorManager manager;
    private final JVMClass jvmClass;
    private final AVM2QName avm2name;
    
    private final AVM2Namespace privateNamespace;
    private final AVM2Namespace internalNamespace;
    private final AVM2Namespace protectedNamespace;
    
    private final Map<String, FieldTranslatorBase> fields = new HashMap<String, FieldTranslatorBase>();
    private final Map<Signature, MethodTranslator> methods = new HashMap<Signature, MethodTranslator>();
        
    /**
     * @param manager the translation manager
     * @param jvmClass the class to translate
     */
    public ClassTranslatorBase( TranslatorManager manager, JVMClass jvmClass ) {
        
        this.name     = jvmClass.name.name;
        this.manager  = manager;
        this.jvmClass = jvmClass;
        
        avm2name = NameUtils.nameForJavaClass( jvmClass.name );
        
        privateNamespace   = new AVM2Namespace( NamespaceKind.PrivateNamespace, name );
        internalNamespace  = new AVM2Namespace( NamespaceKind.PackageInternalNamespace, 
                                                jvmClass.name.packageName );
        protectedNamespace = new AVM2Namespace( NamespaceKind.ProtectedNamespace, 
                                                jvmClass.name.packageName + ":" 
                                                    + jvmClass.name.simpleName );  
    }
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#getAVM2InternalNamespace() */
    public AVM2Namespace getAVM2InternalNamespace() {
        return internalNamespace;
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getAVM2Name() */
    public AVM2QName getAVM2Name() {
        return avm2name;
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getAVM2PrivateNamespace() */
    public AVM2Namespace getAVM2PrivateNamespace() {
        return privateNamespace;
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getAVM2ProtectedNamespace() */
    public AVM2Namespace getAVM2ProtectedNamespace() {
        return protectedNamespace;
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getTranslatorForMethod(org.epistem.jvm.type.Signature) */
    public MethodTranslator getTranslatorForMethod( Signature sig ) throws NoSuchMethodException {
        MethodTranslator mt = methods.get( sig );
        if( mt != null ) return mt;
        
        ClassTranslator superclass = getSuperclass();
        if( superclass == null ) throw new NoSuchMethodException( jvmClass.name + "::" + sig.toString() );
        
        return superclass.getTranslatorForMethod( sig );
    }
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#getFieldTranslator(java.lang.String) */
    public FieldTranslator getFieldTranslator( String name ) throws NoSuchFieldException {
        FieldTranslatorBase ft = fields.get( name );
        if( ft != null ) return ft;

        ClassTranslator superclass = getSuperclass();
        if( superclass == null ) throw new NoSuchFieldException( jvmClass.name + "::" + name );
        
        return superclass.getFieldTranslator( name );
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getManager() */
    public TranslatorManager getManager() {
        return manager;
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getSuperclass() */
    public ClassTranslator getSuperclass() {
        if( jvmClass.superclassName == null ) return null;        
        return manager.translatorForClass( jvmClass.superclassName );
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getAnnotation(java.lang.String) */
    public JavaAnnotation getAnnotation( String name ) {
        try {
            return jvmClass.attributes.annotation( name );
        } catch( Exception ex ) {
            J2AVM.log.severe( "While looking for annotation " + name + ": " + ex.getMessage() );
            return null;
        }
    }
}
