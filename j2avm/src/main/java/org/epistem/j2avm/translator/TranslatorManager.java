package org.epistem.j2avm.translator;

import java.io.IOException;
import java.util.*;

import org.epistem.j2avm.annotations.runtime.DefaultTranslator;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassTranslator;
import org.epistem.j2avm.translator.impl.java.JavaClassTranslator;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.jvm.AttributeContainer;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.type.ObjectType;

/**
 * Manages the translation of a set of Java classes to a target SWF.
 *
 * @author nickmain
 */
public class TranslatorManager {

    /**
     * The loader being used
     */
    public final JVMClassLoader loader;
    
    private final Map<ObjectType, ClassTranslator> translators = new HashMap<ObjectType, ClassTranslator>();
    
    private final LinkedList<ClassTranslator> translationQueue  = new LinkedList<ClassTranslator>();
    private final Collection<ClassTranslator> requiredClasses   = new HashSet<ClassTranslator>();    
    
    /**
     * @param loader the loader to use to find Java classes
     */
    public TranslatorManager( JVMClassLoader loader ) {
        this.loader = loader;
    }
    
    /**
     * Translate all required classes
     * 
     * @param code the target avm2 code
     */
    public void translateRequiredClasses( Code code ) {
        while( ! translationQueue.isEmpty() ) {
            ClassTranslator trans = translationQueue.removeFirst();
            
            System.out.println( trans.getJVMType() );
            System.out.flush();
            trans.translateImplementation( code );
        }
    }
    
    /**
     * Require that the given class dependency also be translated
     */
    public void requireClass( ObjectType type ) {
        requireClass( translatorForClass( type ));
    }
    
    /**
     * Require that the given class dependency also be translated
     */
    public void requireClass( ClassTranslator clazz ) {                
        while( clazz != null ) {
            if( requiredClasses.contains( clazz ) ) return;
            
            translationQueue.add( clazz );
            requiredClasses.add( clazz );
            clazz = clazz.getSuperclass();
        }
    }
    
    /**
     * Get the translator for the given class
     * 
     * @param type the class
     * @return not null
     */    
    public ClassTranslator translatorForClass( ObjectType type ) {
        
        ClassTranslator trans = translators.get( type );
        if( trans != null ) return trans;
        
        try {
            JVMClass jvmClass = loader.getClass( type );
            JavaAnnotation anno = findTranslatorAnnotation( loader, jvmClass.attributes );
            if( anno == null ) {
                String name = jvmClass.name.name;
         
                //java framework classes
                if( name.startsWith( "java." ) || name.startsWith( "javax." )) {                    
                    trans = new JavaFrameworkClassTranslator( this, jvmClass );
                }
                else {
                    //--assume a vanilla Java class
                    trans = new JavaClassTranslator( this, jvmClass );
                }
            }
            else {
            
                ObjectType helperType = (ObjectType) anno.classValue( "value" );
                
                @SuppressWarnings("unchecked")
                Class<? extends ClassTranslator> ctClass = 
                    (Class<? extends ClassTranslator>) Class.forName( helperType.name );
                
                trans = (ClassTranslator) 
                    ctClass.getConstructor( TranslatorManager.class, JVMClass.class )
                           .newInstance( this, jvmClass );
            }

            translators.put( type, trans );
            return trans;
            
        } catch( Exception e ) {
            throw new RuntimeException( e );            
        }
    }

    /**
     * Find a Translator or DefaultTranslator annotation
     * 
     * @param loader the load to use for resolving any required types
     * @param attrs the attribute container to search
     * @return null if not found
     */
    public static JavaAnnotation findTranslatorAnnotation( JVMClassLoader loader, AttributeContainer attrs ) {
        try {
            JavaAnnotation anno = attrs.annotation( Translator.class.getName());
            if( anno != null ) return anno;

            anno = attrs.annotation( DefaultTranslator.class.getName());
            if( anno != null ) return anno;
            
            //look for meta annotation
            for( JavaAnnotation ann : attrs.annotations() ) {
                JVMClass annoClass = loader.getClass( ann.type );
                if( annoClass.name.name.startsWith( "java" )) continue; //avoid loops with the @Retention annotation
                anno = findTranslatorAnnotation( loader, annoClass.attributes );
                if( anno != null ) return anno;
            }
            
            return null;
            
        } catch( ClassNotFoundException e ) {
            throw new RuntimeException( e );            
        } catch( IOException e ) {
            throw new RuntimeException( e );
        }
    }
}
