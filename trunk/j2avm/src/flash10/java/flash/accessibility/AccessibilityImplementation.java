//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.accessibility;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class AccessibilityImplementation extends flash.FlashObject {

    public  AccessibilityImplementation( ) {}

    public native boolean isLabeledBy( flash.geom.Rectangle labelBounds );

    public native String get_accName( int childID );

    @Getter
    public native int getErrno( );

    @Setter
    public native void setErrno( int errno );

    public native int get_accFocus( );

    public native String get_accValue( int childID );

    public native void accDoDefaultAction( int childID );

    @Getter
    public native boolean getStub( );

    @Setter
    public native void setStub( boolean stub );

    public native flash.FlashArray get_accSelection( );

    public native int get_accRole( int childID );

    public native Object accLocation( int childID );

    public native flash.FlashArray getChildIDArray( );

    public native int get_accState( int childID );

    public native void accSelect( int operation, int childID );

    public native String get_accDefaultAction( int childID );
}
