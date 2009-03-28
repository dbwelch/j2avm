//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.net;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class NetConnection extends flash.events.EventDispatcher {

    @Setter
    public static final native void setDefaultObjectEncoding( int setDefaultObjectEncoding );

    @Getter
    public static final native int getDefaultObjectEncoding( );

    @Getter
    public native flash.FlashArray getUnconnectedPeerStreams( );

    @Getter
    public native String getNearID( );

    @Setter
    public native void setObjectEncoding( int setObjectEncoding );

    @Setter
    public native void setMaxPeerConnections( int setMaxPeerConnections );

    @Getter
    public native String getProtocol( );

    @Getter
    public native String getProxyType( );

    @Getter
    public native boolean getConnected( );

    public native void connect( String command );

    @Getter
    public native flash.FlashObject getClient( );

    @Getter
    public native String getUri( );

    public native void addHeader( String operation, boolean mustUnderstand, flash.FlashObject param );

    public native void addHeader( String operation, boolean mustUnderstand );

    public native void addHeader( String operation );

    @Getter
    public native int getMaxPeerConnections( );

    @Setter
    public native void setProxyType( String setProxyType );

    @Getter
    public native int getObjectEncoding( );

    @Getter
    public native String getNearNonce( );

    @Setter
    public native void setClient( flash.FlashObject setClient );

    @Getter
    public native boolean getUsingTLS( );

    public native void close( );

    @Getter
    public native String getFarID( );

    @Getter
    public native String getFarNonce( );

    public native void call( String command, flash.net.Responder responder );

    @Getter
    public native String getConnectedProxyType( );
}
