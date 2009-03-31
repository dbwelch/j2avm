//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class SharedObject extends flash.events.EventDispatcher {

    public  SharedObject( ) {}

    @Setter
    public static final native void setDefaultObjectEncoding( int setDefaultObjectEncoding );

    public static final native int getDiskUsage( String url );

    @Getter
    public static final native int getDefaultObjectEncoding( );

    public static final native flash.net.SharedObject getLocal( String name, String localPath, boolean secure );

    public static final native flash.net.SharedObject getLocal( String name, String localPath );

    public static final native flash.net.SharedObject getLocal( String name );

    public static final native int deleteAll( String url );

    public static final native flash.net.SharedObject getRemote( String name, String remotePath, flash.FlashObject persistence, boolean secure );

    public static final native flash.net.SharedObject getRemote( String name, String remotePath, flash.FlashObject persistence );

    public static final native flash.net.SharedObject getRemote( String name, String remotePath );

    public static final native flash.net.SharedObject getRemote( String name );

    @Getter
    public native int getSize( );

    public native String flush( int minDiskSpace );

    public native String flush( );

    public native void send( );

    @Getter
    public native flash.FlashObject getData( );

    @Getter
    public native flash.FlashObject getClient( );

    public native void clear( );

    public native void setDirty( String propertyName );

    public native void connect( flash.net.NetConnection myConnection, String params );

    public native void connect( flash.net.NetConnection myConnection );

    @Setter
    public native void setFps( double setFps );

    @Setter
    public native void setObjectEncoding( int setObjectEncoding );

    @Setter
    public native void setClient( flash.FlashObject setClient );

    public native void setProperty( String propertyName, flash.FlashObject value );

    public native void setProperty( String propertyName );

    public native void close( );

    @Getter
    public native int getObjectEncoding( );
}
