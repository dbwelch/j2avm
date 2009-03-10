package org.epistem.jvm.attributes;

import java.io.DataInput;
import java.io.IOException;
import java.util.Map;

import org.epistem.jvm.JVMAttribute;
import org.epistem.jvm.JVMClassLoader;
import org.epistem.jvm.io.ConstantPool;
import org.epistem.jvm.type.JVMType;
import org.epistem.jvm.type.ObjectType;

/**
 * Base for annotation attributes
 *
 * @author nickmain
 */
public abstract class AnnotationAttribute extends JVMAttribute {

    /** Whether the annotation is visible via the reflection API */
    public final boolean isRuntimeVisible;    
    
    protected AnnotationAttribute( JVMAttribute.Name name, boolean isVisible ) {
        super( name.name() );
        this.isRuntimeVisible = isVisible;
    }
    
    /** Parse the annotations */
    protected void parseAnnotations( Map<String,JavaAnnotation> annotations, ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        int numAnnos = in.readUnsignedShort();
        
        for (int i = 0; i < numAnnos; i++) {
            JavaAnnotation anno = parseAnnotation( pool, loader, in );
            annotations.put( anno.type.name, anno );
        }  
    }
    
    /** Parse a single annotation */
    private static JavaAnnotation parseAnnotation( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {

        int typeIndex = in.readUnsignedShort();
        int numPairs  = in.readUnsignedShort();
        
        String typeName = pool.getUTF8Value( typeIndex );
        typeName = ConstantPool.decodeTypeName( pool.getUTF8Value(typeIndex));
        
        JavaAnnotation anno = new JavaAnnotation( ObjectType.fromName( typeName )); 
        
        for (int p = 0; p < numPairs; p++) {
            int nameIndex = in.readUnsignedShort();
            String name   = pool.getUTF8Value( nameIndex );

            JavaAnnotation.Value value = parseValue( pool, loader, in );
            anno.values.put( name, value );             
        }

        return anno;
    }
    
    /** Parse an annotation value */
    public static JavaAnnotation.Value parseValue( ConstantPool pool, JVMClassLoader loader, DataInput in ) throws IOException {
        JavaAnnotation.Value value = null;
        
        int tag = in.readUnsignedByte();
        switch( tag ) {             
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z': {
                int constIndex = in.readUnsignedShort();
                value = new JavaAnnotation.IntegerValue( ((Integer) pool.getConstant( constIndex )).intValue() );
                break;
            }              
                
            case 'D': {
                int constIndex = in.readUnsignedShort();
                value = new JavaAnnotation.DoubleValue( ((Double) pool.getConstant( constIndex )).doubleValue() );
                break;
            }         
            
            case 'F': {
                int constIndex = in.readUnsignedShort();
                value = new JavaAnnotation.FloatValue( ((Float) pool.getConstant( constIndex )).floatValue() );
                break;
            }         
            
            case 'J': {
                int constIndex = in.readUnsignedShort();
                value = new JavaAnnotation.LongValue( ((Long) pool.getConstant( constIndex )).longValue() );
                break;
            }         
            
            case 's': {
                int constIndex = in.readUnsignedShort();
                value = new JavaAnnotation.StringValue( pool.getUTF8Value( constIndex ));
                break;
            }
            
            case 'e': {
                int classIdx = in.readUnsignedShort();
                int enumIdx  = in.readUnsignedShort();

                String className = ConstantPool.decodeTypeName( pool.getUTF8Value(classIdx));
                
                value = new JavaAnnotation.EnumValue( JVMType.fromName( className ), 
                                                      pool.getUTF8Value( enumIdx ) );
                
                break;
            }
            
            case 'c': {
                int classIdx = in.readUnsignedShort();
                String className = ConstantPool.decodeTypeName( pool.getUTF8Value( classIdx ));
                value = new JavaAnnotation.ClassValue( JVMType.fromName( className ));
                
                break;
            }
            
            case '@': {
                value = new JavaAnnotation.AnnotationValue( parseAnnotation( pool, loader, in) );
                break;
            }
            
            case '[': {
                int length = in.readUnsignedShort();
                JavaAnnotation.Value[] values = new JavaAnnotation.Value[ length ];
                value = new JavaAnnotation.ArrayValue( values );
                
                for (int i = 0; i < values.length; i++) {
                    values[i] = parseValue( pool, loader, in );
                }
                
                break;
            }
            
            default: break;
        }
        
        return value;
    }
}
