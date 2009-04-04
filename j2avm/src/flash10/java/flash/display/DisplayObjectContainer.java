//---------------------------------------------------------------------------
// THIS FILE WAS AUTOMATICALLY GENERATED - HAND ALTERATIONS MAY BE LOST
//---------------------------------------------------------------------------
package flash.display;

import org.epistem.j2avm.annotations.runtime.*;

@FlashNativeClass
public class DisplayObjectContainer extends flash.display.InteractiveObject {

    public  DisplayObjectContainer( ) {}

    @Getter
    public native boolean getMouseChildren( );

    @Getter
    public native int getNumChildren( );

    public native boolean contains( flash.display.DisplayObject child );

    public native void swapChildrenAt( int index1, int index2 );

    public native flash.display.DisplayObject getChildByName( String name );

    public native flash.display.DisplayObject removeChildAt( int index );

    public native int getChildIndex( flash.display.DisplayObject child );

    public native flash.display.DisplayObject addChildAt( flash.display.DisplayObject child, int index );

    @Setter
    public native void setTabChildren( boolean enable );

    @Getter
    public native flash.text.TextSnapshot getTextSnapshot( );

    public native void swapChildren( flash.display.DisplayObject child1, flash.display.DisplayObject child2 );

    @Getter
    public native boolean getTabChildren( );

    public native flash.FlashArray getObjectsUnderPoint( flash.geom.Point point );

    @Setter
    public native void setMouseChildren( boolean enable );

    public native flash.display.DisplayObject removeChild( flash.display.DisplayObject child );

    public native flash.display.DisplayObject getChildAt( int index );

    public native flash.display.DisplayObject addChild( flash.display.DisplayObject child );

    public native boolean areInaccessibleObjectsUnderPoint( flash.geom.Point point );

    public native void setChildIndex( flash.display.DisplayObject child, int index );
}
