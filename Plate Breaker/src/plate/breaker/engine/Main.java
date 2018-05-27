package plate.breaker.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main {
    //Variables
    int fps = 60;
    int gameWidth = 1080, gameHeight = 800;
    public int crosshairX, crosshairY;
    public static int x=65, y=710;
    public static int  plateCount = 1, platesHit = 0;
    public static int highScore = 0;
    public static int attemptsLeft = 3;
    public static boolean gameOver = false;
    static boolean isRunning = true, plateLaunched = false;
    
    String highSCoreString = "High Score: ";
    Font stringFont = new Font("SansSerif", Font.PLAIN, 18);
    JFrame frame;
    Plate plate;
    Slingshot slingshot;
    Background background;
    Pebble pebble;
    Graphics g2;
    Graphics g;
    BufferedImage i;
    Controller c;
    
    //Initialize new Main method
    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        run();
        System.exit(-1);
    }
    
    //All objects and entities initialized here
    void init(){
        //Frame
        frame = new JFrame("Plate Breaker");
        frame.setSize(new Dimension(gameWidth, gameHeight));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        i = new BufferedImage(gameWidth, gameHeight, BufferedImage.TYPE_INT_RGB);
        g = i.getGraphics();
        g2 = frame.getGraphics();
        
        //Mouse motion listener
        Mouse motion = new Mouse(this);
        frame.addMouseMotionListener(motion);
        Mouse mouseClick = new Mouse(this);
        frame.addMouseListener(mouseClick);
        
        //Objects
        slingshot = new Slingshot();
        background = new Background();
        c = new Controller(this);
    }
    
    //Game Loop
    private void run(){
        init();
        
        while(isRunning){
            long time = System.currentTimeMillis();
            plateLaunched = true;
            update();
            render();
            time = (1000 / fps) - (System.currentTimeMillis() - time);
            
            if(time > 0){
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }  
        } 
    }

    //Main update method
    void update(){
        
        //Crosshair coordinates
        crosshairX = Mouse.x;
        crosshairY = Mouse.y;

        //Pebble and plate tick methods
        c.tick();
            
    }
    
    //All sprites and drawing done here
    void render(){
        //background
        g.drawImage(Background.background, 0, 0, frame);
        
        //Slingshot
        if(Mouse.clicked == false){
            g.drawImage(Slingshot.slingshotBefore, 50, 600, frame);
        }else if (Mouse.clicked == true){
            g.drawImage(Slingshot.slingshotAfter, 50, 500, frame);
        }
        
        //Pebble and plate
        c.render(g);
        
        //Crosshair       
        g.setColor(Color.black);
        g.fillRect(crosshairX-24, crosshairY, 50, 5);
        g.fillRect(crosshairX, crosshairY-22, 5, 45);
        
        //Scores
        g.setColor(Color.BLACK);
        g.setFont(stringFont);
        g.drawString("High Score: " + highScore, 500, 750);
        g.drawString("Score " + platesHit, 300, 750);
        
        //Draws full image to scren
        g2.drawImage(i, 0, 0, frame);

    }

}
