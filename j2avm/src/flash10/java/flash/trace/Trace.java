//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.trace;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class Trace extends flash.FlashObject {

    public  Trace( ) {}

    public static final int METHODS_AND_LINES_WITH_ARGS = 4;

    public static final int METHODS_AND_LINES = 3;

    public static final native flash.FlashFunction getListener( );

    public static final native int getLevel( int target );

    public static final native int getLevel( );

    public static final int OFF = 0;

    public static final int METHODS_WITH_ARGS = 2;

    public static final native Object setLevel( int l, int target );

    public static final native Object setLevel( int l );

    public static final int METHODS = 1;

    public static final native Object setListener( flash.FlashFunction f );

    public static final Object LISTENER = 2;

    public static final Object FILE = 1;
}
