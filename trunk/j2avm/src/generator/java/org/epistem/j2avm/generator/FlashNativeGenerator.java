package org.epistem.j2avm.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;

import com.anotherbigidea.flash.avm2.NamespaceKind;
import com.anotherbigidea.flash.avm2.model.*;
import com.anotherbigidea.flash.avm2.utils.AVM2ABCFileLoader;

/**
 * Generates Java classes representing the ActionScript classes founds within
 * a SWF.
 *
 * @author nickmain
 */
public class FlashNativeGenerator {

    private IndentingPrintWriter out;
       
    private Collection<AVM2ABCFile> abcFiles;
    private File dir;
    
    /**
     * Read a SWF file
     * 
     * @return true if the file contained ABC code
     */
    public boolean readSWF( File swf ) throws IOException {
        abcFiles = AVM2ABCFileLoader.abcFilesFromExistingSWF( swf );
        return ! abcFiles.isEmpty();
    }
    
    /**
     * Generate Java sources from the loaded ABC code
     * 
     * @param dir the destination directory
     */
    public void generateJava( File dir ) throws IOException {
        this.dir = dir;

        for( AVM2ABCFile abcFile : abcFiles ) {        
            for( AVM2Class clazz : abcFile.classes.values() ) {
                String filename = clazz.name.name + ".java";
                if( clazz.name.namespace.name.length() == 0 ) continue;
                File pkgDir = new File( dir, clazz.name.namespace.name.replace( '.', '/' ) );
                pkgDir.mkdirs();
                
                FileWriter fw = new FileWriter( new File( pkgDir, filename ) );
                try {    
                    out = new IndentingPrintWriter( fw );
                    generateClass( clazz );
                } 
                finally {
                    out.flush();
                    fw.close();
                }
            }
        }
    }
    
    /**
     * Generate the Java source for a class
     */
    private void generateClass( AVM2Class clazz ) {
        System.out.println( "Generating " + clazz.name );
                
        templateStartClass( clazz );
    
        //TODO: static constants
        //TODO: static getters / setters
        //TODO: static methods
        
        //TODO: constructor
        //TODO: getters / setters

        for( AVM2Trait trait : clazz.traits.traits ) {
            boolean isPublic = trait.name.namespace.equals( AVM2Namespace.publicNamespace );
            boolean isProtected = trait.name.namespace.kind == NamespaceKind.ProtectedNamespace;
            if( ! ( isProtected || isPublic )) continue;
            
            System.out.println( clazz.name.toQualString() );
            
//            if( trait instanceof AVM2MethodSlot ) {
//                AVM2MethodSlot methodSlot = (AVM2MethodSlot) trait;
//                AVM2Method     method     = methodSlot.method;
//                
//                String name = trait.name.name; 
//                
//                
//                templateMethod( name, method.returnType, method.paramTypes, 
//                                method.paramNames, method.defaultValues.size(),
//                                isProtected, methodSlot.isFinal, 
//                                ! clazz.isInterface );
//            }
//            else if( trait instanceof AVM2Slot ) {
//                //TODO:
//            }
        }
        
        templateEndClass();
    }
    
    /**
     * Make a Java type name from an AVM2 type
     */
    private String typeToString( AVM2Name type ) {
        String pack = type.namespace.name;
        String name = type.name;
        
        if( pack.length() != 0 ) return pack + "." + name;
        
        if( name.equals( "int"      ) ) return "int";
        if( name.equals( "uint"     ) ) return "int";
        if( name.equals( "String"   ) ) return "String";
        if( name.equals( "Number"   ) ) return "double";
        if( name.equals( "Boolean"  ) ) return "boolean";
        if( name.equals( "Object"   ) ) return "flash.FlashObject";
        if( name.equals( "Array"    ) ) return "flash.FlashArray";
        if( name.equals( "Error"    ) ) return "flash.FlashError";
        if( name.equals( "Function" ) ) return "flash.FlashFunction";
        if( name.equals( "Date"     ) ) return "flash.FlashDate";
        
        return "flash." + name;
    }


