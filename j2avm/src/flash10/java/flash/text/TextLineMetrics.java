//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.text;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class TextLineMetrics extends flash.FlashObject {

    public  TextLineMetrics( double x, double width, double height, double ascent, double descent, double leading ) {}

    /** DO NOT CALL THIS CONSTRUCTOR - IT IS A FICTION */
    protected TextLineMetrics() {}

    @Getter
    public native double getAscent( );

    @Setter
    public native void setAscent( double setAscent );

    @Getter
    public native double getWidth( );

    @Setter
    public native void setWidth( double setWidth );

    @Getter
    public native double getHeight( );

    @Setter
    public native void setHeight( double setHeight );

    @Getter
    public native double getLeading( );

    @Setter
    public native void setLeading( double setLeading );

    @Getter
    public native double getX( );

    @Setter
    public native void setX( double setX );

    @Getter
    public native double getDescent( );

    @Setter
    public native void setDescent( double setDescent );
}
