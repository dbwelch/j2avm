//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.system;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class JPEGLoaderContext extends flash.system.LoaderContext {

    public  JPEGLoaderContext( double deblockingFilter, boolean checkPolicyFile, flash.system.ApplicationDomain applicationDomain, flash.system.SecurityDomain securityDomain ) {}

    public  JPEGLoaderContext( double deblockingFilter, boolean checkPolicyFile, flash.system.ApplicationDomain applicationDomain ) {}

    public  JPEGLoaderContext( double deblockingFilter, boolean checkPolicyFile ) {}

    public  JPEGLoaderContext( double deblockingFilter ) {}

    public  JPEGLoaderContext( ) {}

    @Getter
    public native double getDeblockingFilter( );

    @Setter
    public native void setDeblockingFilter( double deblockingFilter );
}
