package org.epistem.jvm.io.parser;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.epistem.jvm.*;
import org.epistem.jvm.attributes.UnknownAttribute;
import org.epistem.jvm.flags.ClassFlag;
import org.epistem.jvm.flags.FieldFlag;
import org.epistem.jvm.flags.MethodFlag;
import org.epistem.jvm.io.ConstantPool;
import org.epistem.jvm.io.ConstantPoolType;
import org.epistem.jvm.io.ConstantPool.UTF8Entry;
import org.epistem.jvm.type.JVMType;
import org.epistem.jvm.type.ObjectType;
import org.epistem.jvm.type.Signature;
import org.epistem.jvm.type.ValueType;
import org.epistem.util.FlagIO;

/**
 * Yet another Class File parser.
 * 
 * @author nickmain
 */
public class ClassFileParser {

    private final DataInput in;
    private final ConstantPool pool = new ConstantPool();
    private final JVMClassLoader loader; 
    private JVMClass jclass;
    
    /**
     * @param in the raw class data
     * @param loader the loader for the class
     */
    public ClassFileParser( DataInput in, JVMClassLoader loader ) {
        this.in     = in;
        this.loader = loader; 
    }
    
    /**
     * Parse the class.
     */
    public JVMClass parseClass() throws IOException {
        
        if( jclass != null ) return jclass;
        
        try {            
            in.readInt(); //magic 
            int minorVersion = in.readShort();
            int majorVersion = in.readShort();

            parseConstantPool();
            
            int flags      = in.readShort();
            int thisclass  = in.readShort();

            String className  = pool.getClassName( thisclass );            
            
            Collection<ClassFlag> flagSet = FlagIO.parse( ClassFlag.class, flags );            
            
            int superclass = in.readShort();
            String superName = (superclass != 0) ? pool.getClassName( superclass ) : null;
            
            jclass = new JVMClass( ObjectType.fromName( className ), 
                                   loader,
                                   (superName != null) ? ObjectType.fromName( superName ) : null,
                                   majorVersion, minorVersion );

            jclass.flags.addAll( flagSet );
            
            //interfaces
            int ifcount  = in.readUnsignedShort();
            for (int i = 0; i < ifcount; i++) {
                int index = in.readShort();                
                String ifaceName = pool.getClassName( index );
                jclass.interfaces.add( ObjectType.fromName( ifaceName ) );
            }

            //fields
            int fieldcount = in.readUnsignedShort();
            for (int i = 0; i < fieldcount; i++) {
                jclass.fields.add( parseField() );
            }
            
            //methods
            int methodcount = in.readUnsignedShort();
            for (int i = 0; i < methodcount; i++) {
                jclass.methods.add( parseMethod() );
            }
            
            //attributes
            int numAttrs = in.readUnsignedShort();            
            for (int i = 0; i < numAttrs; i++) {
                JVMAttribute attr = parseAttribute();
                jclass.attributes.byName.put( attr.name, attr );
            }
            
        } finally {
            if( in instanceof DataInputStream ) {
                ((DataInputStream) in).close();
            }            
        }
        
        return jclass;
    }
    
    private JVMField parseField() throws IOException {
        
        int flags   = in.readUnsignedShort();
        int nameIdx = in.readUnsignedShort();
        int typeIdx = in.readUnsignedShort();
        
        String name = pool.getUTF8Value( nameIdx );
        String typeName = pool.getTypeName(typeIdx);
        
        ValueType type = ValueType.fromName( typeName );
                
        JVMField field = new JVMField( jclass, name, type );
        field.flags.addAll( FlagIO.parse( FieldFlag.class, flags ) );
        
        int numAttrs = in.readUnsignedShort();       
        for (int i = 0; i < numAttrs; i++) {
            JVMAttribute attr = parseAttribute();
            field.attributes.byName.put( attr.name, attr );
        }
        
        return field;
    }
    
    private JVMMethod parseMethod() throws IOException {
        int flags   = in.readUnsignedShort();
        int nameIdx = in.readUnsignedShort();

        String name = pool.getUTF8Value( nameIdx );
            
        int sigIdx = in.readUnsignedShort();
        String sig = pool.getUTF8Value( sigIdx );
        
        String rt = sig.substring( sig.indexOf(")") + 1 );
        JVMType retType = JVMType.fromName( ConstantPool.decodeTypeName( rt ) );
        
        sig = sig.substring(1,sig.indexOf(")") );        
        String[] ptypes = ConstantPool.readTypes( sig );
        
        ValueType[] params = new ValueType[ ptypes.length ];
        for (int p = 0; p < params.length; p++) {
            params[p] = ValueType.fromName( ptypes[p] );
        }

        JVMMethod method = new JVMMethod( jclass, new Signature( name, params ), retType );
        method.flags.addAll( FlagIO.parse( MethodFlag.class, flags ) );
        
        int numAttrs = in.readUnsignedShort();       
        for (int i = 0; i < numAttrs; i++) {
            JVMAttribute attr = parseAttribute();
            method.attributes.byName.put( attr.name, attr );
        }
        
        return method;
    }
  
