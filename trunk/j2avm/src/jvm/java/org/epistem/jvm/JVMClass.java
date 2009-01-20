package org.epistem.jvm;

import java.io.IOException;
import java.util.*;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.flags.ClassFlag;
import org.epistem.jvm.io.print.ClassPrinter;
import org.epistem.jvm.type.ObjectType;

/**
 * A JVM Class definition.
 *
 * @author nickmain
 */
public class JVMClass {

    public static final int MAGIC = 0xCAFEBABE;
    public static final int MAJOR = 0x0031;
    public static final int MINOR = 0x0000;
    
    /** The class name */
    public final ObjectType name;

    /** The class loader */
    public final JVMClassLoader loader;    
    
    /**
     * The class attributes
     */
    public final AttributeContainer attributes;
    
    public final Collection<ObjectType>   interfaces  = new HashSet<ObjectType>();
    public final Collection<JVMField>     fields      = new HashSet<JVMField>();
    public final Collection<JVMMethod>    methods     = new HashSet<JVMMethod>();    
    public final Collection<ClassFlag>    flags       = EnumSet.noneOf( ClassFlag.class );    
    
    public final ObjectType superclassName;
    public final int majorVersion;
    public final int minorVersion;
 
    public JVMClass( ObjectType name, 
                     JVMClassLoader loader,     
                     ObjectType superclassName,
                     int majorVersion,
                     int minorVersion ) { 
        this.name     = name;
        this.loader   = loader;
        this.superclassName = superclassName;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        attributes = new AttributeContainer( loader );
    }
 
    /** @see java.lang.Object#toString() */
    public String toString() {
        return "class " + name;
    }    
    
    /**
     * Get the superclass
     */
    public JVMClass getSuperclass() throws ClassNotFoundException, IOException {
        if( superclassName == null ) return null;
        return loader.getClass( superclassName );
    }
    
    /**
     * Dump class for debug purposes
     */
    public void dump( IndentingPrintWriter ipw ) {
        new ClassPrinter( ipw ).print( this );
        ipw.flush();
    }
}

    