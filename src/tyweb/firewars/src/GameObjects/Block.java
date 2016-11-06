package tyweb.firewars.src.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.Textures;

public class Block extends GameObject {
    
    Rectangle rect;
    
    public Block(int x, int y, tyweb.firewars.src.ID ID, GameState isFor, int r) {
        super(x, y, ID, isFor, r);
        setWidth(32);
        setHeight(32);
        rect = new Rectangle(x, y, w, h);
    }
    public Block(int x, int y, int w, int h, tyweb.firewars.src.ID ID, GameState isFor, int r, Textures tex) {
        super(x, y, ID, isFor, r);
        setWidth(w);
        setHeight(h);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
            if(ID == ID.blockDirt){
                g.drawImage(Textures.blockDirt, x, y, null);
            }
            if(ID == ID.blockStone){
                g.drawImage(Textures.blockStone, x, y, null);
            }
    }

    @Override
    public void handleInput(boolean in, KeyEvent ke) {
    }
    
    public Rectangle getRectangle(){
        return rect;
    }
    
}
