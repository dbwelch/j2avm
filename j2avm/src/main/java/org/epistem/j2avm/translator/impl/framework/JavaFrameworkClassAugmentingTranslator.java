package org.epistem.j2avm.translator.impl.framework;

import java.util.ArrayList;
import java.util.List;

import org.epistem.j2avm.annotations.runtime.FlashTargetClass;
import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.translator.impl.MemberTranslatorBase;
import org.epistem.j2avm.translator.impl.flash.FlashNativeDummyMethodTranslator;
import org.epistem.j2avm.translator.impl.java.JavaClassTranslator;
import org.epistem.j2swf.swf.code.*;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.code.instructions.FieldAccess;
import org.epistem.jvm.code.instructions.InstanceOf;
import org.epistem.jvm.code.instructions.New;
import org.epistem.jvm.type.ObjectType;

import com.anotherbigidea.flash.avm2.model.*;

/**
 * Translator for Java framework classes that augments existing Flash
 * classes with extra methods.
 *
 * @author nickmain
 */
public class JavaFrameworkClassAugmentingTranslator extends JavaFrameworkClassTranslator {

    private final ObjectType targetClass;
    
    public JavaFrameworkClassAugmentingTranslator( TranslatorManager manager, JVMClass jvmClass ) {
        super( manager, jvmClass );
        targetClass = extractTarget( jvmClass );
    }
    
    /**
     * Extract the target Flass class
     */
    private ObjectType extractTarget( JVMClass jvmClass ) {
        try {
            JavaAnnotation annot = jvmClass.attributes.annotation( FlashTargetClass.class.getName() );
            
            if( annot != null ) {
                return (ObjectType) annot.classValue( "value" );
            }
        }
        catch( Exception ex ) {
            throw new RuntimeException( ex );
        }
        
        throw new RuntimeException( "TargetClass annotation is missing on " + jvmClass.name );
    }
    
    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#getSuperclass() */
    @Override
    public ClassTranslator getSuperclass() {
        
        //Object has no superclass
        if( jvmClass.name.name.equals( "j2avm.java.lang.Object" ) ) {
            return null;
        }
        
        return super.getSuperclass();
    }

    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultFieldTranslator(org.epistem.jvm.JVMField) */
    @Override
    public FieldTranslator defaultFieldTranslator( JVMField field ) {
        JavaFrameworkFieldTranslator t = new JavaFrameworkFieldTranslator( this, field );        
        t.setAVM2Name( new AVM2QName( t.getAVM2Name().name )); //make public
        return t;
    }

