package test;

import org.epistem.j2avm.annotations.swf.SWF;

import flash.display.DisplayObjectContainer;
import flash.display.Graphics;
import flash.display.MovieClip;

/**
 * A simple test case
 *
 * @author nickmain
 */
@SWF( width  = 200, 
      height = 200,
      background = 0xffeeee )
public class Test extends MovieClip {

    private class Painter {
        private final Graphics g;
        
        private Painter( Graphics g ) {
            this.g = g;
            trace( "New Painter" );
        }
        
        private void paint( int x, int y ) {
            g.beginFill( colors[ colorIndex++ ] );
            g.lineStyle( 1, 0x000080 );
            g.moveTo( x, y );
            g.lineTo( x + 10, y );
            g.lineTo( x + 10, y + 10 );
            g.lineTo( x, y + 10 );
            g.lineTo( x, y );
            g.endFill();
        }
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
    
    public Test() {
        trace( "New Test" );

        for( int x = 10; x < 90; x += 15 ) {
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
    }
    
    private boolean paint( int x, int y ) {
        new Painter( getGraphics() ).paint( x, y );
        
        return true; //test pop instruction
    }
}