    private JVMAttribute parseAttribute() throws IOException {
        return parseAttribute( in, loader,  pool );
    }
    
    public static JVMAttribute parseAttribute( DataInput in, 
                                                JVMClassLoader loader, 
                                                ConstantPool pool ) throws IOException {
        
        int    nameIndex = in.readUnsignedShort();
        int    dataSize  = in.readInt();
        
        if( ! (pool.getEntry( nameIndex ) instanceof UTF8Entry) ) {
            System.err.println( "OOPS --> " + nameIndex );
        }
        
        String attrName  = pool.getUTF8Value( nameIndex );
        
        byte[] data = new byte[ dataSize ];
        in.readFully( data );        
        
        JVMAttribute attr;
        try {
            JVMAttribute.Name name = JVMAttribute.Name.valueOf( attrName );
            Class<? extends JVMAttribute> attrClass = name.attributeClass;
            DataInputStream dataIn = new DataInputStream( new ByteArrayInputStream( data ));
            
            attr = (JVMAttribute) attrClass.getMethod( "parse", ConstantPool.class, JVMClassLoader.class, DataInput.class )
                                        .invoke( null, pool, loader, dataIn );
            
        } catch( InvocationTargetException itex ) {
            if( itex.getCause() instanceof IOException ) throw (IOException) itex.getCause();            
            if( itex.getCause() instanceof RuntimeException ) throw (RuntimeException) itex.getCause();            
            
            throw new RuntimeException( itex );
            
        } catch( Exception ex ) {                   
            attr = new UnknownAttribute( attrName, data );
        }
        
        return attr;
    }
       
    private void parseConstantPool() throws IOException {
        int cpcount = in.readShort();
        int cpIndex = 1;
        
        while( cpIndex < cpcount ) {
        
            int tag = in.readUnsignedByte();        
            ConstantPoolType type = ConstantPoolType.valueToType.get( tag );
            
            if( type == null )  {
                throw new IOException( "Unknown CP tag: " + tag );
            }
            
            switch( type ) {
                case CPool_Utf8 :
                    {
                        int size = in.readShort();
                        byte[] chars = new byte[size];
                        in.readFully( chars );
                        
                        String utf8 = new String(chars, "UTF-8");
                        cpIndex++;                        
                        pool.appendUTF8(utf8);
                        break;
                    }
    
                case CPool_Integer :
                    {
                        int value = in.readInt();
                        cpIndex++;
                        pool.appendInt(value);
                        break;
                    }
    
                case CPool_Float :
                    {
                        float value = in.readFloat();
                        cpIndex++;
                        pool.appendFloat(value);
                        break;
                    }
    
                case CPool_Long :
                    {
                        long value = in.readLong();
                        cpIndex += 2;
                        pool.appendLong(value);
                        break;
                    }
    
                case CPool_Double :
                    {
                        double value = in.readDouble();
                        cpIndex += 2;
                        pool.appendDouble(value);
                        break;
                    }
    
                case CPool_Class :
                    {
                        int index = in.readShort();
                        cpIndex++;
                        pool.appendClass(index);
                        break;
                    }
    
                case CPool_String :
                    {
                        int index = in.readShort();
                        cpIndex++;
                        pool.appendString(index);
                        break;
                    }
    
                case CPool_Fieldref :
                    {
                        int classIndex = in.readShort();
                        int nameIndex = in.readShort();
                        cpIndex++;
                        pool.appendField(classIndex, nameIndex);
                        break;
                    }
    
                case CPool_Methodref :
                    {
                        int classIndex = in.readShort();
                        int nameIndex = in.readShort();
                        cpIndex++;
                        pool.appendMethod(classIndex, nameIndex);
                        break;
                    }
    
                case CPool_InterfaceMethodref :
                    {
                        int classIndex = in.readShort();
                        int nameIndex = in.readShort();
                        cpIndex++;
                        pool.appendInterfaceMethod(classIndex, nameIndex);
                        break;
                    }
    
                case CPool_NameAndType :
                    {
                        int nameIndex = in.readShort();
                        int typeIndex = in.readShort();
                        cpIndex++;
                        pool.appendNameType(nameIndex, typeIndex);
                        break;
                    }
            }
        }
    }
}
