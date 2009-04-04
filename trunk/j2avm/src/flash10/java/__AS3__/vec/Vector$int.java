//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package __AS3__.vec;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class Vector$int extends flash.FlashObject {

    public  Vector$int( int length, boolean fixed ) {}

    public  Vector$int( int length ) {}

    public  Vector$int( ) {}

    public native int shift( );

    public native __AS3__.vec.Vector$int reverse( );

    public native int unshift( );

    @Setter
    public native void setLength( int value );

    public native double indexOf( int value, double from );

    public native double indexOf( int value );

    public native int pop( );

    public native __AS3__.vec.Vector$int slice( double start, double end );

    public native __AS3__.vec.Vector$int slice( double start );

    public native __AS3__.vec.Vector$int slice( );

    public native __AS3__.vec.Vector$int concat( );

    public native boolean some( Object checker, flash.FlashObject thisObj );

    public native boolean some( Object checker );

    public native int push( );

    public native boolean every( flash.FlashFunction checker, flash.FlashObject thisObj );

    public native boolean every( flash.FlashFunction checker );

    public native Object map( flash.FlashFunction mapper, flash.FlashObject thisObj );

    public native Object map( flash.FlashFunction mapper );

    public native __AS3__.vec.Vector$int sort( Object comparefn );

    @Getter
    public native int getLength( );

    @Setter
    public native void setFixed( boolean f );

    public native void forEach( flash.FlashFunction eacher, flash.FlashObject thisObj );

    public native void forEach( flash.FlashFunction eacher );

    public native double lastIndexOf( int value, double from );

    public native double lastIndexOf( int value );

    public native String toString( );

    public native String toLocaleString( );

    public native String join( String separator );

    public native String join( );

    @Getter
    public native boolean getFixed( );

    public native __AS3__.vec.Vector$int filter( flash.FlashFunction checker, flash.FlashObject thisObj );

    public native __AS3__.vec.Vector$int filter( flash.FlashFunction checker );

    public native __AS3__.vec.Vector$int splice( double start, double deleteCount );
}
