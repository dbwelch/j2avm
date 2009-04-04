//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.sampler;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class StackFrame extends flash.FlashObject {

    public  StackFrame( ) {}

    public native String toString( );

    @Getter
    public native int getLine( );

    @Setter
    public native void setLine( int line );

    @Getter
    public native String getName( );

    @Setter
    public native void setName( String name );

    @Getter
    public native String getFile( );

    @Setter
    public native void setFile( String file );
}
