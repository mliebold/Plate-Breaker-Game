package plate.breaker.engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseMotionListener, MouseListener{
    public static int x, y;
    public static boolean clicked = false;
    public static int tX, tY;
    Controller c;
    Main game;

    //Mouse Contructor
    public Mouse(Main game) {
        this.game = game;
        this.c = new Controller(game);
    }
    

    @Override
    public void mouseDragged(MouseEvent me) {}

    //Gets coordinates of mouse
    @Override
    public void mouseMoved(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    
    //Shoots the pebble
    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("x = " + x + ", y = " + y);
        clicked = true;
        tX = x;
        tY = y;
        Controller.addEntity(new Pebble(Pebble.xStart, Pebble.yStart, game));
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    
}
