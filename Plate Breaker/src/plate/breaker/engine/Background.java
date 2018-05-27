package plate.breaker.engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Background {
    public static BufferedImage background;

    public Background() {
        try {
            Background.background = Sprite.getSprite("GameBackground.png");
        } catch (IOException ex) {
            Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
