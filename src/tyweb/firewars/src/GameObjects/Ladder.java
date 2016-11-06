package tyweb.firewars.src.GameObjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.Textures;

public class Ladder extends GameObject {

    public Ladder(int x, int y, tyweb.firewars.src.ID ID, GameState isFor, int r) {
        super(x, y, ID, isFor, r);
        w = 32;
        h = 32;
    }
    
    public Ladder(int x, int y, tyweb.firewars.src.ID ID, GameState isFor, int r, int w, int h) {
        super(x, y, ID, isFor, r);
        this.w = w;
        this.h = h;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Textures.ladder, x, y, w, h, null);
    }

    @Override
    public void handleInput(boolean in, KeyEvent ke) {
    }
    
    
}
