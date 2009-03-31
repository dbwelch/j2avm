//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text.engine;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class TabStop extends flash.FlashObject {

    public  TabStop( String alignment, double position, String decimalAlignmentToken ) {}

    public  TabStop( String alignment, double position ) {}

    public  TabStop( String alignment ) {}

    public  TabStop( ) {}

    @Setter
    public native void setPosition( double setPosition );

    @Getter
    public native String getAlignment( );

    @Setter
    public native void setAlignment( String setAlignment );

    @Getter
    public native String getDecimalAlignmentToken( );

    @Getter
    public native double getPosition( );

    @Setter
    public native void setDecimalAlignmentToken( String setDecimalAlignmentToken );
}
