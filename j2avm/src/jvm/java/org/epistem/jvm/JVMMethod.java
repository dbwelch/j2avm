package org.epistem.jvm;

import java.io.IOException;
import java.util.*;

import org.epistem.jvm.attributes.*;
import org.epistem.jvm.code.analysis.Analyzer;
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
    
    private Analyzer analyzer;

    private List<Set<JavaAnnotation>> paramAnnotations;
    
    public JVMMethod( JVMClass containerClass, Signature signature, JVMType type ) {
        super( containerClass, signature.name, type, MethodFlag.class );  
        this.signature = signature;
    }

    /**
     * Get the analysis for the method - performing if necessary.
     */
    public Analyzer analyzer() {
        if( analyzer == null ) {
            if( flags.contains( MethodFlag.MethodIsAbstract ) ) return null;
            
            analyzer = new Analyzer( this );
        }
        
        return analyzer;
    }
    
    /**
     * Get the exceptions thrown by the method
     * 
     * @return empty collection if none
     */
    public Collection<ObjectType> getThrownExceptions() {
        ExceptionsAttribute excps = attributes.forClass( ExceptionsAttribute.class );        
        if( excps == null ) return Collections.emptySet();        
        return excps.thrownExceptions;
    }
    
    /**
     * If this is a method in an annotation then get the default value.
     * @return null if there is no default value
     */
    public JavaAnnotation.Value getAnnotationDefault() {
        AnnotationDefaultAttribute ada =  attributes.forClass( AnnotationDefaultAttribute.class );        
        if( ada == null ) return null;        
        return ada.value;
    }
    
    /**
     * Get the code attribute
     * 
     * @return null if this method has no code (is native or abstract)
     */
    public CodeAttribute getCode() {
       return attributes.forClass( CodeAttribute.class ); 
    }
    
    /**
     * Get the annotations on the parameters
     */
    public final List<Set<JavaAnnotation>> getParameterAnnotations() {
        
        List<Set<JavaAnnotation>> annoSets = new ArrayList<Set<JavaAnnotation>>();

        RuntimeInvisibleParameterAnnotationsAttribute ripaa = 
            attributes.forClass( RuntimeInvisibleParameterAnnotationsAttribute.class );
            
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
            attributes.forClass( RuntimeVisibleParameterAnnotationsAttribute.class );
        
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
     * Get the annotations on the parameters - filling in default values and
     * caching.
     * 
     * @return set of annotations 
     * @throws ClassNotFoundException if any annotation class is not found
     * @throws IOException if any annotation class cannot be parsed
     */
    public final List<Set<JavaAnnotation>> getDefaultedParameterAnnotations()  
        throws ClassNotFoundException, IOException {
        
        if( paramAnnotations != null ) return paramAnnotations;
        
        paramAnnotations = getParameterAnnotations();
        for( Set<JavaAnnotation> annos : paramAnnotations ) {
            for( JavaAnnotation anno : annos ) {
                anno.fillInDefaults( containerClass.loader );
            }
        }
        
        return paramAnnotations;
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
