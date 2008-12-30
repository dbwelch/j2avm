package test;

import org.epistem.j2avm.annotations.swf.SWF;

import flash.display.Graphics;
import flash.display.MovieClip;

/**
 * A simple test case
 *
 * @author nickmain
 */
@SWF( width  = 110, 
      height = 110,
      background = 0xffeeee )
public class Test extends MovieClip {

    public Test() {
        for( int x = 10; x < 90; x += 10 ) {
            for( int y = 10; y < 90; y += 10 ) {
                paint( x, y );
            }            
        }
    }
    
    private void paint( int x, int y ) {
        Graphics g = getGraphics();
        g.beginFill( 0xff0000 );
        g.lineStyle( 3.0, 0x000080 );
        g.moveTo( x, y );
        g.lineTo( x + 10, y );
        g.lineTo( x + 10, y + 10 );
        g.lineTo( x, y + 10 );
        g.lineTo( x, y );
        g.endFill();        
    }
}
