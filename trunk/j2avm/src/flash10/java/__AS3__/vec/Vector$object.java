//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package __AS3__.vec;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class Vector$object extends flash.FlashObject {

    public  Vector$object( int length, boolean fixed ) {}

    public  Vector$object( int length ) {}

    public  Vector$object( ) {}

    public native __AS3__.vec.Vector$object reverse( );

    public native int unshift( );

    @Setter
    public native void setLength( int setLength );

    public native double indexOf( flash.FlashObject value, double from );

    public native double indexOf( flash.FlashObject value );

    public native Object pop( );

    public native __AS3__.vec.Vector$object slice( double start, double end );

    public native __AS3__.vec.Vector$object slice( double start );

    public native __AS3__.vec.Vector$object slice( );

    public native __AS3__.vec.Vector$object concat( );

    @Getter
    public native boolean getFixed( );

    public native int push( );

    public native boolean every( flash.FlashFunction checker, flash.FlashObject thisObj );

    public native boolean every( flash.FlashFunction checker );

    public native Object map( flash.FlashFunction mapper, flash.FlashObject thisObj );

    public native Object map( flash.FlashFunction mapper );

    public native __AS3__.vec.Vector$object sort( Object comparefn );

    public native Object shift( );

    @Getter
    public native int getLength( );

    @Setter
    public native void setFixed( boolean setFixed );

    public native String join( String separator );

    public native String join( );

    public native double lastIndexOf( flash.FlashObject value, double from );

    public native double lastIndexOf( flash.FlashObject value );

    public native String toString( );

    public native String toLocaleString( );

    public native void forEach( flash.FlashFunction eacher, flash.FlashObject thisObj );

    public native void forEach( flash.FlashFunction eacher );

    public native boolean some( Object checker, flash.FlashObject thisObj );

    public native boolean some( Object checker );

    public native __AS3__.vec.Vector$object splice( double start, double deleteCount );

    public native __AS3__.vec.Vector$object filter( flash.FlashFunction checker, flash.FlashObject thisObj );

    public native __AS3__.vec.Vector$object filter( flash.FlashFunction checker );
}