    /**
     * Template for a method
     */
    private void templateMethod( String name, AVM2Name returnType, 
                                 List<AVM2Name> paramTypes,
                                 List<String>   paramNames,
                                 int optionalParamCount,
                                 boolean isProtected , boolean isFinal, boolean isNative ) {

        String visibility = isProtected ? " protected" : " public";
        String strRetType = typeToString( returnType );
        String strFinal   = isFinal  ? " final" : "";
        String strNative  = isNative ? " native" : "";
        
        String[] pTypes = new String[ paramTypes.size() ];
        String[] pNames = new String[ pTypes.length ];
        for( int i = 0; i < pTypes.length; i++ ) {
            pTypes[i] = typeToString( paramTypes.get( i ) );
            
            if( paramNames.size() > i ) {
                pNames[i] = paramNames.get( i );
            }
            else {
                pNames[i] = "arg" + (i+1);
            }
        }
        
        //generate all possible parameter lengths
        String[] paramStrings = new String[ optionalParamCount + 1 ];
        for( int i = 0; i < paramStrings.length; i++ ) {
            StringBuilder buff = new StringBuilder();
            
            for( int j = 0; j < pTypes.length - i; j++ ) {
                if( j == 0 ) buff.append( " " );
                else buff.append( ", " );
                
                buff.append( pTypes[j] );
                buff.append( " " );
                buff.append( pNames[j] );
            }
            
            buff.append( " " );
            paramStrings[i] = buff.toString();
        }
        
        for( int i = 0; i < paramStrings.length; i++ ) {
            out.println();
            out.println( visibility + strFinal + strNative + " " + strRetType + " " + name + "(" + paramStrings[i] + ");" );            
        }
    }

    /**
     * Template for a constructor
     */
    private void templateConstructor() {
        
    }

    /**
     * Template for a method signature
     */
    private void templateSignature( Signature signature ) {
        StringBuilder arguments = new StringBuilder();
        for( int i = 0; i < signature.paramTypes.length; i++ ) {
            if( i > 0 ) arguments.append( "," );
            arguments.append( " " );
            arguments.append( signature.paramTypes[i].name );
            arguments.append( " arg" + (i+1) );
        }
        if( arguments.length() > 0 ) arguments.append( " " );
        
        out.print( signature.name + "(" + arguments + ")" );
    }
    
    /**
     * The template for the start of a class
     */
    private void templateStartClass( AVM2Class clazz ) {
        
        StringBuilder ifaces = new StringBuilder();
        boolean first = true;
        for( AVM2Name ifname : clazz.interfaces ) {
            if( first ) {
                ifaces.append( " implements" );
                first = false;
            }
            else ifaces.append( "," );
            
            System.out.println( "IFACE ==> " + ifname );
            
            ifaces.append( typeToString( ifname ) );
        }
        
        String isFinal = clazz.isFinal ? " final" : "";
        String nature  = clazz.isInterface ? " interface" : " class";
        String name    = new ObjectType( typeToString( clazz.name ) ).simpleName;
        String supername = (clazz.superclass == null) ? 
                              "" :
                              " extends " + typeToString( clazz.superclass );
        
        out.println( "//---------------------------------------------------------------------------" );
        out.println( "// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST" );
        out.println( "//---------------------------------------------------------------------------" );
        out.println( "package " + clazz.name.namespace.name + ";" );
        out.println();
        out.println( "import org.epistem.j2avm.annotations.runtime.*;" );
        out.println();
        out.println( "@FlashNativeClass" );
        out.println( "public" + isFinal + nature + " " + name + supername + ifaces + " {" );
        out.indent();
    }
    
    /**
     * The template for the end of a class
     */
    private void templateEndClass() {
        out.unindent();
        out.println( "}" );
    }

}
