package org.epistem.j2avm.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.epistem.io.IndentingPrintWriter;

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

    private static final AVM2Namespace BUILT_IN = 
        new AVM2Namespace( NamespaceKind.Namespace, "http://adobe.com/AS3/2006/builtin" );
    
    private IndentingPrintWriter out;
       
    private Collection<AVM2ABCFile> abcFiles;
    
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
        
        Map<AVM2Namespace,Collection<AVM2MethodSlot>> functions = 
            new HashMap<AVM2Namespace, Collection<AVM2MethodSlot>>();
        
        //process each ABC block and generate the classes
        for( AVM2ABCFile abcFile : abcFiles ) {        
            for( AVM2Class clazz : abcFile.classes.values() ) {
                String filename = clazz.name.name + ".java";
                File pkgDir = null;
                if( clazz.name.namespace.name.length() == 0 ) {
                    pkgDir = new File( dir, "flash" );
                    filename = "Flash" + filename;
                }
                else {
                   pkgDir = new File( dir, clazz.name.namespace.name.replace( '.', '/' ) );
                }
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
            
            //gather the stand-alone functions by package (they may be spread
            // across different ABC blocks)
            for( AVM2Script script : abcFile.scripts ) {
                for( AVM2Trait trait : script.traits.traits ) { 
                    if( trait instanceof AVM2MethodSlot ){
                        AVM2MethodSlot fn = (AVM2MethodSlot) trait;
                        AVM2Namespace ns = fn.name.namespace;
                        if( ns.kind == NamespaceKind.PackageNamespace ) {
                            Collection<AVM2MethodSlot> fns = functions.get( ns );
                            if( fns == null ) {
                                functions.put( ns, fns = new ArrayList<AVM2MethodSlot>() );
                            }
                            fns.add( fn );
                        }                                
                    }                    
                }
            }
        }
        
        //generate the function holders
        AVM2Name superclass = new AVM2QName( "flash.FlashObject" );
        for( AVM2Namespace ns : functions.keySet() ) {
            
            AVM2QName className;
            if( ns.equals( AVM2Namespace.publicNamespace ) ) {
                className = new AVM2QName( "flash.toplevel_functions" );
            }
            else {
                className = new AVM2QName( ns.name + "_functions" );                
            }
            
            String filename = className.name + ".java";
            File pkgDir = new File( dir, className.namespace.name.replace( '.', '/' ) );
            pkgDir.mkdirs();
            
            System.out.println( "Generating scripts for " + className );
            
            FileWriter fw = new FileWriter( new File( pkgDir, filename ) );
            try {    
                out = new IndentingPrintWriter( fw );
                templateStartClass( className, true, false, superclass, null );
    
                //private constructor to prevent instantiation
                out.println( "\nprivate " + className.name + "() {}" ); 
    
                for( AVM2MethodSlot func : functions.get( ns ) ) {
                    String annotation = ns.name;
                    if( annotation.length() > 0 ) annotation += ".";
                    annotation = "@Function(\"" + annotation + func.name.name + "\")";
                    
                    templateMethod( func.name.name, 
                                    func.method.returnType,
                                    func.method.paramTypes,
                                    func.method.paramNames,
                                    func.method.defaultValues.size(),
                                    true, false, false, true, 
                                    false, false, false,
                                    annotation );                    
                }
                
                templateEndClass();
            } 
            finally {
                out.flush();
                fw.close();
            }
        }
    }
    
    /**
     * Generate the Java source for a class
     */
    private void generateClass( AVM2Class clazz ) {
        System.out.println( "Generating " + clazz.name );
                
        templateStartClass( clazz.name, clazz.isFinal, clazz.isInterface,
                            clazz.superclass, clazz.interfaces );
            
        generateConstructors:
        if( ! clazz.isInterface ) {
            AVM2Method cons = clazz.constructor;
    
            String pkg = clazz.name.namespace.name;
            String name    = clazz.name.name;
            if( pkg.length() == 0 ) name = "Flash" + name;
        
            if( name.equals( "FlashDate" ) ) {
                generateDateConstructors();                
                break generateConstructors;
            }
            
            templateMethod( name, null, cons.paramTypes, 
                            cons.paramNames, cons.defaultValues.size(),
                            false, false, false, false, false, true, false, null );
            
            //cheat - create a no-arg protected constructor so that we don't have
            // to make a valid super call in the sub-class constructors
            if( cons.paramTypes.size() > 0 
             && cons.defaultValues.size() < cons.paramTypes.size() ) {
                out.println();
                out.println( "/** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */" );
                out.println( "protected " + name + "() {}" );
            }
        }
        
        for( AVM2Trait trait : clazz.staticTraits.traits ) {
            processTrait( trait, clazz, true );            
        }
        
        for( AVM2Trait trait : clazz.traits.traits ) {
            processTrait( trait, clazz, false );
        }
        
        templateEndClass();
    }
     
    //Date is a special case
    private void generateDateConstructors() {
        out.println( "public FlashDate() {}" );
        out.println( "public FlashDate( double millis ) {}" );
        out.println( "public FlashDate( String date ) {}" );
        out.println( "public FlashDate( double year, double month, double date, double hour, double minute, double second, double millisecond ) {}" );
        out.println( "public FlashDate( double year, double month, double date, double hour, double minute, double second ) {}" );
        out.println( "public FlashDate( double year, double month, double date, double hour, double minute ) {}" );
        out.println( "public FlashDate( double year, double month, double date, double hour ) {}" );
        out.println( "public FlashDate( double year, double month, double date ) {}" );
        out.println( "public FlashDate( double year, double month ) {}" );
    }
    
    private void processTrait( AVM2Trait trait, AVM2Class clazz, boolean isStatic ) {
        
        boolean isDate = clazz.name.name.equals( "Date" ) 
                      && clazz.name.namespace.equals( AVM2Namespace.publicNamespace );
        
        AVM2Namespace ns = new AVM2Namespace( NamespaceKind.Namespace, 
                                              clazz.name.namespace.name + ":" + clazz.name.name );
        
        boolean isPublic = trait.name.namespace.equals( AVM2Namespace.publicNamespace )
                        || trait.name.namespace.equals( BUILT_IN )
                        || trait.name.namespace.equals( ns ) ;
        boolean isProtected = trait.name.namespace.kind == NamespaceKind.ProtectedNamespace;
        if( ! ( isProtected || isPublic )) return;
        
        boolean isFinal = false;
        boolean isOverride = false;
        if( trait instanceof AVM2MethodSlot ) {
            isFinal    = ((AVM2MethodSlot) trait).isFinal;
            isOverride = ((AVM2MethodSlot) trait).isOverride;
        }
        
        //System.out.println( clazz.name.toQualString() );
        
        if( trait instanceof AVM2Getter ) {
            if( isDate ) return; //don't generate accessors for Date since it also specifies them explicitly
            
            AVM2Getter getter = (AVM2Getter) trait;
            String   name = getter.name.name;
            AVM2Name type = getter.getMethod().returnType;

            templateGetter( name, type, isStatic, isProtected, isFinal, isOverride, clazz.isInterface );                
        }
        else if( trait instanceof AVM2Setter ) {
            if( isDate ) return; //don't generate accessors for Date since it also specifies them explicitly
            
            AVM2Setter setter = (AVM2Setter) trait;
            String   name = setter.name.name;
            AVM2Name type = setter.getMethod().paramTypes.get( 0 );

            List<String> pnames = setter.method.paramNames;
            String pname = (pnames != null && ! pnames.isEmpty()) ? pnames.get( 0 ) : name;
            
            templateSetter( name, type, pname, isStatic, isProtected, isFinal, isOverride, clazz.isInterface );
        }
        else if( trait instanceof AVM2MethodSlot ) {
            AVM2MethodSlot methodSlot = (AVM2MethodSlot) trait;
            AVM2Method     method     = methodSlot.method;
            
            String name = trait.name.name; 
            
            templateMethod( name, method.returnType, method.paramTypes, 
                            method.paramNames, method.defaultValues.size(),
                            isStatic, isProtected, methodSlot.isFinal, 
                            ! clazz.isInterface, methodSlot.isOverride, 
                            false, clazz.isInterface, null );
        }
        else if( trait instanceof AVM2Slot ) {
            if( isDate ) return; //don't generate accessors for Date since it also specifies them explicitly
            
            AVM2Slot slot = (AVM2Slot) trait;
            String   name = slot.name.name;
            AVM2Name type = slot.type;

            if( isStatic & slot.isConstant & slot.value != null ) {
                templateConstant( name, type, slot.value, isProtected );
                return;
            }
            
            templateGetter( name, type, isStatic, isProtected, isFinal, isOverride, clazz.isInterface );
            templateSetter( name, type, name, isStatic, isProtected, isFinal, isOverride, clazz.isInterface );
        }
    }
    
    /**
     * Template for a static constant
     */
    private void templateConstant( String name, AVM2Name type, 
                                   AVM2DefaultValue value, boolean isProtected ) {

        if( name.equals( "MAX_VALUE" ) && type.name.equals( "uint" )) {
            out.println();
            out.print( "public static final long " + name + " = " );
            out.print( ((Number) value.value ).longValue() );
            out.println( "L;" );
            return;
        }
        
        String visibility = isProtected ? "protected" : "public"; 
        out.println();
        out.print( visibility + " static final " + typeToString( type ) 
                     + " " + name + " = " );
        
        switch( value.valueKind ) {
            case CONSTANT_Utf8:   out.writeDoubleQuotedString( (String) value.value ); break;     
            case CONSTANT_Int:    out.print( value.value ); break;
            case CONSTANT_UInt:   out.print( value.value ); break;
            case CONSTANT_Double: out.print( numStr( value.value )); break;
            case CONSTANT_False:  out.print( "false" ); break;
            case CONSTANT_True:   out.print( "true" ); break;
            case CONSTANT_Null:   out.print( "null" ); break;
        }
        out.println( ";" );
    }
    
    private String numStr( Object value ) {
        String valueStr = value.toString();
        
        if( valueStr.equals( "Infinity"  ) ) return "Double.POSITIVE_INFINITY";
        if( valueStr.equals( "-Infinity" ) ) return "Double.NEGATIVE_INFINITY";
        if( valueStr.equals( "NaN"       ) ) return "Double.NaN";

        return valueStr;
    }
    
    /**
     * Make a Java type name from an AVM2 type
     */
    private String typeToString( AVM2Name type ) {
        
        if( type == null ) return "Object"; //any
        
        if( type instanceof AVM2GenericName ) {
            AVM2GenericName gen = (AVM2GenericName) type;
            return typeToString( gen.typeParams[0] ) + "[]";
        }
        
        String pack = null;
        
        if( type.namespace == null ) {
            List<AVM2Namespace> nss = new ArrayList<AVM2Namespace>( type.namespaceSet );
            Collections.reverse( nss );
            for( AVM2Namespace ns : nss ) {
                if( ns.kind == NamespaceKind.PackageNamespace && ns.name.length() > 0 ) {
                    pack = ns.name;
                    break;
                }
            }
        }
        else {
            pack = type.namespace.name;
        }
        
        String name = type.name;
        
        //System.out.println( type );
        if( pack.length() != 0 ) return pack + "." + name;
        
        if( name.equals( "void"     ) ) return "void";
        if( name.equals( "int"      ) ) return "int";
        if( name.equals( "uint"     ) ) return "int";
        if( name.equals( "String"   ) ) return "String";
        if( name.equals( "Number"   ) ) return "double";
        if( name.equals( "Boolean"  ) ) return "boolean";

        return "flash.Flash" + name;
    }


    /**
     * Template for a method
     */
    private void templateMethod( String name, AVM2Name returnType, 
                                 List<AVM2Name> paramTypes,
                                 List<String>   paramNames,
                                 int optionalParamCount,
                                 boolean isStatic, boolean isProtected , 
                                 boolean isFinal, boolean isNative, boolean isOverride,
                                 boolean isConstructor,
                                 boolean isInterface,
                                 String annotation ) {

        String visibility = isProtected ? "protected" : "public";
        String strRetType = isConstructor ? "" : typeToString( returnType );
        String strStatic  = isStatic ? " static" : "";
        String strFinal   = isFinal  ? " final" : "";
        String strNative  = isNative ? " native" : "";
        String body = ( isNative || isInterface ) ? ";" : " {}";
        
        if( paramTypes == null ) paramTypes = Collections.emptyList();
        if( paramNames == null ) paramNames = Collections.emptyList();
        
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
            if( isOverride ) out.println( "@Override" );
            if( annotation != null ) out.println( annotation );
            out.println( visibility + strStatic + strFinal + strNative 
                         + " " + strRetType + " " + name + "(" + paramStrings[i] + ")" + body );            
        }
    }


    /**
     * Generate a setter method
     * @param name the property name
     * @param type the property type
     */
    private void templateSetter( String name, AVM2Name type, String paramName, boolean isStatic, 
                                 boolean isProtected, boolean isFinal, boolean isOverride, boolean isInterface ) {
        
        if( name.length() == 1 ) name = name.toUpperCase();
        else name = name.substring( 0, 1 ).toUpperCase() + name.substring( 1 );
        
        name = "set" + name;
        List<AVM2Name> params = Collections.singletonList( type );
        List<String>   pnames = Collections.singletonList( paramName );
        
        templateMethod( name, AVM2StandardName.TypeVoid.qname, 
                        params, pnames, 0, isStatic, 
                        isProtected, isFinal, ! isInterface, 
                        isOverride, false, isInterface, "@Setter" );
    }

    /**
     * Generate a getter method
     * @param name the property name
     * @param type the property type
     */
    private void templateGetter( String name, AVM2Name type, boolean isStatic, 
                                 boolean isProtected, boolean isFinal, boolean isOverride, boolean isInterface ) {
        
        if( name.length() == 1 ) name = name.toUpperCase();
        else name = name.substring( 0, 1 ).toUpperCase() + name.substring( 1 );
        
        name = "get" + name;
        
        templateMethod( name, type, null, null, 0, isStatic, 
                        isProtected, isFinal, ! isInterface, 
                        isOverride, false, isInterface, "@Getter" );
    }
    
    /**
     * The template for the start of a class
     */
    private void templateStartClass( AVM2QName className,
                                     boolean isFinal, boolean isInterface,
                                     AVM2Name superclass,
                                     Collection<AVM2Name> interfaces ) {
        
        StringBuilder ifaces = new StringBuilder();
        if( interfaces != null ) {
            boolean first = true;
            for( AVM2Name ifname : interfaces ) {
                if( first ) {
                    ifaces.append( " implements " );
                    first = false;
                }
                else ifaces.append( "," );
                
                ifaces.append( typeToString( ifname ) );
            }
        }
        
        String pkg = className.namespace.name;
        
        String finalStr  = isFinal ? " final" : "";
        String nature    = isInterface ? " interface" : " class";
        String name      = className.name;
        String supername = (superclass == null) ? 
                               "" :
                               " extends " + typeToString( superclass );

        if( pkg.length() == 0 ) {
            pkg = "flash";
            name = "Flash" + name;
        }

        out.println( "//---------------------------------------------------------------------------" );
        out.println( "// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST" );
        out.println( "//---------------------------------------------------------------------------" );
        out.println( "package " + pkg + ";" );
        out.println();
        out.println( "import org.epistem.j2avm.annotations.runtime.*;" );
        out.println();
        out.println( "@FlashNativeClass" );
        out.println( "public" + finalStr + nature + " " + name + supername + ifaces + " {" );
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
