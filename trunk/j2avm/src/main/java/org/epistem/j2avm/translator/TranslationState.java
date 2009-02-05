package org.epistem.j2avm.translator;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.epistem.j2avm.translator.transformers.StaticCallHandler;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.j2swf.swf.code.CodeMethod;
import org.epistem.jvm.code.InstructionVisitor;

/**
 * The state required by a translation pass.
 *
 * @author nickmain
 */
public class TranslationState {

    /**
     * The translator manager
     */
    public final TranslatorManager manager;
    
    /**
     * The SWF code to add classes to
     */
    public final Code code;    

    /**
     * The class currently being translated
     */
    public ClassTranslator classTranslator;
    
    /**
     * The class currently being translated
     */
    public CodeClass codeClass;

    /**
     * The method currently being translated
     */
    public MethodTranslator methodTranslator;
    
    /**
     * The method currently being translated
     */
    public CodeMethod codeMethod;
    
    /**
     * An instruction visitor that will rewrite the JVM instructions to make
     * them suitable for translation. May be null.
     */
    public InstructionVisitor transformer;
    
    private final Set<ClassTranslator> requiredClasses = new HashSet<ClassTranslator>();
    
    private final Set<String> alreadyTranslated;
    
    /**
     * @param manager the translator manager
     * @param code the target code block
     * @param alreadyTranslated a set of classes already translated
     */
    public TranslationState( TranslatorManager manager, Code code, Set<String> alreadyTranslated ) {
        this.code    = code;
        this.manager = manager;
        this.alreadyTranslated = alreadyTranslated;
        
        //set up the transformers - TODO: this should be refactored into some
        // sort of factory-driven process
        //transformer = new StaticCallHandler();
    }
    
    /**
     * Require that the given class dependency also be translated
     */
    public void requireClass( ClassTranslator clazz ) 
        throws ClassNotFoundException, IOException {        
        
        while( clazz != null ) {
            requiredClasses.add( clazz );
            clazz = clazz.superclass();
        }
    }
    
    /**
     * Translate all the dependencies
     */
    public void translateDependencies() throws ClassNotFoundException, IOException {
        while( ! dependencies.isEmpty() ) {        
            Set<String> batch = new HashSet<String>( dependencies ); //avoid concurrent mod exception
            for( String className : batch ) {
                dependencies.remove( className );                
                if( alreadyTranslated.contains( className ) ) continue;
                alreadyTranslated.add( className );
                
                manager.getClassTranslation( className ).translate( this );
            }
        }
    }
}
