package org.epistem.jvm.io.print;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClass;
import org.epistem.jvm.JVMField;
import org.epistem.jvm.JVMMethod;
import org.epistem.jvm.code.analysis.Analyzer;
import org.epistem.jvm.flags.ClassFlag;
import org.epistem.jvm.flags.FieldFlag;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.type.ObjectType;

/**
 * A printer for JVMClass
 *
 * @author nickmain
 */
public class ClassPrinter {
    
    private final IndentingPrintWriter out;
    
    /**
     * Set to true to cause method analysis
     */
    private boolean analyze = false;
    
    public ClassPrinter( IndentingPrintWriter out ) {
        this.out = out;
    }
    
    /**
     * @param analyze whether to analyze the methods
     */
    public ClassPrinter( IndentingPrintWriter out, boolean analyze ) {
        this.out = out;
        this.analyze = analyze;
    }

    /**
     * Print and entire class
     */
    public void print( JVMClass jclass ) {
        out.println( "class " + jclass.name + " extends " + jclass.superclassName + "\n{" );
        out.indent();
        
        for( ObjectType iface : jclass.interfaces ) {
            out.println( "implements " + iface );
        }
        
        out.print( "flags <" );
        for( ClassFlag flag : jclass.flags ) {
            out.print( " " + flag.name() );
        }
        out.println( " >" );
        
        out.println( "version " + jclass.majorVersion + "." + jclass.minorVersion );
        
        for( JVMField field : jclass.fields ) {
            out.println();
            print( field );
        }

        for( JVMMethod method : jclass.methods ) {
            out.println();            
            print( method );
        }
        
        out.println();
        out.println( "attributes\n{" );
        out.indent();
        for( JVMAttribute attr : jclass.attributes.values() ) {
            attr.dump( out );
        }
        out.unindent();
        out.println( "}" );
        
        out.unindent();
        out.println( "}" );        
    }
    
    /**
     * Print a field
     */
    public void print( JVMField field ) {
        
        out.print( field.type + " " + field.name + " <");
        for( FieldFlag flag : field.flags ) {
            out.print( " " + flag.name() );
        }
        out.println( " >" );
        
        if( ! field.attributes.isEmpty() ) {
            out.println( "{" );
            out.indent();
            for( JVMAttribute attr : field.attributes.values() ) {
                attr.dump( out );
            }            
            out.unindent();
            out.println( "}" );
        }
    }

    /**
     * Print a method
     */
    public void print( JVMMethod method ) {
        Analyzer analyzer = analyze ? method.analyzer() : null;                
        
        out.print( method.type + " " + method.signature + " <");
        for( MethodFlag flag : method.flags ) {
            out.print( " " + flag.name() );
        }
        out.println( " >" );

        if( ! method.attributes.isEmpty() ) {
            out.println( "{" );
            out.indent();
            for( JVMAttribute attr : method.attributes.values() ) {
                attr.dump( out );
            }            
            out.unindent();
            out.println( "}" );
        }
    }

}
