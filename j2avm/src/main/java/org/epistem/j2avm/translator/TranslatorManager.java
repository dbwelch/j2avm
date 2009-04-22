package org.epistem.j2avm.translator;

import java.io.IOException;
import java.util.*;

import org.epistem.j2avm.annotations.runtime.DefaultTranslator;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2avm.translator.impl.java.JavaTranslator;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.jvm.AttributeContainer;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.type.ObjectType;

import com.anotherbigidea.flash.avm2.model.AVM2QName;
import com.anotherbigidea.flash.avm2.model.AVM2Script;

/**
 * Manages the translation of a set of Java classes to a target SWF.
 *
 * @author nickmain
 */
public class TranslatorManager implements Comparator<AVM2Script> {

    /**
     * The loader being used
     */
    public final JVMClassLoader loader;
    
    private final Map<ObjectType, ClassTranslator> translators = new HashMap<ObjectType, ClassTranslator>();
    
    private final LinkedList<ClassTranslator> translationQueue  = new LinkedList<ClassTranslator>();
    private final Collection<ClassTranslator> requiredClasses   = new HashSet<ClassTranslator>();    
    private final Set<ClassTranslator> translated = new HashSet<ClassTranslator>();
    
    private ClassTranslator mainClass;
    
    /**
     * @param loader the loader to use to find Java classes
     */
    public TranslatorManager( JVMClassLoader loader ) {
        this.loader = loader;
    }
    
    /**
     * Set the main class.
     */
    public void setMainClass( ClassTranslator mainClass ) {
        this.mainClass = mainClass;
        requireClass( mainClass );
    }
    
    /**
     * Translate all required classes
     * 
     * @param code the target avm2 code
     */
    public void translateRequiredClasses( Code code ) {
        while( ! translationQueue.isEmpty() ) {
            ClassTranslator trans = translationQueue.removeFirst();
            if( translated.contains( trans )) continue;
            
            System.out.println( trans.getJVMType() );
            System.out.flush();
         
            trans.translateImplementation( code );
            translated.add( trans );
        }
    }
    
    /**
     * Get the main class
     */
    public ClassTranslator getMainClass() {
        return mainClass;
    }
    
    /**
     * Require that the given class dependency also be translated
     */
    public void requireClass( ObjectType type ) {
        if( type.equals( ObjectType.STRING ) ) return;
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
            
            //also require implemented interfaces
            for( ClassTranslator ifTrans : clazz.getInterfaces() ) {
                requireClass( ifTrans );
            }
            
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

        if( type.name.startsWith( "j2avm." ) ) {
            type = new ObjectType( type.name.substring( 6 ) );
        }
        
        ClassTranslator trans = translators.get( type );
        if( trans != null ) return trans;
        
        try {
            JVMClass jvmClass = null;
            
            try { //look for a framework class implementation
                ObjectType fwType = new ObjectType( "j2avm." + type.name );
                jvmClass = loader.getClass( fwType ); 
                
            } catch( ClassNotFoundException cnfe ) {
                jvmClass = loader.getClass( type );                
            }
                
            JavaAnnotation anno = findTranslatorAnnotation( loader, jvmClass.attributes );
            if( anno == null ) {
                String name = jvmClass.name.name;
         
                //java framework classes
                if( name.startsWith( "java." ) || name.startsWith( "javax." )) {                    
                    throw new RuntimeException( "Framework classes need an explicit translator: " + name );
                }
                else {
                    //--assume a vanilla Java class
                    trans = JavaTranslator.forClass( jvmClass, this );
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
            
        } catch( RuntimeException e ) {
            throw e;
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

    /** 
     * Order scripts according to the classes that they initialize, such that
     * superclasses come before base classes. 
     *  
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare( AVM2Script a, AVM2Script b ) {
        AVM2QName classA = a.findClassSlot();
        AVM2QName classB = b.findClassSlot();
        
        if( classA == null && classB == null ) return 0;
        if( classA == null ) return 1;  //non-class scripts come later
        if( classB == null ) return -1;
                
        ClassTranslator ctA = translatorForClass( new ObjectType( classA.toQualString() ) );
        ClassTranslator ctB = translatorForClass( new ObjectType( classB.toQualString() ) );
        
        //main class always last
        if( ctA == mainClass ) return 1;
        if( ctB == mainClass ) return -1;
        
        if( ClassTranslatorBase.isSuperclassOf( ctA, ctB ) ) return 1; 
        if( ClassTranslatorBase.isSuperclassOf( ctB, ctA ) ) return -1; 
        
        if( ClassTranslatorBase.hasInterface( ctA, ctB ) ) return 1;
        if( ClassTranslatorBase.hasInterface( ctB, ctA ) ) return -1;
        
        return 0;
    }
}
