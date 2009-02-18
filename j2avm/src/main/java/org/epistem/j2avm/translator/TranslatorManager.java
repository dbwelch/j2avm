package org.epistem.j2avm.translator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.epistem.j2avm.translator.helpers.VanillaHelper;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
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
     * Get the translation helper for the given method
     * 
     * @param owner the target class
     * @param signature the method signature
     * @return not null
     */
    /*pkg*/ TranslationHelper translatorForMethod( ObjectType owner, Signature signature ) {
        try {
            JVMClass  clazz  = loader.getClass( owner );
            JVMMethod method = clazz.getMethod( signature );
            if( method == null ) throw new RuntimeException( "Method " + owner + "::" + signature + " not found" );
            
            JavaAnnotation anno = TranslationHelper.findTranslatorAnnotation( loader, method.attributes );
            
            if( anno == null ) {
                ClassTranslator ct = getClassTranslator( owner );
                return ct.helper;
            }
                
            ObjectType helperType = (ObjectType) anno.classValue( "value" );
            return (TranslationHelper) Class.forName( helperType.name ).newInstance();
            
        } catch( IllegalAccessException e ) {
            throw new RuntimeException( e );            
        } catch( ClassNotFoundException e ) {
            throw new RuntimeException( e );            
        } catch( InstantiationException e ) {
            throw new RuntimeException( e );
        } catch( IOException e ) {
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
            JVMClass clazz = loader.getClass( owner );
            JVMField field = clazz.getField( name );
            JavaAnnotation anno = TranslationHelper.findTranslatorAnnotation( loader, field.attributes );
            
            if( anno == null ) {
                ClassTranslator ct = getClassTranslator( owner );
                return ct.helper;
            }
                
            ObjectType helperType = (ObjectType) anno.classValue( "value" );
            return (TranslationHelper) Class.forName( helperType.name ).newInstance();
            
        } catch( IllegalAccessException e ) {
            throw new RuntimeException( e );            
        } catch( ClassNotFoundException e ) {
            throw new RuntimeException( e );            
        } catch( InstantiationException e ) {
            throw new RuntimeException( e );
        } catch( IOException e ) {
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
            JavaAnnotation anno = TranslationHelper.findTranslatorAnnotation( loader, jvmClass.attributes );
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
            return (TranslationHelper) Class.forName( helperType.name ).newInstance();
            
        } catch( IllegalAccessException e ) {
            throw new RuntimeException( e );            
        } catch( ClassNotFoundException e ) {
            throw new RuntimeException( e );            
        } catch( InstantiationException e ) {
            throw new RuntimeException( e );
        }
    }

}
