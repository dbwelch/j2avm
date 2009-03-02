package org.epistem.j2avm.translator.impl;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.FieldTranslator;
import org.epistem.j2avm.translator.MethodTranslator;
import org.epistem.j2avm.translator.TranslatorManager;
import org.epistem.j2avm.util.NameUtils;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.flags.FieldFlag;
import org.epistem.jvm.type.ObjectType;

import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * The translator for a field
 *
 * @author nickmain
 */
public abstract class FieldTranslatorBase extends MemberTranslatorBase  
                                          implements FieldTranslator {

    protected final JVMField jvmField;
    protected final boolean isStatic;    
    protected final boolean isFinal;
    
    protected FieldTranslatorBase( ClassTranslator classTrans, JVMField field ) {
        super( classTrans, 
               field.name, 
               makeAVM2Name( classTrans, field ), 
               NameUtils.qnameForJavaType( field.type ) );
        this.jvmField = field;
        this.isStatic = field.flags.contains( FieldFlag.FieldIsStatic );
        this.isFinal  = field.flags.contains( FieldFlag.FieldIsFinal );
    }
    
    /**
     * Get a field translator based on any annotations found on the field
     * or, failing that, one created by the class translator
     */
    public static FieldTranslator forField( JVMField field, ClassTranslatorBase classBase ) {
        JavaAnnotation annot = 
            TranslatorManager.findTranslatorAnnotation( classBase.manager.loader, 
                                                        field.attributes );
        if( annot != null ) {
            ObjectType helperType = (ObjectType) annot.classValue( "value" );
            
            try {
                @SuppressWarnings("unchecked")
                Class<? extends MethodTranslator> ctClass = 
                    (Class<? extends MethodTranslator>) Class.forName( helperType.name );

                return (FieldTranslator) 
                    ctClass.getConstructor( ClassTranslatorBase.class, JVMField.class )
                           .newInstance( classBase, field );
            } catch( Exception e ) {
                throw new RuntimeException( e );
            }
        }
        
        return classBase.defaultFieldTranslator( field );
    }
    
    private static AVM2QName makeAVM2Name( ClassTranslator classTrans, JVMField field ) {
        AVM2Namespace namespace = null;
        if( field.flags.contains( FieldFlag.FieldIsPrivate ) ) {
            namespace = classTrans.getAVM2PrivateNamespace();
        }
        else if( field.flags.contains( FieldFlag.FieldIsProtected ) ) {
            namespace = classTrans.getAVM2ProtectedNamespace();
        } 
        else if( field.flags.contains( FieldFlag.FieldIsPublic ) ) {
            namespace = AVM2Namespace.publicNamespace;
        } 
        else {
            namespace = classTrans.getAVM2InternalNamespace();
        }
        
        //TODO: should there be some sort of name mangling ?
        return new AVM2QName( namespace, field.name );                 
    }

}
