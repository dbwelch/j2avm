package org.epistem.j2avm.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.epistem.io.IndentingPrintWriter;
import org.epistem.util.FreeMindWriter;
import org.epistem.util.FreeMindWriter.Posn;

import com.anotherbigidea.flash.avm2.MethodInfoFlags;
import com.anotherbigidea.flash.avm2.instruction.Instruction;
import com.anotherbigidea.flash.avm2.instruction.InstructionTarget;
import com.anotherbigidea.flash.avm2.model.*;

/**
 * Utility that dumps an ABC file as a FreeMind mindmap
 *
 * @author nickmain
 */
public class ABCMindMapDumper {

    private FreeMindWriter out;
    
    /**
     * @param file the target file
     */
    public ABCMindMapDumper( String file ) throws IOException {
        out = new FreeMindWriter( file );
    }
    
    /**
     * Dump an ABC file/block
     */
    public void dump( AVM2ABCFile abc ) {
        out.startNode().text( "ABC" );
        
        out.startNode().text( "classes" ).folded().position( Posn.RIGHT );
        
        //build package tree
        Map<String, Object> classes = new HashMap<String, Object>();
        for( AVM2Class  c : abc.classes.values() ) {
            String pkg = c.name.namespace.name;
            String[] segs = pkg.split( "\\." );
            
            Map<String, Object> map = classes;
            for( String seg : segs ) {
                Object obj = map.get( seg );
                if( obj == null ) {
                    map.put( seg, obj = new HashMap<String, Object>() );
                }
                
                map = (Map<String, Object>) obj;
            }
            
            map.put( c.name.name, c );
        }

        dumpPkg( classes );
        out.endNode();
        
        out.startNode().text( "scripts" ).folded().position( Posn.LEFT );
        for( AVM2Script s : abc.scripts ) dumpScript( s );
        out.endNode();
        
        out.startNode().text( "methods" ).folded().position( Posn.LEFT );
        for( AVM2Method m : abc.methods ) {
            out.startNode().text( "method" ).backColor( 0xffe0e0 ).folded();
            dumpMethod( m );
            out.endNode();
        }
        out.endNode();
        
        out.endNode();
        out.close();
    }
    
    private void dumpPkg( Map<String,Object> pkg ) {
        for( String name : pkg.keySet() ) {
            Object obj = pkg.get( name );
            
            if( obj instanceof Map ) {
                out.startNode().text( name ).folded();
                dumpPkg( (Map<String,Object>) obj );
                out.endNode();
            }
            else {
                dumpClass( (AVM2Class) obj );
            }            
        }
    }
    
    private void dumpClass( AVM2Class c ) {
        out.startNode().text( c.name.namespace.name + "." + c.name.name ).folded();

        out.attribute( c.isInterface ? "interface" : "class", " " );
        
        if( c.superclass != null ) {
            out.attribute( "extends", c.superclass.toString() );
        }

        for( AVM2Name iface : c.interfaces ) {
            out.attribute( "implements", iface.toString() );
        }  

        out.attribute( c.isSealed ? "sealed" : "dynamic", " " );

        if( c.isFinal ) out.attribute( "final", " " );
        
        //out.attribute( "index", "" + c.index );
        
        if( c.protectedNamespace != null ) {
            out.attribute( "prot-ns", dumpNS( c.protectedNamespace ));
        }

        out.startNode().text( "<static-init>" ).backColor( 0x808080 ).folded();
        dumpMethod( c.staticInitializer );
        out.endNode();

        out.startNode().text( "<constructor>" ).backColor( 0xA0A0A0 ).folded();
        dumpMethod( c.constructor );
        out.endNode();

        out.startNode().text( "static" ).folded();
        for( AVM2Trait trait : c.staticTraits.traits ) {
            dumpTrait( trait );
        }        
        out.endNode();

        out.startNode().text( "instance" ).folded();
        for( AVM2Trait trait : c.traits.traits ) {
            dumpTrait( trait );
        }        
        out.endNode();
        
        out.endNode();
    }

