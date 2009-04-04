//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class FlashArray extends flash.FlashObject {

    public  FlashArray( ) {}

    public static final int CASEINSENSITIVE = 1;

    public static final int length = 1;

    public static final int NUMERIC = 16;

    public static final int UNIQUESORT = 4;

    public static final int RETURNINDEXEDARRAY = 8;

    public static final int DESCENDING = 2;

    public native flash.FlashArray reverse( );

    public native flash.FlashArray map( flash.FlashFunction callback, Object thisObject );

    public native flash.FlashArray map( flash.FlashFunction callback );

    public native Object shift( );

    @Getter
    public native int getLength( );

    public native int unshift( );

    public native String join( Object sep );

    public native int lastIndexOf( Object searchElement, Object fromIndex );

    public native int lastIndexOf( Object searchElement );

    @Setter
    public native void setLength( int newLength );

    public native int indexOf( Object searchElement, Object fromIndex );

    public native int indexOf( Object searchElement );

    public native Object pop( );

    public native flash.FlashArray slice( Object A, Object B );

    public native flash.FlashArray slice( Object A );

    public native flash.FlashArray slice( );

    public native flash.FlashArray concat( );

    public native boolean some( flash.FlashFunction callback, Object thisObject );

    public native boolean some( flash.FlashFunction callback );

    public native flash.FlashArray filter( flash.FlashFunction callback, Object thisObject );

    public native flash.FlashArray filter( flash.FlashFunction callback );

    public native void forEach( flash.FlashFunction callback, Object thisObject );

    public native void forEach( flash.FlashFunction callback );

    public native int push( );

    public native boolean every( flash.FlashFunction callback, Object thisObject );

    public native boolean every( flash.FlashFunction callback );

    public native Object splice( );

    public native Object sortOn( Object names, Object options );

    public native Object sortOn( Object names );

    public native Object sort( );
}
