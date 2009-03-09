package org.epistem.j2avm.translator.impl.java;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.FieldTranslatorBase;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.attributes.ConstantValueAttribute;
import org.epistem.jvm.code.instructions.FieldAccess;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.PrimitiveType;

import com.anotherbigidea.flash.avm2.ValueKind;
import com.anotherbigidea.flash.avm2.model.AVM2DefaultValue;
import com.anotherbigidea.flash.avm2.model.AVM2Traits;

/**
 * Generic translator for Java fields
 *
 * @author nickmain
 */
public class JavaFieldTranslator extends FieldTranslatorBase {
    
    protected JavaFieldTranslator( ClassTranslator classTrans, JVMField field ) {
        super( classTrans, field );
    }

    /**
     * @see org.epistem.j2avm.translator.MemberTranslator#translateImplementation(org.epistem.j2swf.swf.code.CodeClass)
     */
    public void translateImplementation( CodeClass codeClass ) {
        
        //static or instance traits
        AVM2Traits traits = isStatic ?
                                codeClass.avm2class.staticTraits :
                                codeClass.avm2class.traits ;
        
        //make sure field type is also translated
        if( jvmField.type instanceof ObjectType ) {
            classTranslator.getManager().requireClass( NameUtils.normalize( (ObjectType) jvmField.type ));
        }
        
        //static final constant fields
        if( isStatic && isFinal ) {
            ConstantValueAttribute valAttr = jvmField.attributes.forClass( ConstantValueAttribute.class );
            if( valAttr != null ) {
                AVM2DefaultValue defValue;
                Object value = valAttr.value;
                
                if( value instanceof Integer ) {
                    
                    if( jvmField.type == PrimitiveType.BOOLEAN ) {
                        if( ((Integer)value).intValue() == 0 ) {
                            defValue = new AVM2DefaultValue( ValueKind.CONSTANT_False, null );
                        }
                        else {
                            defValue = new AVM2DefaultValue( ValueKind.CONSTANT_True, null );                            
                        }
                    }
                    else {
                        defValue = new AVM2DefaultValue( ValueKind.CONSTANT_Int, value );
                    }
                }
                else if( value instanceof Long ) { //TODO: long support
                    defValue = new AVM2DefaultValue( ValueKind.CONSTANT_Double, ((Long)value).doubleValue() ); 
                }
                else if( value instanceof Double ) {
                    defValue = new AVM2DefaultValue( ValueKind.CONSTANT_Double, value ); 
                }
                else if( value instanceof Float ) {
                    defValue = new AVM2DefaultValue( ValueKind.CONSTANT_Double, ((Float)value).doubleValue() ); 
                }
                else if( value instanceof String ) {
                    defValue = new AVM2DefaultValue( ValueKind.CONSTANT_Utf8, value ); 
                }
                else throw new RuntimeException( "Unknown constant type" );
                
                traits.addConst( getAVM2Name(), getAVM2Type(), defValue );
                return;
            }
        }
        
        traits.addVar( getAVM2Name(), getAVM2Type(), null );
    }

    /**
     * @see org.epistem.j2avm.translator.FieldTranslator#translateRead(MethodTranslator, FieldAccess, boolean)
     */
    public void translateRead( MethodTranslator method, FieldAccess access, boolean isSuper ) {
        if( access.isStatic ) { //push class onto the stack
            classTranslator.translateStaticPush( method );
        }

        if( isSuper ) method.getCode().code().getSuperProperty( avm2Name );
        else method.getCode().code().getProperty( avm2Name );
    }

    /**
     * @see org.epistem.j2avm.translator.FieldTranslator#translateWrite(MethodTranslator, FieldAccess, boolean)
     */
    public void translateWrite( MethodTranslator method, FieldAccess access, boolean isSuper ) {
        if( access.isStatic ) {
            classTranslator.translateStaticPush( method );
            method.getCode().code().swap();
        }

        if( isSuper ) method.getCode().code().setSuperProperty( avm2Name );
        else method.getCode().code().setProperty( avm2Name );
    }
}
