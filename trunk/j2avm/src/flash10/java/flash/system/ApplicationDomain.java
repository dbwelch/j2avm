//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.system;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class ApplicationDomain extends flash.FlashObject {

    @Getter
    public static final native flash.system.ApplicationDomain getCurrentDomain( );

    @Getter
    public static final native int getMIN_DOMAIN_MEMORY_LENGTH( );

    @Getter
    public native flash.utils.ByteArray getDomainMemory( );

    public native flash.FlashObject getDefinition( String name );

    @Setter
    public native void setDomainMemory( flash.utils.ByteArray setDomainMemory );

    public native boolean hasDefinition( String name );

    @Getter
    public native flash.system.ApplicationDomain getParentDomain( );
}
