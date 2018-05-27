package plate.breaker.engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Slingshot {
    public static BufferedImage slingshotBefore, slingshotAfter;

    public Slingshot() {
        try {
            Slingshot.slingshotBefore = Sprite.getSprite("SlingshotBefore.png");
            Slingshot.slingshotAfter = Sprite.getSprite("SlingshotAfter.png");
        } catch (IOException ex) {
            Logger.getLogger(Slingshot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
