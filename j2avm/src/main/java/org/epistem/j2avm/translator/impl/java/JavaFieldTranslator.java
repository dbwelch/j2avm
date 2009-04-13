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
        
        //make sure field type is also translated
        if( jvmField.type instanceof ObjectType ) {
            classTranslator.getManager().requireClass( NameUtils.normalize( (ObjectType) jvmField.type ));
        }

        if( isStatic ) {
            
            //translate static final fields with values as constants
            if( isFinal ) {
                ConstantValueAttribute valAttr = jvmField.attributes.forClass( ConstantValueAttribute.class );
                if( valAttr != null ) {
                    Object value = valAttr.value;
                    
                    codeClass.addStaticConstant( getAVM2Name(), getAVM2Type(), value );
                    return;
                }                
            }
            
            codeClass.addStaticField( getAVM2Name(), getAVM2Type(), null );
        }
        else {            
            codeClass.addField( getAVM2Name(), getAVM2Type(), null );            
        }
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
