package org.epistem.jvm.attributes;

import java.util.Collection;
import java.util.EnumSet;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.type.ObjectType;
import org.epistem.util.Flag;

/**
 * Information about an inner class
 *
 * @author nickmain
 */
public class InnerClass {

    /**
     * The flags that apply to inner classes
     */
    public static enum InnerClassFlag {
        
        @Flag( 0x0001 ) InnerClassIsPublic,
        @Flag( 0x0002 ) InnerClassIsPrivate,
        @Flag( 0x0004 ) InnerClassIsProtected,
        @Flag( 0x0008 ) InnerClassIsStatic,
        @Flag( 0x0010 ) InnerClassIsFinal,
        @Flag( 0x0200 ) InnerClassIsInterface,
        @Flag( 0x0400 ) InnerClassIsAbstract,
        @Flag( 0x1000 ) InnerClassIsSynthetic,
        @Flag( 0x2000 ) InnerClassIsAnnotation,
        @Flag( 0x4000 ) InnerClassIsEnum;
    }
    
    /**
     * The simple name of the inner class in the original source.
     * This is null if the inner class is anonymous.
     */
    public final String simpleName;
    
    /**
     * The inner class flags
     */
    public final Collection<InnerClassFlag> flags = EnumSet.noneOf(InnerClassFlag.class);
    
    /**
     * The outer (containing) class - null if the inner class is not a member.
     */
    public final ObjectType outerClass;
    
    /**
     * The inner class
     */
    public final ObjectType innerClass;
    
    /**
     * @param simpleName null if the class is anonymous
     * @param outerClass null if the inner class is not a member
     * @param innerClass the inner class
     */
    public InnerClass( String simpleName, 
                       ObjectType outerClass, ObjectType innerClass ) {
        this.outerClass = outerClass;
        this.innerClass = innerClass;
        this.simpleName = simpleName;
    }

    /** @see java.lang.Object#equals(java.lang.Object) */
    @Override
    public boolean equals(Object obj) {
        if( obj == null ) return false;
        if( !( obj instanceof InnerClass )) return false;
        return ((InnerClass) obj).innerClass.equals( innerClass );
    }

    /** @see java.lang.Object#hashCode() */
    @Override
    public int hashCode() {
        return innerClass.hashCode();
    }
    
    /**
     * Dump for debug purposes
     */
    public void dump( IndentingPrintWriter out ) {
        out.println( "innerclass {" );
        out.indent();
        
        out.println( "simple name: " + simpleName );
        out.println( "inner class: " + innerClass );
        out.println( "outer class: " + outerClass );
        out.print  ( "inner flags:" );
        for( InnerClassFlag flag : flags ) {
            out.print( " " + flag.name());
        }
        out.println();
        
        out.unindent();
        out.println( "}" );
    }
}
