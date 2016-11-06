package tyweb.firewars.src.GameObjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import tyweb.firewars.src.GameState;


public class Effect extends GameObject {

    public Effect(int x, int y, tyweb.firewars.src.ID ID, GameState isFor, int r) {
        super(x, y, ID, isFor, r);
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleInput(boolean in, KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
