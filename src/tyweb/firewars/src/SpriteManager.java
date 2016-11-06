package tyweb.firewars.src;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class SpriteManager {
    
    BufferedImage spriteSheet;
    
    public SpriteManager(){
        try {
            spriteSheet = ImageIO.read(getClass().getResource("img/SpriteSheet.bmp"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage get(int x, int y){
        return spriteSheet.getSubimage(x*32-32, y*32-32, 32, 32);
    }
    public BufferedImage get(int x, int y, int w, int h){
        return spriteSheet.getSubimage(x*32-32, y*32-32, w, h);
    }
}
