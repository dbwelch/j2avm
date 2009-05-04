package test;

import org.epistem.j2avm.annotations.runtime.Name;
import org.epistem.j2avm.annotations.swf.SWF;
import org.epistem.j2avm.asm.AVM2_ASM;

import static flash.Flash.*;
import flash.FlashDate;
import flash.display.DisplayObjectContainer;
import flash.display.Graphics;
import flash.display.MovieClip;
import flash.events.MouseEvent;
import flash.text.TextField;
import flash.utils.Dictionary;
import static flash.utils_functions.*;

/**
 * A simple test case
 *
 * @author nickmain
 */
@SWF( width  = 800, 
      height = 600,
      background = 0xffeeee )
public class Test extends MovieClip implements Foo {

//    public static final int LINE_COLOR = 0xFF0080;
    
    public Test() {
//        field = new TextField();
//        field.setX( 10 );
//        field.setY( 150 );
//        field.setWidth( 150 );
//        field.setHeight( 20 );
//        field.setText( GUser.WELCOME_MESSAGE );
//        addChild( field );
//
//        field2 = new TextField();
//        field2.setX( 300 );
//        field2.setY( 10 );
//        field2.setWidth( 490 );
//        field2.setHeight( 580 );
//        field2.setText( describeType( getDefinitionByName( "flash.display.MovieClip" ) ).toString() );
//        field2.setBorder( true );
//        addChild( field2 );
//        
//        for( int x = 10; x < 90; x += 15 ) {
//            // StringBuilder test --> 
//            trace( "x = " + x );
//            
//            for( int y = 10; y < 90; y += 15 ) {
//                
//                switch( x ) {
//                    case 25: trace( "25" ); break;                      
//                    case 55: trace( "55" ); break;                      
//                    default: trace( "default" ); break;
//                } 
//
//                if( this instanceof DisplayObjectContainer ) paint( x, y );
//                if( colorIndex >= colors.length ) colorIndex = 0;
//            }            
//        }
//    
//        getStage().addEventListener( MouseEvent.MOUSE_MOVE, delegate( "onMove" ) );
//
//        Object[] objs = { new Object(), new Object(), "obj3", new Object(), new Object(), new Object() };
//        for( int i = 0; i < objs.length; i++ ) {
//            trace( "Object " + i + " ==> " + objs[i] + " " + objs[i].hashCode() );
//        }
//        
//        for( int i = 0; i < objs.length; i++ ) {
//            trace( "Object " + i + " ==> " + objs[i] + " " + objs[i].hashCode() );
//        }
//
//        trace( "NEW OBJECT ==> " + new Object() );

//        new Painter( );
    }

//    @Name("onMove")
//    void onMove( MouseEvent e ) {
//        double now = new FlashDate().getTime();
//        field.setText( "" + now + " " + e.getStageX() + "," + e.getStageY() );
//    }
//    
//    //override in order to test whether super.getGraphics works
//    //public Graphics getGraphics() { return null; }
//    
//    private boolean paint( int x, int y ) {
//        Painter p = new Painter( super.getGraphics() );
//        if( p instanceof FooBar ) {
//            p.useGraphics( super.getGraphics() );
//        }
//        p.paint( x, y );
//        
//        return true; //test pop instruction
//    }
}
