package org.epistem.j2avm.translator;

import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.flags.FieldFlag;
import org.epistem.jvm.type.ObjectType;

import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;
import com.anotherbigidea.flash.avm2.model.AVM2Traits;

/**
 * The translator for a field
 *
 * @author nickmain
 */
public class FieldTranslator {

    /**
     * The JVM field being translated
     */
    /*pkg*/ final JVMField jvmField;
    
    /**
     * The AVM2 name for the field
     */
    /*pkg*/ final AVM2QName avm2name;
    
    /**
     * The AVM2 type of the field
     */
    /*pkg*/ final AVM2QName avm2type;

    /**
     * The helper for accesses of this field
     */
    /*pkg*/ final TranslationHelper helper;
    
    /**
     * The class this field belongs to
     */
    private final ClassTranslator classTrans;
    
    /**/ public FieldTranslator( ClassTranslator classTrans, JVMField field ) {
        this.jvmField = field;
        this.classTrans = classTrans;
        avm2type = NameUtils.qnameForJavaType( jvmField.type );
        
        this.helper = classTrans.manager.helperForField( field, classTrans );
        
        AVM2Namespace namespace = null;
        if( jvmField.flags.contains( FieldFlag.FieldIsPrivate ) ) {
            namespace = classTrans.privateNamespace;
        }
        else if( jvmField.flags.contains( FieldFlag.FieldIsProtected ) ) {
            namespace = classTrans.protectedNamespace;
        } 
        else if( jvmField.flags.contains( FieldFlag.FieldIsPublic ) ) {
            namespace = AVM2Namespace.publicNamespace;
        } 
        else {
            namespace = classTrans.internalNamespace;
        }
        
        //TODO: should there be some sort of name mangling ?
        avm2name = new AVM2QName( namespace, jvmField.name );         
    }
    
    /**
     * Translate the field
     * 
     * @param state the target context
     */
    /*pkg*/ void translate( TranslationState state ) {
        //static or instance traits
        AVM2Traits traits = jvmField.flags.contains( FieldFlag.FieldIsStatic ) ?
                                state.codeClass.avm2class.staticTraits :
                                state.codeClass.avm2class.traits ;
        
        //make sure field type is also translated
        if( jvmField.type instanceof ObjectType ) {
            state.requireClass( jvmField.type.name );
        }
        
        traits.addVar( avm2name, avm2type, null );
    }
}
