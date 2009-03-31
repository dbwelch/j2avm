//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.system;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class LoaderContext extends flash.FlashObject {

    public  LoaderContext( boolean checkPolicyFile, flash.system.ApplicationDomain applicationDomain, flash.system.SecurityDomain securityDomain ) {}

    public  LoaderContext( boolean checkPolicyFile, flash.system.ApplicationDomain applicationDomain ) {}

    public  LoaderContext( boolean checkPolicyFile ) {}

    public  LoaderContext( ) {}

    @Getter
    public native flash.system.ApplicationDomain getApplicationDomain( );

    @Setter
    public native void setApplicationDomain( flash.system.ApplicationDomain setApplicationDomain );

    @Getter
    public native boolean getCheckPolicyFile( );

    @Setter
    public native void setCheckPolicyFile( boolean setCheckPolicyFile );

    @Getter
    public native flash.system.SecurityDomain getSecurityDomain( );

    @Setter
    public native void setSecurityDomain( flash.system.SecurityDomain setSecurityDomain );
}
