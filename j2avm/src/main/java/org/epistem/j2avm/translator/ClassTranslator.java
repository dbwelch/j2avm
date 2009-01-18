package org.epistem.j2avm.translator;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.epistem.code.LocalValue;
import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.annotations.runtime.FlashNativeClass;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.flags.ClassFlag;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;

import com.anotherbigidea.flash.avm2.NamespaceKind;
import com.anotherbigidea.flash.avm2.instruction.Instruction;
import com.anotherbigidea.flash.avm2.model.AVM2Class;
import com.anotherbigidea.flash.avm2.model.AVM2Code;
import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

import flash.FlashObject;
import flash.display.MovieClip;

/**
 * A translator for a class. A bridge between the Java class and the AVM2 class.
 *
 * @author nickmain
 */
public class ClassTranslator {

    /**
     * Fully qualified Java class name
     */
    /*pkg*/ final String name;

    /**
     * The manager that loaded this class
     */
    /*pkg*/ final TranslatorManager manager;
    
    /**
     * The JVM class
     */
    /*pkg*/ final JVMClass jvmClass;
    
    /**
     * The avm2 name for the class
     */
    /*pkg*/ final AVM2QName avm2name;
    
    /**
     * Whether is a Flash native class
     */
    /*pkg*/ final boolean isFlashNative;

    /**
     * The helper to use when translating code that references this class
     */
    /*pkg*/ final TranslationHelper helper;
    
    /*pkg*/ AVM2Namespace privateNamespace;
    /*pkg*/ AVM2Namespace packageNamespace;
    /*pkg*/ AVM2Namespace internalNamespace;
    /*pkg*/ AVM2Namespace protectedNamespace;
    
    /*pkg*/ final Map<String, FieldTranslator> fields = new HashMap<String, FieldTranslator>();
    /*pkg*/ final Map<Signature, MethodTranslator> methods = new HashMap<Signature, MethodTranslator>();
    
    private MethodTranslator noArgConstructor;
    
    /**
     * @param manager the translation manager
     * @param jvmClass the class to translate
     */
    public ClassTranslator( TranslatorManager manager, JVMClass jvmClass ) 
        throws IOException, ClassNotFoundException {
        
        this.name     = jvmClass.name.name;
        this.manager  = manager;
        this.jvmClass = jvmClass;
        this.helper   = manager.helperForClass( jvmClass );
        
        avm2name = NameUtils.nameForJavaClass( jvmClass.name );
        
        privateNamespace   = new AVM2Namespace( NamespaceKind.PrivateNamespace, name );
        packageNamespace   = new AVM2Namespace( NamespaceKind.PackageNamespace, 
                                                jvmClass.name.packageName );
        internalNamespace  = new AVM2Namespace( NamespaceKind.PackageInternalNamespace, 
                                                jvmClass.name.packageName );
        protectedNamespace = new AVM2Namespace( NamespaceKind.ProtectedNamespace, 
                                                jvmClass.name.packageName + ":" 
                                                    + jvmClass.name.simpleName );  
        
        //detect Flash native classes 
        JavaAnnotation fnc = getAnnotation( FlashNativeClass.class.getName() );
        isFlashNative = fnc != null;
        
        //TODO: implemented interfaces
        //TODO: Enums
        
        //-- fields 
        for( JVMField field : jvmClass.fields ) {
            FieldTranslator fieldTrans = new FieldTranslator( this, field );
            fields.put( field.name, fieldTrans );
        }
        
        //-- method, constructors and static initializer
        for( JVMMethod method : jvmClass.methods ) {            
            
            //MethodTranslation
            MethodTranslator methodTrans = new MethodTranslator( this, method );
            methods.put( method.signature, methodTrans );
            
            //detect the no arg constructor
            if( method.name.equals( "<init>" ) 
             && method.signature.paramTypes.length == 0 ) {                
                noArgConstructor = methodTrans;
            }                
        }
        
    }
    
    /**
     * Get the specified Java annotation
     * @return null if the annotation does not exist
     */
    public JavaAnnotation getAnnotation( String name ) {
        try {
            return jvmClass.attributes.annotation( name );
        } catch( Exception ex ) {
            J2AVM.log.severe( "While looking for annotation " + name + ": " + ex.getMessage() );
            return null;
        }
    }
    
    /**
     * Find the given method on this class or a superclass
     * 
     * @param sig the method signature
     * @throws NoSuchMethodException if the method cannot be found
     */
    /*pkg*/ MethodTranslator findMethod( Signature sig ) 
        throws NoSuchMethodException, ClassNotFoundException, IOException {
        
        MethodTranslator mt = methods.get( sig );
        if( mt != null ) return mt;
        ClassTranslator superclass = superclass();
        if( superclass == null ) throw new NoSuchMethodException( sig.toString() );
        
        return superclass.findMethod( sig );
    }
    
    /**
     * Find the given field on this class or a superclass
     */
    /*pkg*/ FieldTranslator findField( String name ) 
        throws NoSuchFieldException, ClassNotFoundException, IOException {
        
        FieldTranslator ft = fields.get( name );
        if( ft != null ) return ft;
        ClassTranslator superclass = superclass();
        if( superclass == null ) throw new NoSuchFieldException( name );
        
        return superclass.findField( name );
    }
    
