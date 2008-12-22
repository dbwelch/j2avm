package org.epistem.jvm.flags;

import org.epistem.util.Flag;

/**
 * The common flags that apply to fields and methods
 */
public enum MemberFlag {
    
    @Flag( 0x0001 ) MemberIsPublic   ,
    @Flag( 0x0002 ) MemberIsPrivate  ,
    @Flag( 0x0004 ) MemberIsProtected,
    @Flag( 0x0008 ) MemberIsStatic   ,
    @Flag( 0x0010 ) MemberIsFinal    ,
    @Flag( 0x1000 ) MemberIsSynthetic;
}