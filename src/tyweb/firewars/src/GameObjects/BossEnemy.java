package tyweb.firewars.src.GameObjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.Textures;
import tyweb.firewars.src.Game;

public class BossEnemy extends Creature {
    
    Player p;
    
    public BossEnemy(int x, int y, tyweb.firewars.src.ID ID, GameState isFor, Game g, int he, Player p, int r) {
        super(x, y, ID, isFor, he, g, r);
        this.p = p;
        w = 128;
        h = 128;
    }

    @Override
    public void tick() {
        x+=vx;
        y+=vy;
        if(vy > 100) vy = 100;
        if(x < 0) x = 1;
        if(y < 0) y = 1;
        if(x+w+1 > g.getLevelWidth()) x = g.getLevelWidth()-w-1;
        if(y+h+1 > g.getLevelHeight()) y = g.getLevelHeight()-h-1;
        if(x < p.getX() + (p.getWidth()/2)) vx = 1;
        if(x > p.getX() + (p.getWidth()/2)) vx = -1;
        if(y+h+3 > g.getLevelHeight()) isDead = true;
        if(Math.abs(p.getY() - y) > 200) vx = 0;
        if(x+w>p.getX()&&x<p.getX()+p.getWidth()/2){
            vx = 0;
            x=p.getX()-w+1;
        }else if(x<p.getX()+p.getWidth()&&x>p.getX()+p.getWidth()/2){
            vx = 0;
            x=p.getX()+p.getWidth()-1;
        }
        if(flinch > 0){
            vx = flinch/3;
            flinch--;
        }else if(flinch < 0){
            vx = flinch/3;
            flinch++;
        }else{
            if(health <= 0) isDead = true;
        }
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(Textures.boss, x, y, w, h, null);
    }
    @Override
    public void handleInput(boolean in, KeyEvent ke) {
        
    }
}
