//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class FlashError extends flash.FlashObject {

    public  FlashError( Object message, Object id ) {}

    public  FlashError( Object message ) {}

    public  FlashError( ) {}

    public static final native Object throwError( flash.FlashClass type, int index );

    public static final native String getErrorMessage( int index );

    public static final int length = 1;

    public native String getStackTrace( );

    @Getter
    public native int getErrorID( );

    @Getter
    public native Object getMessage( );

    @Setter
    public native void setMessage( Object message );

    @Getter
    public native Object getName( );

    @Setter
    public native void setName( Object name );
}
