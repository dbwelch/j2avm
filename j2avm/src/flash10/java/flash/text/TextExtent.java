//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class TextExtent extends flash.FlashObject {

    public  TextExtent( double width, double height, double textFieldWidth, double textFieldHeight, double ascent, double descent ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected TextExtent() {}

    @Getter
    public native double getAscent( );

    @Setter
    public native void setAscent( double ascent );

    @Getter
    public native double getWidth( );

    @Setter
    public native void setWidth( double width );

    @Getter
    public native double getHeight( );

    @Setter
    public native void setHeight( double height );

    @Getter
    public native double getTextFieldWidth( );

    @Setter
    public native void setTextFieldWidth( double textFieldWidth );

    @Getter
    public native double getDescent( );

    @Setter
    public native void setDescent( double descent );

    @Getter
    public native double getTextFieldHeight( );

    @Setter
    public native void setTextFieldHeight( double textFieldHeight );
}
