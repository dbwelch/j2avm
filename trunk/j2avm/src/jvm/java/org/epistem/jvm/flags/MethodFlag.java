package org.epistem.jvm.flags;

import org.epistem.util.Flag;

/**
 * The flags that apply to methods
 */
public enum MethodFlag {
    
    @Flag( 0x0001 ) MethodIsPublic       ,
    @Flag( 0x0002 ) MethodIsPrivate      ,
    @Flag( 0x0004 ) MethodIsProtected    ,
    @Flag( 0x0008 ) MethodIsStatic       ,
    @Flag( 0x0010 ) MethodIsFinal        ,
    @Flag( 0x0020 ) MethodIsSynchronized ,
    @Flag( 0x0040 ) MethodIsBridge       ,
    @Flag( 0x0080 ) MethodHasVarArgs     ,
    @Flag( 0x0100 ) MethodIsNative       ,
    @Flag( 0x0400 ) MethodIsAbstract     ,
    @Flag( 0x0800 ) MethodIsStrictFP     ,
    @Flag( 0x1000 ) MethodIsSynthetic    ;        
}