    /** @see org.epistem.j2avm.translator.impl.ClassTranslatorBase#defaultMethodTranslator(org.epistem.jvm.JVMMethod) */
    @Override
    public MethodTranslator defaultMethodTranslator( JVMMethod method ) {

        if( method.name.equals( JavaClassTranslator.INIT_NAME ) ) {
            return new FlashNativeDummyMethodTranslator( this, method );
        }        
        
        return new JavaFrameworkMethodTranslator( this, method );
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateImplementation(Code))
     */
    public void translateImplementation( final Code code ) {       

        CodeClass mainClass = ((JavaClassTranslator) manager.getMainClass()).getCodeClass();
        final CodeClassInitializer mainInit = mainClass.getStaticInitializer();
        final ClassTranslator target = manager.translatorForClass( targetClass );
        final List<CodeMethod> methods = new ArrayList<CodeMethod>();

        final boolean[] hasClinit = {false};
        
        codeClass = new CodeClass( mainClass ) {

            /** @see CodeClass#addAbstractMethod(AVM2QName, AVM2Name, boolean, AVM2Name[]) */
            @Override
            public CodeMethod addAbstractMethod( AVM2QName name, AVM2Name returnType, boolean isOverride, AVM2Name... paramTypes ) {
                throw new RuntimeException( "UNIMPLEMENTED" );
                // TODO Auto-generated method stub
                //return super.addAbstractMethod( name, returnType, isOverride, paramTypes );
            }

            /** @see CodeClass#addInstanceMethod(AVM2QName, AVM2Name, boolean, boolean, AVM2Name[]) */
            @Override
            public CodeMethod addInstanceMethod( final AVM2QName name, AVM2Name returnType, boolean isFinal, boolean isOverride, AVM2Name... paramTypes ) {
                
                final AVM2Method func = code.addFunction( returnType );
                func.methodBody.scopeDepth = scopeDepth;
                
                CodeMethod cm = makeFunction( func, this, name, paramTypes );                
                methods.add( cm );
                
                //add to the main class static init to augment the target class
                AVM2Code c2 = mainInit.code();
                c2.getLex( target.getAVM2Name() );
                c2.getProperty( "prototype" );
                c2.pushString( name.name );
                c2.newFunction( func );
                c2.setProperty( new AVM2LateMultiname( AVM2StandardNamespace.EmptyPackage.namespace ) );

                return cm;                
            }

            /** @see CodeClass#addStaticMethod(AVM2QName, AVM2Name, AVM2Name[]) */
            @Override
            public CodeMethod addStaticMethod( AVM2QName name, AVM2Name returnType, AVM2Name... paramTypes ) {
                final AVM2Method func = code.addFunction( returnType );
                func.methodBody.scopeDepth = scopeDepth;
                
                CodeMethod cm = makeFunction( func, this, name, paramTypes );                
                methods.add( cm );
                
                //add to the target class
                AVM2Code c2 = mainInit.code();
                c2.getLex( target.getAVM2Name() );
                c2.pushString( name.name );
                c2.newFunction( func );
                c2.setProperty( new AVM2LateMultiname( AVM2StandardNamespace.EmptyPackage.namespace ) );

                if( name.name.equals( "<clinit>()" ) ) hasClinit[0] = true;
                
                return cm;                
            }

            /** @see CodeClass#addField(AVM2QName, AVM2Name, Object, boolean, boolean) */
            @Override
            protected CodeField addField( AVM2QName name, AVM2Name type, Object defaultValue, boolean isStatic, boolean isConstant ) {
                
                if( isStatic ) {
                    //add to the target class
                    AVM2Code c2 = mainInit.code();
                    c2.getLex( target.getAVM2Name() );
                    c2.pushString( name.name );
                    pushDefaultValue( c2, defaultValue, type );                                    
                    c2.setProperty( new AVM2LateMultiname( AVM2StandardNamespace.EmptyPackage.namespace ) );
                }
                else {
                    //add to the main class static init to augment the target class
                    AVM2Code c2 = mainInit.code();
                    c2.getLex( target.getAVM2Name() );
                    c2.getProperty( "prototype" );
                    c2.pushString( name.name );
                    pushDefaultValue( c2, defaultValue, type );                    
                    c2.setProperty( new AVM2LateMultiname( AVM2StandardNamespace.EmptyPackage.namespace ) );
                }
                
                return new CodeField( this, null, isStatic, isConstant );
            }
            
            private void pushDefaultValue( AVM2Code c2, Object value, AVM2Name type ) {
                
                if( value == null ) {
                    c2.pushUndefined();
                }                
                else if( value instanceof Integer ) {
                    
                    if( type.equals( AVM2StandardName.TypeBoolean.qname ) ) {
                        if( ((Integer)value).intValue() == 0 ) {
                            c2.pushBoolean( false );
                        }
                        else {
                            c2.pushBoolean( true );
                        }
                    }
                    else {
                        c2.pushInt( (Integer) value );
                    }
                }
                else if( value instanceof Long ) { //TODO: long support
                    c2.pushDouble( ((Number) value).doubleValue() );
                }
                else if( value instanceof Double ) {
                    c2.pushDouble( (Double) value );
                }
                else if( value instanceof Float ) {
                    c2.pushDouble( (Float) value );
                }
                else if( value instanceof String ) {
                    c2.pushString( (String) value );
                }
                else throw new RuntimeException( "Unknown constant type" );
            }
        };        
        
        translateMembers();
        
        //if there was a clinit then call it
        if( hasClinit[0] ) {
            //add to the target class
            AVM2Code c2 = mainInit.code();
            c2.getLex( target.getAVM2Name() );
            c2.callPropVoid( "<clinit>()", 0 );
        }
        
        for( CodeMethod cm : methods ) {
            cm.code().analyze();
        }
    }

    //make a standalone function
    private CodeMethod makeFunction( final AVM2Method func,
                                     CodeClass codeClass,
                                     final AVM2QName name,
                                     AVM2Name... paramTypes ) {
        
        for( int i = 0; i < paramTypes.length; i++ ) {
            func.addParameter( "arg"+(i+1), paramTypes[i], null );                    
        }
                        
        CodeMethod cm = new CodeMethod( codeClass, null, paramTypes ) {
            /** @see org.epistem.j2swf.swf.code.CodeMethod#makeCode(com.anotherbigidea.flash.avm2.model.AVM2Name[]) */
            @Override
            protected AVM2Code makeCode( AVM2Name... paramTypes ) {
                return new AVM2Code( func.methodBody, null, paramTypes.length, false );
            }

            /** @see org.epistem.j2swf.swf.code.CodeMethod#name() */
            @Override
            public AVM2QName name() {
                return name;
            }                    
        };
        
        return cm;
    }
    
    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateInstanceOf(MethodTranslator, InstanceOf)
     */
    public void translateInstanceOf( MethodTranslator method, InstanceOf instOfInsn ) {
        ClassTranslator target = manager.translatorForClass( targetClass );
        target.translateInstanceOf( method, instOfInsn );
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateInstantiation(MethodTranslator, New)
     */
    public void translateInstantiation( MethodTranslator method, New newInsn ) {
        ClassTranslator target = manager.translatorForClass( targetClass );
        target.translateInstantiation( method, newInsn ); 
    }

    /**
     * @see org.epistem.j2avm.translator.ClassTranslator#translateStaticPush(MethodTranslator)
     */
    public void translateStaticPush( MethodTranslator method ) {
        ClassTranslator target = manager.translatorForClass( targetClass );
        target.translateStaticPush( method );
    }
    
    
}
