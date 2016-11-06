package tyweb.firewars.src;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Textures {
    
    public static BufferedImage background,
            credits,
            level1,
            player0,
            player1,
            player2,
            player3,
            player4,
            player5,
            player6,
            player7,
            player8,
            player9,
            blockDirt,
            blockStone,
            ladder,
            bullet,
            heart,
            lvl1background,
            boss,
            coin;
    
    public Textures(SpriteManager sm){
        player0 = sm.get(1, 3, 32, 64);
        player1 = sm.get(2, 3, 32, 64);
        player2 = sm.get(3, 3, 32, 64);
        player3 = sm.get(4, 3, 32, 64);
        player4 = sm.get(5, 3, 32, 64);
        player5 = sm.get(1, 5, 32, 64);
        player6 = sm.get(2, 5, 32, 64);
        player7 = sm.get(3, 5, 32, 64);
        player8 = sm.get(4, 5, 32, 64);
        player9 = sm.get(5, 5, 32, 64);
        blockStone = sm.get(2, 1);
        blockDirt = sm.get(3, 1);
        ladder = sm.get(4, 2);
        coin = sm.get(2, 2);
        bullet = sm.get(3, 2, 16, 16);
        heart = sm.get(4, 1);
        boss = sm.get(5, 1, 64, 64);
        try {
            background = ImageIO.read(getClass().getResource("img/background.png"));
            credits = ImageIO.read(getClass().getResource("img/credits.png"));
            level1 = ImageIO.read(getClass().getResource("img/level1.png"));
            lvl1background = ImageIO.read(getClass().getResource("img/lvl1background.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
}
