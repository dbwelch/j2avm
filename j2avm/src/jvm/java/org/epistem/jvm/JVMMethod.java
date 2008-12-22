package org.epistem.jvm;

import java.util.*;

import org.epistem.jvm.attributes.*;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.type.JVMType;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;

/**
 * A member method
 *
 * @author nickmain
 */
public class JVMMethod extends JVMMember<MethodFlag> {

    /**
     * The method signature
     */
    public final Signature signature;

    public JVMMethod( JVMClass containerClass, Signature signature, JVMType type ) {
        super( containerClass, signature.name, type, MethodFlag.class );  
        this.signature = signature;
    }

    /**
     * Get the exceptions thrown by the method
     * 
     * @return empty collection if none
     */
    public Collection<ObjectType> getThrownExceptions() {
        ExceptionsAttribute excps = (ExceptionsAttribute)
            attributes.get( JVMAttribute.Name.Exceptions );
        
        if( excps == null ) return Collections.emptySet();        
        return excps.thrownExceptions;
    }
    
    /**
     * If this is a method in an annotation then get the default value.
     * @return null if there is no default value
     */
    public JavaAnnotation.Value getAnnotationDefault() {
        AnnotationDefaultAttribute ada = (AnnotationDefaultAttribute)
            attributes.get( JVMAttribute.Name.AnnotationDefault.name() );
        
        if( ada == null ) return null;
        
        return ada.value;
    }
    
    /**
     * Get the annotations on the parameters
     * @return set of annotations (not connected to the underlying attributes)
     */
    public final List<Set<JavaAnnotation>> getParameterAnnotations() {
        List<Set<JavaAnnotation>> annoSets = new ArrayList<Set<JavaAnnotation>>();

        RuntimeInvisibleParameterAnnotationsAttribute ripaa = 
            (RuntimeInvisibleParameterAnnotationsAttribute)
                attributes.get( JVMAttribute.Name.RuntimeInvisibleParameterAnnotations );
            
        if( ripaa != null ) {
            for( Map<String,JavaAnnotation> amap : ripaa.parameterAnnotations ) {
                Set<JavaAnnotation> annos = new HashSet<JavaAnnotation>();
                for( JavaAnnotation a : amap.values() ) {
                    annos.add( a );
                }
                
                annoSets.add( annos );
            }
        }
        
        RuntimeVisibleParameterAnnotationsAttribute rvpaa = 
            (RuntimeVisibleParameterAnnotationsAttribute)
                attributes.get( JVMAttribute.Name.RuntimeVisibleParameterAnnotations );
        
        if( ripaa != null ) {
            Iterator<Set<JavaAnnotation>> setIt = annoSets.iterator();

            for( Map<String,JavaAnnotation> amap : rvpaa.parameterAnnotations ) {
                Set<JavaAnnotation> annos = setIt.next();
                for( JavaAnnotation a : amap.values() ) {
                    annos.add( a );
                }
            }
        }
        
        return annoSets;
    }

    /**
     * Equality is based on the name and the parameter types 
     * @see java.lang.Object#equals(java.lang.Object) 
     */
    @Override
    public boolean equals(Object obj) {
        if( obj == null ) return false;
        if( !( obj instanceof JVMMethod )) return false;
        
        JVMMethod other = (JVMMethod) obj;
        
        if( ! other.signature.equals( signature ) ) return false;
        
        return true;
    }

    /** @see java.lang.Object#hashCode() */
    @Override
    public int hashCode() {        
        return name.hashCode() * signature.hashCode();
    }
}
