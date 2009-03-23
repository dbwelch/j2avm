package org.epistem.j2avm.translator;

import java.util.Collection;

import org.epistem.j2swf.swf.code.Code;
import org.epistem.jvm.attributes.JavaAnnotation;
import org.epistem.jvm.code.instructions.InstanceOf;
import org.epistem.jvm.code.instructions.New;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;

import com.anotherbigidea.flash.avm2.model.AVM2Namespace;
import com.anotherbigidea.flash.avm2.model.AVM2QName;


/**
 * A translator for a class. A bridge between the Java class and the AVM2 class.
 *
 * @author nickmain
 */
public interface ClassTranslator {

    /**
     * Get the translation manager
     */
    public TranslatorManager getManager();
    
    /**
     * Write the class implementation into the given code block
     * 
     * @param code the target avm2 code block
     */
    public void translateImplementation( Code code );
    
    /**
     * Write the code to instantiate this class
     * 
     * @param method the method being written
     * @param newInsn the instruction being translated 
     */
    public void translateInstantiation( MethodTranslator method, New newInsn );
    
    /**
     * Translate an instanceof operation
     * 
     * @param method the method being written
     * @param instOfInsn the instanceOf instruction
     */
    public void translateInstanceOf( MethodTranslator method, InstanceOf instOfInsn );
    
    /**
     * Generate instruction(s) to push the static class object (that holds the
     * static properties and methods) on the stack.
     * 
     * @param method the method being written
     */
    public void translateStaticPush( MethodTranslator method );
    
    /**
     * Get the translator for the superclass
     * 
     * @return null if there is no superclass
     */
    public ClassTranslator getSuperclass();
    
    /**
     * Get the implemented interfaces
     * @return empty collection if none
     */
    public Collection<ClassTranslator> getInterfaces();
    
    /**
     * Get the translator for the given method - looking in superclasses
     * if necessary.
     * 
     * @param sig the JVM signature of the method
     * @throws NoSuchMethodException if the method cannot be found
     */
    public MethodTranslator getMethodTranslator( ClassTranslator owner, Signature sig ) throws NoSuchMethodException;
    
    /**
     * Get the translator for the given field - looking in superclasses
     * if necessary
     * 
     * @param name the JVM field name
     * @throws NoSuchFieldException if the field cannot be found
     */
    public FieldTranslator getFieldTranslator( ClassTranslator owner, String name ) throws NoSuchFieldException;
    
    /**
     * Get the JVM type
     */
    public ObjectType getJVMType();
    
    /**
     * Get the AVM2 name for the class
     */
    public AVM2QName getAVM2Name();    

    /**
     * Get the AVM2 protected namespace of the class
     */
    public AVM2Namespace getAVM2ProtectedNamespace();
    
    /**
     * Get the AVM2 internal namespace of the class
     */
    public AVM2Namespace getAVM2InternalNamespace();

    /**
     * Get the AVM2 private namespace of the class
     */
    public AVM2Namespace getAVM2PrivateNamespace();
    
    /**
     * Get the specified Java annotation
     * @return null if the annotation does not exist
     */
    public JavaAnnotation getAnnotation( String name );
}
