package org.epistem.jvm;

import java.util.*;

import org.epistem.jvm.type.JVMType;

/**
 * A class member 
 *
 * @author nickmain
 */
public abstract class JVMMember<FLAGTYPE extends Enum<FLAGTYPE>> {
    
    /**
     * The class that contains this member
     */
    public final JVMClass containerClass; 
    
    /**
     * The name of this member
     */
    public final String name;
    
    /**
     * The type of this member (return type if a method)
     */
    public final JVMType type;
    
    /**
     * Set of flags
     */
    public final Collection<FLAGTYPE> flags;
            
    /**
     * Map of attributes
     */
    public final Map<String,JVMAttribute> attributes = new HashMap<String,JVMAttribute>();
    
    /*pkg*/ JVMMember( JVMClass containerClass,
                        String name,
                        JVMType type,
                        Class<FLAGTYPE> flagClass ) {
        
        this.containerClass = containerClass;
        this.name           = name;
        this.type           = type;
        this.flags          = EnumSet.noneOf( flagClass );
    }
           
    @Override
    public String toString() {
        return containerClass.name + "::" + name;
    }
    
    /**
     * Get the attribute with the given class
     */
    public <T extends JVMAttribute> T attribute( Class<T> attrClass ) {
        for( JVMAttribute attr : attributes.values()) {
            if( attrClass.isAssignableFrom( attr.getClass() ) ) {
                @SuppressWarnings("unchecked") T t = (T) attr;
                return t;   
            }
        }
        
        return null;
    }
}