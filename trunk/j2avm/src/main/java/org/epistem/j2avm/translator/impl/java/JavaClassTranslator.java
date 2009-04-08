package org.epistem.j2avm.translator.impl.java;

import java.util.LinkedList;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.code.instructions.New;
import org.epistem.jvm.flags.ClassFlag;
import org.epistem.jvm.type.Signature;

import com.anotherbigidea.flash.avm2.model.AVM2Class;
import com.anotherbigidea.flash.avm2.model.AVM2Code;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

import flash.display.MovieClip;

/**
 * Translator for generic Java classes
 *
 * @author nickmain
 */
public class JavaClassTranslator extends JavaTranslator {

    public static final String INIT_NAME = "<init>";
    public static final Signature NO_ARG_CONTRUCTOR = new Signature( INIT_NAME );
    public static final String CLINIT_NAME = "<clinit>";
    public static final Signature STATIC_INIT = new Signature( CLINIT_NAME );
    
    public JavaClassTranslator( TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass );        
    }
    
    protected JavaClassTranslator(  TranslatorManager manager, JVMClass jvmClass, AVM2QName avm2name ) {
        super( manager, jvmClass, avm2name );
    }

    /**
     * Get the CodeClass
     */
    public CodeClass getCodeClass() {
        return codeClass;
    }
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#translateImplementation(org.epistem.j2swf.swf.code.Code) */
    public void translateImplementation( Code code ) {
        J2AVM.log.info( "Translating class " + name );
        
        //get the superclasses
        LinkedList<String> superNames = new LinkedList<String>();
        ClassTranslator superClass = getSuperclass();
        while( superClass != null ) {            
            //make sure the superclass is also translated
            manager.requireClass( superClass );
            
            superNames.addFirst( superClass.getAVM2Name().toQualString() );
            superClass = superClass.getSuperclass();            
        }
        String[] superclasses = superNames.toArray( new String[ superNames.size() ] );
        
        boolean isFinal = jvmClass.flags.contains( ClassFlag.IsFinal );
        
        String initName = methods.containsKey( STATIC_INIT ) ? CLINIT_NAME : null;
        
        codeClass = code.addClass( avm2name.toQualString(), initName, true, isFinal, false, superclasses );

        translateMembers();
        addImplementedInterfaces();
                
        //-- constructor
        makeConstructor();
        
        
        //TODO: class annotations
        //TODO: field annotations
        //TODO: method annotations
        //TODO: parameter annotations
        
        //TODO: a bunch of things I forgot
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#translateInstantiation(MethodTranslator, New) */
    public void translateInstantiation( MethodTranslator method, New newInsn ) {
        AVM2Code code = method.getCode().code();
        
        code.findPropStrict( avm2name );
        code.constructProp( avm2name, 0 );
        
        //The <init> method will be called later in the same manner as
        //normal JVM instantiation
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
    private void makeConstructor() {
        AVM2Class avm2class = codeClass.avm2class;
        AVM2Code cons = AVM2Code.startNoArgConstructor( avm2class );
        
        if( J2AVM.TRACE_ON ) cons.trace( J2AVM.TRACE_PREFIX + "New JVM instance: " + getJVMType() );
        
        //if this is an extension of MovieClip then also need to call
        //the <init> method - since this class will be instantiated by the
        //Flash player, which will not know to call <init>
        if( getSuperclass().getJVMType().equals( MovieClip.class.getName() ) ) {
            
            MethodTranslator noArgInit = methods.get( NO_ARG_CONTRUCTOR );
            if( noArgInit == null ) {
                throw new RuntimeException( "LIMITATION: Java class that extends MovieClip must have a no-arg constructor" );
            }

            if( J2AVM.TRACE_ON ) cons.trace( J2AVM.TRACE_PREFIX + "MovieClip extension - calling <init>() from constructor" );

            cons.getLocal( cons.thisValue );            
            cons.callPropVoid( noArgInit.getAVM2Name(), 0 );
        }
        
        cons.returnVoid();
        cons.analyze();
    }
}
