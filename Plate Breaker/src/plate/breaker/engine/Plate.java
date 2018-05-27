package plate.breaker.engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plate implements EntityB{
    public static BufferedImage plate;
    public static int x = 1080, y = 750;
    static float xSpeed = 0f, ySpeed = 0f;
    private float gravity = 0.05f;
    static int lastRandomNum, randomYSpeed;
    
    Main game;

    //Plate contructor
    public Plate(int x, int y, Main game) {
        Plate.x = x;
        Plate.y = y;
        this.game = game;

        try {
            Plate.plate = Sprite.getSprite("Plate.png");
        } catch (IOException ex) {
            Logger.getLogger(Plate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //Creates Random Y Speed
    public static int randomNum(){
        randomYSpeed = (int)(Math.random() * 10);
         if(randomYSpeed <=3){
             randomYSpeed += 5;
         }
         if(-(randomYSpeed)==lastRandomNum){
             randomNum();
         }
         System.out.println("ySpeed is " + randomYSpeed);
         return -(randomYSpeed);
    }
    
    //Sets the plates Y coordinate and sets new Y speed
    public static float plateYSet(){
        y = 750;
        ySpeed = randomNum();
        lastRandomNum = (int)ySpeed;
        return y;
    }
    
    //Sets plates X cooridnate and X speed
    public static int plateXSet(){
        x = 1080;
        xSpeed = -3f;
        return x;
    }
    
    //Plates tick method
    @Override
    public void tick(){

        
        x += (int)xSpeed;
        y += (int)ySpeed;

        ySpeed += gravity;

    }
    
    //Plates render method
    @Override
    public void render(Graphics g){
        g.drawImage(plate, x, y, null);
        
    }

}
