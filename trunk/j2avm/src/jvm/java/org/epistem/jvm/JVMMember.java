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
     * The attributes
     */
    public final AttributeContainer attributes;
    
    /*pkg*/ JVMMember( JVMClass containerClass,
                        String name,
                        JVMType type,
                        Class<FLAGTYPE> flagClass ) {
        
        this.containerClass = containerClass;
        this.name           = name;
        this.type           = type;
        this.flags          = EnumSet.noneOf( flagClass );
        
        attributes = new AttributeContainer( containerClass.loader );
    }
           
    @Override
    public String toString() {
        return containerClass.name + "::" + name;
    }    
}