package org.epistem.j2avm.translator.impl.java;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.impl.FieldTranslatorBase;
import org.epistem.j2swf.swf.code.CodeClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.code.instructions.FieldAccess;
import org.epistem.jvm.flags.FieldFlag;
import org.epistem.jvm.type.ObjectType;

import com.anotherbigidea.flash.avm2.model.AVM2Traits;

/**
 * Generic translator for Java fields
 *
 * @author nickmain
 */
public class JavaFieldTranslator extends FieldTranslatorBase {

    /*pkg*/ JavaFieldTranslator( ClassTranslator classTrans, JVMField field ) {
        super( classTrans, field );
    }

    /**
     * @see org.epistem.j2avm.translator.MemberTranslator#translateImplementation(org.epistem.j2swf.swf.code.CodeClass)
     */
    public void translateImplementation( CodeClass codeClass ) {
        
        //static or instance traits
        AVM2Traits traits = jvmField.flags.contains( FieldFlag.FieldIsStatic ) ?
                                codeClass.avm2class.staticTraits :
                                codeClass.avm2class.traits ;
        
        //make sure field type is also translated
        if( jvmField.type instanceof ObjectType ) {
            getClassTranslator().getManager().requireClass( (ObjectType) jvmField.type );
        }
        
        //TODO: default value
        
        traits.addVar( getAVM2Name(), getAVM2Type(), null );
    }

    /**
     * @see org.epistem.j2avm.translator.FieldTranslator#translateRead(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.FieldAccess)
     */
    public void translateRead( MethodTranslator method, FieldAccess access ) {
        // TODO Auto-generated method stub

    }

    /**
     * @see org.epistem.j2avm.translator.FieldTranslator#translateWrite(org.epistem.j2avm.translator.MethodTranslator, org.epistem.jvm.code.instructions.FieldAccess)
     */
    public void translateWrite( MethodTranslator method, FieldAccess access ) {
        // TODO Auto-generated method stub

    }
}
