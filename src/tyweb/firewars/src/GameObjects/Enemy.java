package tyweb.firewars.src.GameObjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import tyweb.firewars.src.Game;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.Textures;


public class Enemy extends Creature {
    
    Player p;
    
    public Enemy(int x, int y, tyweb.firewars.src.ID ID, GameState isFor, int r, Game g, Player p, int health) {
        super(x, y, ID, isFor, r, g, health);
        this.p = p;
        w=32;
        h=64;
    }

    @Override
    public void tick() {
        x+=vx;
        y+=vy;
        if(falling || jumping){
            vy+=gravity;
        }
        if(vy > 100) vy = 100;
        if(x < 0) x = 1;
        if(y < 0) y = 1;
        if(x+w+1 > g.getLevelWidth()) x = g.getLevelWidth()-w-1;
        if(y+h+1 > g.getLevelHeight()) y = g.getLevelHeight()-h-1;
        if(x < p.getX() + (p.getWidth()/2)) vx = 3;
        if(x > p.getX() + (p.getWidth()/2)) vx = -3;
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
            vx = flinch;
            flinch--;
        }else if(flinch < 0){
            vx = flinch;
            flinch++;
        }else{
            if(health <= 0) isDead = true;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Textures.player0, x, y, w, h, null);
    }

    @Override
    public void handleInput(boolean in, KeyEvent ke) {
    }

}
