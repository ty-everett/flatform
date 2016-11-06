package tyweb.firewars.src.GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.Textures;

public class Bullet extends GameObject {
    
    public Bullet(int x, int y, int vx, tyweb.firewars.src.ID ID, GameState isFor, int  r) {
        super(x, y, ID, isFor, r);
        this.vx = vx;
        w = 16;
        h = 16;
    }
    public Bullet(int x, int y, int vx, int vy, tyweb.firewars.src.ID ID, GameState isFor, int r) {
        super(x, y, ID, isFor, r);
        this.vx = vx;
        this.vy = vy;
        w = 16;
        h = 16;
    }
    
    @Override
    public void tick() {
        x+=vx;
        y+=vy;
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Textures.bullet, x, y, null);
    }
    
    @Override
    public void handleInput(boolean in, KeyEvent ke) {
    }
    
    public Rectangle getRectangle(){
        return new Rectangle(x, y, w, h);
    }
    
}
