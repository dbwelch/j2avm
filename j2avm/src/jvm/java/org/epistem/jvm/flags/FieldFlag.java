package org.epistem.jvm.flags;

import org.epistem.util.Flag;

/**
 * The flags that apply to fields
 */
public enum FieldFlag {
    
    @Flag( 0x0001 ) FieldIsPublic    ,
    @Flag( 0x0002 ) FieldIsPrivate   ,
    @Flag( 0x0004 ) FieldIsProtected ,
    @Flag( 0x0008 ) FieldIsStatic    ,
    @Flag( 0x0010 ) FieldIsFinal     ,
    @Flag( 0x0040 ) FieldIsVolatile  ,
    @Flag( 0x0080 ) FieldIsTransient ,
    @Flag( 0x1000 ) FieldIsSynthetic ,
    @Flag( 0x4000 ) FieldIsEnum      ;
}