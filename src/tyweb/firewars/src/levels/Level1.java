package tyweb.firewars.src.levels;

import java.awt.image.BufferedImage;
import tyweb.firewars.src.GameObjects.Block;
import tyweb.firewars.src.GameObjects.Player;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.Handler;
import tyweb.firewars.src.ID;
import tyweb.firewars.src.Textures;
import tyweb.firewars.src.Game;
import tyweb.firewars.src.GameObjects.BossEnemy;
import tyweb.firewars.src.GameObjects.Coin;
import tyweb.firewars.src.GameObjects.Enemy;
import tyweb.firewars.src.GameObjects.Ladder;

public class Level1 extends Level {

    public Level1(Handler h, Player p, Game g) {
        super(h, p, g);
        h.addObject(p);
        g.setLevelHeight(Textures.level1.getHeight()*32);
        g.setLevelWidth(Textures.level1.getWidth()*32);
        buildTerrain(Textures.level1);
    }
    
    @Override
    public void buildTerrain(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int pixel = bi.getRGB(x, y);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = (pixel) & 0xff;
                if(r == 255 &&
                    g == 255 &&
                    b == 255){
                    h.addObject(new Block(x*32, y*32, ID.blockStone, GameState.level1, 3));
                }else if(r == 0 &&
                    g == 0 &&
                    b == 255){
                    p.setX(x*32);
                    p.setY(y*32);
                }else if(r == 100 &&
                        g == 100 &&
                        b == 100){
                    h.addObject(new Block(x*32, y*32, ID.blockDirt, GameState.level1, 1));
                }else if(r == 0 &&
                        g == 255 &&
                        b == 0){
                    h.addObject(new Coin(x*32, y*32, ID.coin, GameState.level1, 1, this.g));
                }else if(r == 255 &&
                        g == 255 &&
                        b == 0){
                    h.addObject(new Enemy(x*32, y*32, ID.enemy, this.g.state, 2, this.g, p, 3));
                }else if(r == 255 &&
                        g == 128 &&
                        b == 0){
                    h.addObject(new Ladder(x*32, y*32, ID.coin, this.g.state, 1));
                }else if(r == 255 &&
                        g == 0 &&
                        b == 0){
                    h.addObject(new BossEnemy(1, 1, ID.boss, GameState.level1, this.g, 35, p, 2));
                }
            }
        }
    }
}
