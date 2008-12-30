package org.epistem.j2avm.translator;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.TranslationManager;
import org.epistem.j2avm.annotations.runtime.FlashNativeClass;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.flags.ClassFlag;

import flash.FlashObject;

/**
 * A translated class. A bridge between the Java class and the AVM2 class.
 *
 * @author nickmain
 */
public class ClassTranslation {

    /**
     * Fully qualified Java class name
     */
    public final String name;

    /**
     * The manager that loaded this class
     */
    public final TranslationManager manager;
    
    /**
     * The JVM class
     */
    public final JVMClass jvmClass;
    
    private boolean hasBeenTranslated;
    private CodeClass swfClass; 
    
    private MethodTranslation constructor;
    private MethodTranslation staticInitializer;
    private final Map<String, MethodTranslation> methods = new HashMap<String, MethodTranslation>();
    
    /**
     * @param manager the translation manager
     * @param jvmClass the class to translate
     */
    public ClassTranslation( TranslationManager manager, JVMClass jvmClass ) throws IOException {
        this.name     = jvmClass.name.name;
        this.manager  = manager;
        this.jvmClass = jvmClass;
        
        //detect Flash native classes - not to be translated
        JavaAnnotation fnc = getAnnotation( FlashNativeClass.class.getName() );
        if( fnc != null ) {
            hasBeenTranslated = true;
            return;
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
     * Get the AVM2 class
     * 
     * @return may be null if the class has not bee translated or is not
     *         translatable
     */
    public CodeClass getAVM2Class() {
        return swfClass;
    }
    
    /**
     * Cause the class to be translated. All dependent classes that have
     * not already been translated will be.
     * 
     * @param code the code block to translate the class into
     */
    public void translate( Code code ) throws ClassNotFoundException, IOException {
        if( hasBeenTranslated ) return;
        
        J2AVM.log.info( "Translating class " + name );
        
        //TODO: handle interfaces
        
        //get the superclasses
        LinkedList<String> superNames = new LinkedList<String>();
        JVMClass superClass = jvmClass.getSuperclass();
        while( true ) {
            
            //stop when Flash root Object class is reached
            //TODO: also stop at java.lang.Object - but rethink this
            if( superClass.name.equals( FlashObject.class.getName() )
             || superClass.name.equals( Object.class.getName() )) {
                superNames.addFirst( "Object" );
                break;
            }
            
            superNames.addFirst( superClass.name.name );
            superClass = superClass.getSuperclass();
        }
        String[] superclasses = superNames.toArray( new String[ superNames.size() ] );
        
        boolean isFinal = jvmClass.flags.contains( ClassFlag.IsFinal );
        boolean isIFace = jvmClass.flags.contains( ClassFlag.IsInterface );
        
        swfClass = code.addClass( name, true, isFinal, isIFace, superclasses );
        
        //TODO: implemented interfaces
        //TODO: static fields        
        //TODO: instance fields
        
        //-- method, constructors and static initializer
        for( JVMMethod method : jvmClass.methods ) {            
            
            //MethodTranslation
            
            
            
            //TODO: static initializer
            //TODO: instance methods

            //TODO: handle more than one constructor - prolly by making all
            //      constructors static methods and the single constructor
            //      being only the common initializations - and the super
            //      constructor call
        }
        
        //TODO: class annotations
        //TODO: field annotations
        //TODO: method annotations
        //TODO: parameter annotations
        
        //TODO: ---
        
        hasBeenTranslated = true;
    }
}
