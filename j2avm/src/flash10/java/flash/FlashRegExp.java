//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class FlashRegExp extends flash.FlashObject {

    public  FlashRegExp( Object pattern, Object options ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected FlashRegExp() {}

    public static final int length = 1;

    public native Object exec( String s );

    public native Object exec( );

    @Getter
    public native boolean getIgnoreCase( );

    @Getter
    public native boolean getGlobal( );

    @Setter
    public native void setLastIndex( int i );

    @Getter
    public native boolean getExtended( );

    public native boolean test( String s );

    public native boolean test( );

    @Getter
    public native String getSource( );

    @Getter
    public native int getLastIndex( );

    @Getter
    public native boolean getMultiline( );

    @Getter
    public native boolean getDotall( );
}
