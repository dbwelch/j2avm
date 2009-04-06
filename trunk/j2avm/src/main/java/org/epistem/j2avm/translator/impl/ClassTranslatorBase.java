package org.epistem.j2avm.translator.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;

import com.anotherbigidea.flash.avm2.NamespaceKind;
import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

import flash.FlashObject;

/**
 * A translator for a class. A bridge between the Java class and the AVM2 class.
 *
 * @author nickmain
 */
public abstract class ClassTranslatorBase implements ClassTranslator {

    protected final String name;
    protected final TranslatorManager manager;
    protected final AVM2QName avm2name;
    
    protected final AVM2Namespace privateNamespace;
    protected final AVM2Namespace internalNamespace;
    protected final AVM2Namespace protectedNamespace;
    
    protected final JVMClass jvmClass;
    protected final Map<String, FieldTranslator> fields = new HashMap<String, FieldTranslator>();
    protected final Map<Signature, MethodTranslator> methods = new HashMap<Signature, MethodTranslator>();
        
    /**
     * @param manager the translation manager
     * @param jvmClass the class to translate
     */
    protected ClassTranslatorBase( TranslatorManager manager, JVMClass jvmClass ) {
        this( manager, jvmClass, null, null, null, null );        
    }
    
    /**
     * Make the default type of method translator
     */
    public abstract MethodTranslator defaultMethodTranslator( JVMMethod method ); 

    /**
     * Make the default type of field translator
     */
    public abstract FieldTranslator defaultFieldTranslator( JVMField field ); 
    
    /**
     * @param manager the translation manager
     * @param jvmClass the class to translate
     */
    protected ClassTranslatorBase( TranslatorManager manager, JVMClass jvmClass,
                                   AVM2QName avm2name, 
                                   AVM2Namespace privateNamespace,
                                   AVM2Namespace internalNamespace,
                                   AVM2Namespace protectedNamespace ) {
        
        this.name     = jvmClass.name.name;
        this.manager  = manager;
        this.jvmClass = jvmClass;
        
        if( avm2name == null ) avm2name = NameUtils.nameForJavaClass( jvmClass.name );
        this.avm2name = avm2name;
        
        this.privateNamespace = (privateNamespace != null) ?
            privateNamespace : 
            new AVM2Namespace( NamespaceKind.PrivateNamespace, avm2name.toQualString() );

        this.internalNamespace = (internalNamespace != null) ?
            internalNamespace :
            new AVM2Namespace( NamespaceKind.PackageInternalNamespace, avm2name.namespace.name );
        
        this.protectedNamespace = (protectedNamespace != null) ? 
            protectedNamespace :
            new AVM2Namespace( NamespaceKind.ProtectedNamespace, 
                               avm2name.namespace.name + ":" + avm2name.name );
        
        addAllMemberTranslators();
    }
    
    /**
     * Add all the class member translators
     */
    protected void addAllMemberTranslators() {
        //-- fields 
        for( JVMField field : jvmClass.fields ) {
            FieldTranslator fieldTrans = FieldTranslatorBase.forField( field, this );
            if( fieldTrans != null ) fields.put( field.name, fieldTrans );
        }
        
        //-- method, constructors and static initializer
        for( JVMMethod method : jvmClass.methods ) {            
            MethodTranslator methodTrans = MethodTranslatorBase.forMethod( method, this );
            if( methodTrans != null ) methods.put( method.signature, methodTrans );               
        }
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

    /** @see org.epistem.j2avm.translator.ClassTranslator#getMethodTranslator(ClassTranslator, Signature)*/
    public MethodTranslator getMethodTranslator( ClassTranslator owner, Signature sig ) throws NoSuchMethodException {
        MethodTranslator mt = methods.get( sig );
        if( mt != null ) return mt;
        
        ClassTranslator superclass = getSuperclass();
        if( superclass == null ) throw new NoSuchMethodException( owner.getAVM2Name().toQualString() + "::" + sig.toString() );
        
        return superclass.getMethodTranslator( owner, sig );
    }
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#getFieldTranslator(ClassTranslator, String) */
    public FieldTranslator getFieldTranslator( ClassTranslator owner, String name ) throws NoSuchFieldException {
        FieldTranslator ft = fields.get( name );
        if( ft != null ) return ft;

        ClassTranslator superclass = getSuperclass();
        if( superclass == null ) throw new NoSuchFieldException( owner.getAVM2Name().toQualString() + "::" + name );
        
        return superclass.getFieldTranslator( owner, name );
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getManager() */
    public TranslatorManager getManager() {
        return manager;
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getSuperclass() */
    public ClassTranslator getSuperclass() {
        if( jvmClass.superclassName == null ) return null; 
        
        if( jvmClass.superclassName.equals( ObjectType.OBJECT ) ) {
            return manager.translatorForClass( new ObjectType( FlashObject.class.getName()));
        }
        
        return manager.translatorForClass( jvmClass.superclassName );
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#getInterfaces() */
    public Collection<ClassTranslator> getInterfaces() {
        Collection<ClassTranslator> ifaces = new ArrayList<ClassTranslator>();
            
        for( ObjectType ifType : jvmClass.interfaces ) {
            ifaces.add( manager.translatorForClass( ifType ) );
        }
        
        return ifaces;
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

    /** @see org.epistem.j2avm.translator.ClassTranslator#getJVMType() */
    public ObjectType getJVMType() {
        return jvmClass.name;
    }
    
    /**
     * Determine whether one class is a superclass of another
     * 
     * @param subclass the sub-class
     * @param superClass the potential superclass
     * @return true if the second arg is a superclass of the first
     */
    public static boolean isSuperclassOf( ClassTranslator subclass, ClassTranslator superClass ) {
        for( ClassTranslator superTran = subclass.getSuperclass(); 
             superTran != null; 
             superTran = superTran.getSuperclass() ) {
       
           if( superTran == superClass ) {
               return true;
           }
        }
        
        return false;
    }
}
