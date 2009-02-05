package org.epistem.j2avm.translator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.epistem.j2avm.annotations.runtime.DefaultTranslator;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.translator.helpers.VanillaHelper;
import org.epistem.jvm.*;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;

/**
 * Caches ClassTranslators 
 *
 * @author nickmain
 */
public class TranslatorManager {

    /**
     * The loader being used
     */
    public final JVMClassLoader loader;
    
    private final Map<ObjectType, ClassTranslator> classes = new HashMap<ObjectType, ClassTranslator>();
    
    private final TranslationHelper vanillaHelper = new VanillaHelper();
    private final Map<ObjectType, TranslationHelper> helperCache = new HashMap<ObjectType, TranslationHelper>();
    
    /**
     * @param loader the loader to use to find Java classes
     */
    public TranslatorManager( JVMClassLoader loader ) {
        this.loader = loader;
    }
    
    /**
     * Get the translator for a Java class
     * @param name the fully qualified Java class name
     * 
     * @throws ClassNotFoundException if the class cannot be found
     * @throws IOException if there is a problem parsing the class
     */
    public ClassTranslator getClassTranslator( ObjectType type ) 
        throws ClassNotFoundException, IOException {
        
        ClassTranslator trans = classes.get( type );
        
        if( trans == null ) {
            JVMClass jvmClass = loader.getClass( type );
            trans = new ClassTranslator( this, jvmClass );
            classes.put( type, trans );
        }        
        
        return trans;
    }
    
    /**
     * Get the translator for the given method
     * 
     * @param owner the target class
     * @param signature the method signature
     * @return not null
     */
    /*pkg*/ MethodTranslator translatorForMethodXX( ObjectType owner, Signature signature ) {
        ClassTranslator classTranslator = 
        
        try {
            JavaAnnotation anno = findTranslatorAnnotation( method.attributes );
            
            if( anno == null ) return ct.helper;
                
            ObjectType helperType = (ObjectType) anno.classValue( "value" );
            TranslationHelper helper = helperCache.get( helperType );
            if( helper == null ) {
                helper = (TranslationHelper) Class.forName( helperType.name ).newInstance();
                helperCache.put( helperType, helper );                
            }
            
            return helper;
            
        } catch( IllegalAccessException e ) {
            throw new RuntimeException( e );            
        } catch( ClassNotFoundException e ) {
            throw new RuntimeException( e );            
        } catch( InstantiationException e ) {
            throw new RuntimeException( e );
        }        
    }

    /**
     * Get the translation helper for the given field
     * 
     * @param field the field
     * @return not null
     */
    /*pkg*/ TranslationHelper helperForField( ObjectType owner, String name ) {
        try {
            JavaAnnotation anno = findTranslatorAnnotation( field.attributes );
            
            if( anno == null ) return ct.helper;
                
            ObjectType helperType = (ObjectType) anno.classValue( "value" );
            TranslationHelper helper = helperCache.get( helperType );
            if( helper == null ) {
                helper = (TranslationHelper) Class.forName( helperType.name ).newInstance();
                helperCache.put( helperType, helper );                
            }
            
            return helper;
            
        } catch( IllegalAccessException e ) {
            throw new RuntimeException( e );            
        } catch( ClassNotFoundException e ) {
            throw new RuntimeException( e );            
        } catch( InstantiationException e ) {
            throw new RuntimeException( e );
        }                
    }

    /**
     * Get the translation helper for the given class
     * 
     * @param jvmClass the class
     * @return not null
     * @throws TranslationHelper.NotFoundException if a helper cannot be found
     */
    /*pkg*/ TranslationHelper helperForClass( JVMClass jvmClass ) {
        try {
            JavaAnnotation anno = findTranslatorAnnotation( jvmClass.attributes );
            if( anno == null ) {
                String name = jvmClass.name.name;
         
                //framework classes require an explicit annotation
                if( name.startsWith( "java." )
                 || name.startsWith( "javax." )) {
                    throw new TranslationHelper.NotFoundException(
                        "Explicit Translator annotation required - cannot translate " + name );
                }
                
                //--assume a vanilla Java class
                return vanillaHelper;
            }
            
            ObjectType helperType = (ObjectType) anno.classValue( "value" );
            TranslationHelper helper = helperCache.get( helperType );
            if( helper == null ) {
                helper = (TranslationHelper) Class.forName( helperType.name ).newInstance();
                helperCache.put( helperType, helper );                
            }
            
            return helper;
            
        } catch( IllegalAccessException e ) {
            throw new RuntimeException( e );            
        } catch( ClassNotFoundException e ) {
            throw new RuntimeException( e );            
        } catch( InstantiationException e ) {
            throw new RuntimeException( e );
        }
    }

}