    /**
     * Get the superclass
     */
    /*pkg*/ ClassTranslator superclass() throws ClassNotFoundException, IOException {
        if( jvmClass.superclassName == null ) return null;
        return manager.getClassTranslation( jvmClass.superclassName.name );
    }
    
    /**
     * Cause the class to be translated.
     * 
     * @param state the translation context
     */
    public CodeClass translate( TranslationState state ) 
        throws ClassNotFoundException, IOException {
        
        if( isFlashNative ) return null;
        
        //TODO: handle java classes
        if( jvmClass.name.packageName.startsWith( "java." ) ) {
            J2AVM.log.info( "Skipping unsupported " + name );            
            return null;
        }
        
        state.classTranslator = this;
        J2AVM.log.info( "Translating class " + name );
        
        //TODO: handle interfaces
        
        //get the superclasses
        LinkedList<String> superNames = new LinkedList<String>();
        JVMClass superClass = jvmClass.getSuperclass();
        while( true ) {            
            //stop when Flash root Object class is reached
            //TODO: also stop at java.lang.Object - but rethink this
            if( superClass.name.equals( FlashObject.class.getName() )
             || superClass.name.equals( ObjectType.OBJECT )) {
                superNames.addFirst( "Object" );
                break;
            }
            
            superNames.addFirst( superClass.name.name );
            superClass = superClass.getSuperclass();
            
            //make sure the superclass is also translated
            state.requireClass( superClass.name.name );
        }
        String[] superclasses = superNames.toArray( new String[ superNames.size() ] );
        
        boolean isFinal = jvmClass.flags.contains( ClassFlag.IsFinal );
        boolean isIFace = jvmClass.flags.contains( ClassFlag.IsInterface );
        
        CodeClass codeClass = state.code.addClass( name, true, isFinal, isIFace, superclasses );
        state.codeClass = codeClass;
        
        //TODO: implemented interfaces
        //TODO: Enums
        
        for( FieldTranslator field : fields.values() ) {
            field.translate( state );
        }
        for( MethodTranslator method : methods.values() ) {            
            method.translate( state );
        }

        //-- Constructor
        makeConstructor( state );
        
        //TODO: static initializer
        //TODO: constructor

        
        //TODO: class annotations
        //TODO: field annotations
        //TODO: method annotations
        //TODO: parameter annotations
        
        //TODO: a bunch of things I forgot
        
        return codeClass;
    }
    
    /**
     * Make the constructor. 
     * 
     * The constructor is not the same as the Java "init" method(s). This
     * constructor does nothing and merely satisfies the requirements of the
     * AVM2. The Java init methods are translated as normal methods and called
     * accordingly. The JVM creates a new object (which will execute this
     * constructor) and then calls the init method separately.
     * 
     * This works when translated Java code is doing the instantiation and
     * when this class does not extend any Flash native classes. When any
     * superclass is Flash native then special cases have to be handled.
     * Likewise, when this class may be instantiated by the Flash Player or
     * Flash native code then the init methods will not be called. TODO: need 
     * to figure out a way to address this
     */
    public void makeConstructor( TranslationState state ) {
        AVM2Class avm2class = state.codeClass.avm2class;
        AVM2Code cons = AVM2Code.startNoArgConstructor( avm2class );
        
        if( J2AVM.TRACE_ON ) cons.trace( J2AVM.TRACE_PREFIX + "In Constructor" );
        
        //if this is an extension of MovieClip then also need to call
        //the <init> method - since this class will be instantiated by the
        //Flash player, which will not know to call <init>
        if( jvmClass.superclassName.equals( MovieClip.class.getName() ) ) {
            if( noArgConstructor == null ) {
                throw new RuntimeException( "LIMITATION: Java class that extends MovieClip must have a no-arg constructor" );
            }

            if( J2AVM.TRACE_ON ) cons.trace( J2AVM.TRACE_PREFIX + "Calling <init> from constructor" );

            cons.getLocal( cons.thisValue );
            cons.callPropVoid( noArgConstructor.avm2name, 0 );
        }
        
        cons.returnVoid();
        cons.analyze();
    }
    
    
    private void THIS_IS_ONLY_FOR_DEV_PURPOSES( AVM2Code cons ) {
        
        cons.trace( "J2AVM: In Constructor" );
        cons.getLocal( cons.thisValue );
        cons.getProperty( "graphics" );
        cons.coerceTo( "flash.display.Graphics" );

        LocalValue<Instruction> g = cons.newLocal();
        cons.setLocal( g );

        cons.callPropVoid( g, "beginFill", 0x888800 );
        cons.callPropVoid( g, "lineStyle", 5, 0x000088 );
        cons.callPropVoid( g, "moveTo", 10, 10 );
        cons.callPropVoid( g, "lineTo", 90, 10 );
        cons.callPropVoid( g, "lineTo", 90, 90 );
        cons.callPropVoid( g, "lineTo", 10, 90 );
        cons.callPropVoid( g, "lineTo", 10, 10 );
        cons.callPropVoid( g, "endFill" );

        cons.trace( "J2AVM: At end of Constructor" );
        
        cons.returnVoid();    
        cons.analyze();
    }
}
