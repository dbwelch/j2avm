package org.epistem.j2avm.translator;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.epistem.j2avm.TranslationManager;
import org.epistem.j2avm.annotations.runtime.FlashNativeClass;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMClass;

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
    
    private final JVMClass jvmClass;
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
        FlashNativeClass fnc = getAnnotation( FlashNativeClass.class );
        if( fnc != null ) {
            hasBeenTranslated = true;
            return;
        }
    }
    
    /**
     * Get the specified Java annotation
     * @return null if the annotation does not exist
     */
    public <A extends Annotation> A getAnnotation( Class<A> annotationClass ) {
        return javaClass.getAnnotation( annotationClass );
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
    public void translate( Code code ) {
        if( hasBeenTranslated ) return;
        
        //TODO: handle interfaces
        
        //get the superclasses
        LinkedList<String> superNames = new LinkedList<String>();
        Class<?> superClass = javaClass.getSuperclass();
        while( superClass != null ) {
            
            //stop when Flash root Object class is reached
            if( superClass == FlashObject.class ) {
                superNames.addFirst( "Object" );
                break;
            }
            
            superNames.addFirst( superClass.getName() );
            superClass = superClass.getSuperclass();
        }
        String[] superclasses = superNames.toArray( new String[ superNames.size() ] );
        
        boolean isFinal = Modifier.isFinal( javaClass.getModifiers());
        boolean isIFace = javaClass.isInterface();
        
        swfClass = code.addClass( name, true, isFinal, isIFace, superclasses );
        
        //TODO: implemented interfaces
        //TODO: static fields        
        //TODO: instance fields
        
        //-- method, constructors and static initializer
        @SuppressWarnings( "unchecked" )
        List<MethodNode> methods = (List<MethodNode>) classNode.methods;
        for( MethodNode method : methods ) {
            
            
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
    
    /**
     * Dump a textual representation of the Java class
     */
    public void dumpJavaClass( PrintWriter out ) {
        if( classNode == null ) {
            out.println( "Non-translating class " + name );
        }
        else {
            TraceClassVisitor tracer = new TraceClassVisitor( out );
            classNode.accept( tracer );
        }
        
        out.flush();
    }
}