    private void dumpTrait( AVM2Trait trait ) {
        out.startNode().text( trait.name.name ).folded();
        out.attribute( "namespace", dumpNS( trait.name.namespace ) );
        if( trait.indexId >= 0 ) out.attribute( "index", "" + trait.indexId );
        
        if( trait instanceof AVM2MethodSlot ) {
            AVM2MethodSlot ms = (AVM2MethodSlot) trait;
            if( ms.isFinal    ) out.attribute( "final", " " );
            if( ms.isOverride ) out.attribute( "override", " " );

            if( trait instanceof AVM2Getter ) out.attribute( "getter", " " );
            if( trait instanceof AVM2Setter ) out.attribute( "setter", " " );

            dumpMethod( ms.method );
        }
        else if( trait instanceof AVM2Slot ) {
            AVM2Slot slot = (AVM2Slot) trait;
            
            String type = ( slot.type == null ) ? "*" : slot.type.toString();

            if( slot.isConstant ) out.attribute( "const", type );
            else                  out.attribute( "var", type );
            
            if( slot.value != null ) {
                out.attribute( "value", slot.value.toString() );
            }
        }
        else if( trait instanceof AVM2FunctionSlot ) {
            AVM2FunctionSlot func = (AVM2FunctionSlot) trait;
            out.attribute( "function", " " );
            dumpMethod( func.function );
        }
        else if( trait instanceof AVM2ClassSlot ) {
            out.attribute( "class", ((AVM2ClassSlot) trait).className.toQualString() );
        }
        
        if( ! trait.metadata.isEmpty() ) {
            out.startNode().text( "metadata" );
            for( AVM2Metadata md : trait.metadata ) {
                out.startNode().bubbleStyle();
                
                StringWriter sw = new StringWriter();
                IndentingPrintWriter ipw = new IndentingPrintWriter( sw );
                md.dump( ipw );
                ipw.flush();
                
                out.richText( "<pre>" + sw + "</pre>" );
                out.endNode();
            }
            out.endNode();
        }
        
        out.endNode();
    }
    
    private void dumpScript( AVM2Script s ) {
        out.startNode().text( "script" ).backColor( 0xe0e0ff ).folded();
        
        for( AVM2Trait trait : s.traits.traits ) {
            dumpTrait( trait );
        }

        out.startNode().text( "body" );
        dumpMethod( s.script );
        out.endNode();
        out.endNode();
    }
    
    //assumes that node is already open
    private void dumpMethod( AVM2Method m ) {
        out.attribute( "return-type", ( m.returnType == null ) ? "*" : m.returnType.toString() );

        for( int i = 0; i < m.paramTypes.size(); i++ ) {
            String paramName = "- param" + ( i + 1 );
                
            if( m.paramNames != null && i < m.paramNames.size() && m.paramNames.get( i ) != null ) {
                paramName = "- " + m.paramNames.get( i );
            }
            
            String paramType = "*";
            
            AVM2Name ptype = m.paramTypes.get( i );
            if( ptype != null ) {
                paramType = ptype.toString();
            }
            
            if( m.defaultValues != null 
             && i >= (m.paramTypes.size() - m.defaultValues.size()) ) {
                int idx = i - (m.paramTypes.size() - m.defaultValues.size());

                paramType += " = ";
                paramType += m.defaultValues.get( idx );
            }
        }
        
        out.attribute( "index", "" + m.index );        
        if( m.name != null ) out.attribute( "name", m.name );

        for( MethodInfoFlags mif : m.flags ) {
            out.attribute( mif.name(), " " );        
        }
                        
        dumpMethodBody( m.methodBody );        
    }
    
    private void dumpMethodBody( AVM2MethodBody body ) {
        out.startNode().text( "instructions" ).folded().cloud( 0xe0ffe0 );

        out.attribute( "max-stack"    , "" + body.maxStack );        
        out.attribute( "max-registers", "" + body.maxRegisters );        
        out.attribute( "max-scope"    , "" + body.maxScope );        
        out.attribute( "scope-depth"  , "" + body.scopeDepth );        
        
        Instruction in = body.instructions.first();
        boolean isTarget = false;
        while( in != null ) {
            out.startNode();
            
            if( in instanceof InstructionTarget ) {
                out.text( "" + ((InstructionTarget) in).label );
                out.backColor( 0xffff00 );
            } else {

                StringWriter sw = new StringWriter();
                IndentingPrintWriter ipw = new IndentingPrintWriter( sw );
                in.dump( ipw );
                ipw.flush();
                
                out.richPreText( sw.toString() );
            }
            
            out.endNode();
            
            in = in.next();
        }

        for( AVM2ExceptionHandler handler : body.exceptionHandlers ) {
            out.startNode();
            out.startRichText();
            handler.dump( out.out );
            out.endRichText();
            out.endNode();
        }
        
        for( AVM2Trait trait : body.activationTraits.traits ) {
            dumpTrait( trait );
        }

        out.endNode();        
    }
    
    private String dumpNS( AVM2Namespace ns ) {
        StringWriter sw = new StringWriter();
        IndentingPrintWriter ipw = new IndentingPrintWriter( sw );        
        ns.dump( ipw );
        ipw.flush();
        return sw.toString();
    }
}
