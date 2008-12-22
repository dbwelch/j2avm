package org.epistem.jvm.flags;

import org.epistem.util.Flag;

/**
 * The flags that apply to classes
 */
public enum ClassFlag {
    
    @Flag( 0x0001 ) IsPublic    ,
    @Flag( 0x0010 ) IsFinal     ,
    @Flag( 0x0020 ) Super       ,
    @Flag( 0x0200 ) IsInterface ,
    @Flag( 0x0400 ) IsAbstract  ,
    @Flag( 0x1000 ) IsSynthetic ,
    @Flag( 0x2000 ) IsAnnotation,
    @Flag( 0x4000 ) IsEnum      ;
}