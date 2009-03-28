//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package __AS3__.vec;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class Vector$double extends flash.FlashObject {

    public native double shift( );

    public native __AS3__.vec.Vector$double reverse( );

    public native int unshift( );

    @Setter
    public native void setLength( int setLength );

    public native double indexOf( double value, double from );

    public native double indexOf( double value );

    public native double pop( );

    public native __AS3__.vec.Vector$double slice( double start, double end );

    public native __AS3__.vec.Vector$double slice( double start );

    public native __AS3__.vec.Vector$double slice( );

    public native __AS3__.vec.Vector$double concat( );

    public native boolean some( Object checker, flash.FlashObject thisObj );

    public native boolean some( Object checker );

    public native int push( );

    public native boolean every( flash.FlashFunction checker, flash.FlashObject thisObj );

    public native boolean every( flash.FlashFunction checker );

    public native Object map( flash.FlashFunction mapper, flash.FlashObject thisObj );

    public native Object map( flash.FlashFunction mapper );

    public native __AS3__.vec.Vector$double sort( Object comparefn );

    @Getter
    public native int getLength( );

    @Setter
    public native void setFixed( boolean setFixed );

    public native void forEach( flash.FlashFunction eacher, flash.FlashObject thisObj );

    public native void forEach( flash.FlashFunction eacher );

    public native double lastIndexOf( double value, double from );

    public native double lastIndexOf( double value );

    public native String toString( );

    public native String toLocaleString( );

    public native String join( String separator );

    public native String join( );

    @Getter
    public native boolean getFixed( );

    public native __AS3__.vec.Vector$double filter( flash.FlashFunction checker, flash.FlashObject thisObj );

    public native __AS3__.vec.Vector$double filter( flash.FlashFunction checker );

    public native __AS3__.vec.Vector$double splice( double start, double deleteCount );
}
