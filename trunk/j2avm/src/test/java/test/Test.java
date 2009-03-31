package test;

import org.epistem.j2avm.annotations.runtime.Name;
import org.epistem.j2avm.annotations.swf.SWF;

import static flash.Flash.*;
import flash.FlashDate;
import flash.display.DisplayObjectContainer;
import flash.display.Graphics;
import flash.display.MovieClip;
import flash.events.MouseEvent;
import flash.text.TextField;

/**
 * A simple test case
 *
 * @author nickmain
 */
@SWF( width  = 500, 
      height = 200,
      background = 0xffeeee )
public class Test extends MovieClip {

    public static final int LINE_COLOR = 0xFF0080;
    
    public interface GUser extends FooBar {
        public static final String WELCOME_MESSAGE = "Hello World !!";
        
        public void useGraphics( Graphics g );
    }
    
    public interface FooBar {}
    
    private class Painter implements GUser {
        private Graphics g;
        
        private Painter( Graphics g ) {
            trace( "New Painter" );
        }

        public void useGraphics( Graphics g ) {
            trace( "Using graphics: " + g );
            this.g = g;
        }

        private void paint( int x, int y ) {
            g.beginFill( colors[ colorIndex++ ] );
            g.lineStyle( 1, LINE_COLOR );
            g.moveTo( x, y );
            g.lineTo( x + 10, y );
            g.lineTo( x + 10, y + 10 );
            g.lineTo( x, y + 10 );
            g.lineTo( x, y );
            g.endFill();
        }
    }
    
    public static enum Foo {
        Red, Green, Blue        
    }
    
    private int[] colors = {
        0xffffff,
        0xffff00,
        0xff00ff,
        0x00ffff,
        0xff0000,
        0x00ff00,
        0x0000ff,
        0x000000
    };
    
    protected int colorIndex = 0;

    private final TextField field;
    private final TextField field2;
    
    //private Foo foo;
    
    public Test() {
        field = new TextField();
        field.setX( 10 );
        field.setY( 150 );
        field.setWidth( 150 );
        field.setHeight( 20 );
        field.setText( GUser.WELCOME_MESSAGE );
        addChild( field );

        field2 = new TextField();
        field2.setX( 300 );
        field2.setY( 10 );
        field2.setWidth( 190 );
        field2.setHeight( 180 );
        field2.setText( "Hashcode test -->\n" );
        field2.setBorder( true );
        addChild( field2 );
        
        for( int x = 10; x < 90; x += 15 ) {
            // StringBuilder test --> 
            trace( "x = " + x );
            
            for( int y = 10; y < 90; y += 15 ) {
                
                switch( x ) {
                    case 25: trace( "25" ); break;                      
                    case 55: trace( "55" ); break;                      
                    default: trace( "default" ); break;
                } 

                if( this instanceof DisplayObjectContainer ) paint( x, y );
                if( colorIndex >= colors.length ) colorIndex = 0;
            }            
        }
    
        getStage().addEventListener( MouseEvent.MOUSE_MOVE, delegate( "onMove" ) );
    }

    @Name("onMove")
    void onMove( MouseEvent e ) {
        double now = new FlashDate().getTime();
        field.setText( "" + now + " " + e.getStageX() + "," + e.getStageY() );
    }
    
    //override in order to test whether super.getGraphics works
    //public Graphics getGraphics() { return null; }
    
    private boolean paint( int x, int y ) {
        Painter p = new Painter( super.getGraphics() );
        if( p instanceof FooBar ) {
            p.useGraphics( super.getGraphics() );
        }
        p.paint( x, y );
        
        return true; //test pop instruction
    }
}
