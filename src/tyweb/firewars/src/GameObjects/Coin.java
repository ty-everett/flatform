package tyweb.firewars.src.GameObjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.Textures;
import tyweb.firewars.src.Game;

public class Coin extends GameObject {
    
    boolean vanishing = false;
    boolean remove = false;
    int ox, oy;
    Game g;
    
    public Coin(int x, int y, tyweb.firewars.src.ID ID, GameState isFor, int r, Game g) {
        super(x, y, ID, isFor, r);
        ox = x;
        oy = y;
        this.g = g;
    }

    @Override
    public void tick() {
        if(vanishing){
            x-=Math.abs(g.getCamX() - ox)/30;
            y-=Math.abs(g.getCamY() - oy)/30;
        }
        if(x < g.getCamX() && y < g.getCamY()) remove = true;
    
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Textures.coin, x, y, null);
    }

    @Override
    public void handleInput(boolean in, KeyEvent ke) {
    }
    
    public void setVanishing(boolean b){
        vanishing = b;
    }
    public boolean getVanishing(){
        return vanishing;
    }
    public boolean shouldRemove(){
        return remove;
    }

}
