package plate.breaker.engine;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {

    //Linked lists for entity A and B
    private static final LinkedList<EntityA> eA = new LinkedList<EntityA>();
    private static final LinkedList<EntityB> eB = new LinkedList<EntityB>();
   
    EntityA entA;
    EntityB entB;
    Main game;
    
    public Controller(Main game){
        this.game = game;
        
        //Adds initial plate
        addEntity(new Plate(Plate.x, Plate.y, game)); 
    }
    
    //Tick method for entity A and B
    public void tick(){
        //Entity A
        for(int i = 0; i < eA.size(); i++){
            entA = eA.get(i);
            
        //If pebble leaves screen remove pebble
        if(Pebble.x>1080 || Pebble.y<1){
            Mouse.clicked = false;
            Controller.removeEntity(entA);
        }
            
            entA.tick();
        }
        
        //Entity B
        for(int i = 0; i < eB.size(); i++){
            entB = eB.get(i);
        
            //If plate leaves screen remove plate and add new one
            if(Plate.y>800 || Plate.x < 0){
                removeEntity(entB);
                //Decrements attempts left and decides whether game is over or not
                Main.attemptsLeft--;
                if(Main.attemptsLeft == 0){
                    Main.platesHit = 0;
                    Main.attemptsLeft = 3;
                    System.out.println(Main.attemptsLeft);
                }
                
                int plateX = Plate.plateXSet();
                int plateY = (int)Plate.plateYSet();
                addEntity(new Plate(plateX, plateY, game));
            }

            entB.tick();
        }
    }

    //Render method for entity A and B
    public void render(Graphics g){
        //Entity A
        for(int i = 0; i < eA.size(); i++){
            entA = eA.get(i);
            
            entA.render(g);
        }
        //Entity B 
        for(int i = 0; i < eB.size(); i++){
            entB = eB.get(i);
            
            entB.render(g);
        }
    }
    
    //Methods to add and remove entities
    public static void addEntity(EntityA block){
        eA.add(block);
    }

    public static void addEntity(EntityB block){
        eB.add(block);
    }  
    
    public static void removeEntity(EntityA block){
        eA.remove(block);
    }
    
    public static void removeEntity(EntityB block){
        eB.remove(block);
    }

}
