//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.external;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class ExternalInterface extends flash.FlashObject {

    public  ExternalInterface( ) {}

    public static final native void addCallback( String functionName, flash.FlashFunction closure );

    @Getter
    public static final native boolean getAvailable( );

    @Getter
    public static final native String getObjectID( );

    public static final native Object call( String functionName );

    @Getter
    public static native boolean getMarshallExceptions( );

    @Setter
    public static native void setMarshallExceptions( boolean setMarshallExceptions );
}
