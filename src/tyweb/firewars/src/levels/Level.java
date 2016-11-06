package tyweb.firewars.src.levels;

import java.awt.image.BufferedImage;
import tyweb.firewars.src.Game;
import tyweb.firewars.src.GameObjects.Player;
import tyweb.firewars.src.Handler;

public abstract class Level {

    Handler h;
    Player p;
    Game g;
    
    public Level (Handler h, Player p, Game g){
        this.h = h;
        this.p = p;
        this.g = g;
    }
    
    public int block(int i){
        return i*32-32;
    }
    public abstract void buildTerrain(BufferedImage b);
}
