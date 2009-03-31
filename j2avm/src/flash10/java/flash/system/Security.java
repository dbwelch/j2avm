//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.system;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class Security extends flash.FlashObject {

    public  Security( ) {}

    public static final native void showSettings( String panel );

    public static final native void showSettings( );

    @Getter
    public static final native String getSandboxType( );

    @Getter
    public static final native boolean getExactSettings( );

    public static final String LOCAL_TRUSTED = "localTrusted";

    @Setter
    public static final native void setExactSettings( boolean setExactSettings );

    public static final String REMOTE = "remote";

    public static final String LOCAL_WITH_FILE = "localWithFile";

    public static final native void allowDomain( );

    public static final native void allowInsecureDomain( );

    public static final native void loadPolicyFile( String url );

    @Setter
    public static final native void setDisableAVM1Loading( boolean setDisableAVM1Loading );

    public static final String LOCAL_WITH_NETWORK = "localWithNetwork";

    @Getter
    public static final native boolean getDisableAVM1Loading( );
}
