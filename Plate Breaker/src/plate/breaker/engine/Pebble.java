package plate.breaker.engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pebble implements EntityA{
    public static BufferedImage pebble;
    
    public static int x = 65, y = 710;
    private static int pebbleX = 65, pebbleY = 710;
    private static int tY = Mouse.tY, tX = Mouse.tX;
    private int staticPebbleY = 710, staticPebbleX = 65;
    static int xStart=65, yStart=710;
    
    Main game;
    EntityB entB;
    
    //Pebble constructor
    public Pebble(int x, int y, Main game){
        Pebble.x = x;
        Pebble.y = y;
        this.game = game;
        
        try {
            Pebble.pebble = Sprite.getSprite("Pebble.png");
        }
        catch (IOException ex) {
            Logger.getLogger(Pebble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Pebble tick
    @Override
    public void tick(){ 
        //pebble movement
        tY = Mouse.tY;
        tX = Mouse.tX;
        pebbleY = staticPebbleY;
        pebbleX = staticPebbleX;
        y += (((tY - pebbleY)/10)-2);
        x += ((tX - pebbleX)/10);
        
        //Collision detection
        int dx = (Pebble.x + 25) - (Plate.x + 60);
        int dy = (Pebble.y + 25) - (Plate.y + 60);
        int sumOfRadius = 45;
         double distance = Math.sqrt(dx * dx + dy * dy);
        if(sumOfRadius >= distance){
            System.out.println("Collision Detected");
            Mouse.clicked = false;
            Controller.removeEntity(entB);
            Plate.plateXSet();
            Plate.plateYSet();
            //Score and highscore
            Main.platesHit++;
            if(Main.platesHit>Main.highScore){
                Main.highScore = Main.platesHit;
            }
        }
    }
    
    @Override
    public void render(Graphics g){
        g.drawImage(pebble, x, y, null);
    }

}
