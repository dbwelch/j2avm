package org.epistem.j2avm.translator.impl.java;

import java.util.LinkedList;

import org.epistem.j2avm.J2AVM;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.translator.impl.ClassTranslatorBase;
import org.epistem.j2swf.swf.code.Code;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.instructions.InstanceOf;
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
public class JavaClassTranslator extends ClassTranslatorBase implements ClassTranslator {

    private static final Signature NO_ARG_CONTRUCTOR = new Signature( "<init>" );
    
    private CodeClass codeClass;
    
    public JavaClassTranslator(  TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass );
        
        addAllMemberTranslators();
    }
    
    protected JavaClassTranslator(  TranslatorManager manager, JVMClass jvmClass, AVM2QName avm2name ) {
        super( manager, jvmClass, avm2name, null, null, null );
    }
    
    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultFieldTranslator(org.epistem.jvm.JVMField) */
    @Override
    public FieldTranslator defaultFieldTranslator( JVMField field ) {
        return new JavaFieldTranslator( this, field );
    }

    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultMethodTranslator(org.epistem.jvm.JVMMethod) */
    @Override
    public MethodTranslator defaultMethodTranslator( JVMMethod method ) {
        return new JavaMethodTranslator( this, method );
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#translateImplementation(org.epistem.j2swf.swf.code.Code) */
    public void translateImplementation( Code code ) {
        J2AVM.log.info( "Translating class " + name );
        
        // TODO Auto-generated method stub
        
        //TODO: implemented interfaces
        //TODO: Enums

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
        boolean isIFace = jvmClass.flags.contains( ClassFlag.IsInterface );
        
        codeClass = code.addClass( avm2name.toQualString(), true, isFinal, isIFace, superclasses );
    
        //TODO: implemented interfaces
        //TODO: Enums
        
        for( FieldTranslator field : fields.values() ) {
            field.translateImplementation( codeClass );
        }
        for( MethodTranslator method : methods.values() ) {            
            method.translateImplementation( codeClass );
        }
        
        //-- Constructor
        makeConstructor();
        
        //TODO: static initializer
        //TODO: constructor

        
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
    
    /** @see org.epistem.j2avm.translator.ClassTranslator#translateInstanceOf(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.InstanceOf) */
    public void translateInstanceOf( MethodTranslator method, InstanceOf instOfInsn ) {
        method.getCode().code().isType( avm2name );
    }

    /** @see org.epistem.j2avm.translator.ClassTranslator#translateStaticPush(org.epistem.j2avm.translator.MethodTranslator) */
    public void translateStaticPush( MethodTranslator method ) {
        // TODO this would be the place to implement ClassLoader support ?
        
        method.getCode().code().getLex( avm2name );
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
    public void makeConstructor() {
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
    
    
//    private void THIS_IS_ONLY_FOR_DEV_PURPOSES( AVM2Code cons ) {
//        
//        cons.trace( "J2AVM: In Constructor" );
//        cons.getLocal( cons.thisValue );
//        cons.getProperty( "graphics" );
//        cons.coerceTo( "flash.display.Graphics" );
//
//        LocalValue<Instruction> g = cons.newLocal();
//        cons.setLocal( g );
//
//        cons.callPropVoid( g, "beginFill", 0x888800 );
//        cons.callPropVoid( g, "lineStyle", 5, 0x000088 );
//        cons.callPropVoid( g, "moveTo", 10, 10 );
//        cons.callPropVoid( g, "lineTo", 90, 10 );
//        cons.callPropVoid( g, "lineTo", 90, 90 );
//        cons.callPropVoid( g, "lineTo", 10, 90 );
//        cons.callPropVoid( g, "lineTo", 10, 10 );
//        cons.callPropVoid( g, "endFill" );
//
//        cons.trace( "J2AVM: At end of Constructor" );
//        
//        cons.returnVoid();    
//        cons.analyze();
//    }
}
