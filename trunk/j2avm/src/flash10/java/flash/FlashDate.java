//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public final class FlashDate extends flash.FlashObject {
    public FlashDate() {}
    public FlashDate( double millis ) {}
    public FlashDate( String date ) {}
    public FlashDate( double year, double month, double date, double hour, double minute, double second, double millisecond ) {}
    public FlashDate( double year, double month, double date, double hour, double minute, double second ) {}
    public FlashDate( double year, double month, double date, double hour, double minute ) {}
    public FlashDate( double year, double month, double date, double hour ) {}
    public FlashDate( double year, double month, double date ) {}
    public FlashDate( double year, double month ) {}

    public static final native double UTC( Object year, Object month, Object date, Object hours, Object minutes, Object seconds, Object ms );

    public static final native double UTC( Object year, Object month, Object date, Object hours, Object minutes, Object seconds );

    public static final native double UTC( Object year, Object month, Object date, Object hours, Object minutes );

    public static final native double UTC( Object year, Object month, Object date, Object hours );

    public static final native double UTC( Object year, Object month, Object date );

    public static final native double UTC( Object year, Object month );

    public static final native double parse( Object s );

    public native double getMilliseconds( );

    public native double getUTCMinutes( );

    public native double setMilliseconds( Object ms );

    public native String toTimeString( );

    public native String toUTCString( );

    public native double setUTCMilliseconds( Object ms );

    public native double setMinutes( Object min, Object sec, Object ms );

    public native double getUTCMilliseconds( );

    public native double getDate( );

    public native String toLocaleString( );

    public native double valueOf( );

    public native double getMinutes( );

    public native double setUTCMinutes( Object min, Object sec, Object ms );

    public native double setDate( Object date );

    public native double getUTCSeconds( );

    public native double getUTCMonth( );

    public native double setUTCDate( Object date );

    public native String toDateString( );

    public native double getUTCDate( );

    public native double setUTCSeconds( Object sec, Object ms );

    public native double setUTCMonth( Object month, Object date );

    public native double getUTCHours( );

    public native double getTime( );

    public native double setSeconds( Object sec, Object ms );

    public native double setMonth( Object month, Object date );

    public native double getSeconds( );

    public native double getMonth( );

    public native double setHours( Object hour, Object min, Object sec, Object ms );

    public native double getUTCDay( );

    public native double setTime( Object t );

    public native String toLocaleTimeString( );

    public native double setUTCHours( Object hour, Object min, Object sec, Object ms );

    public native double getHours( );

    public native double getTimezoneOffset( );

    public native double getDay( );

    public native double getFullYear( );

    public native String toString( );

    public native double setFullYear( Object year, Object month, Object date );

    public native String toLocaleDateString( );

    public native double setUTCFullYear( Object year, Object month, Object date );

    public native double getUTCFullYear( );
}
