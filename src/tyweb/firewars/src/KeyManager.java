package tyweb.firewars.src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;


public class KeyManager implements KeyListener {
    
    Game g;
    boolean keys[];
    
    public KeyManager(Game g){
        this.g = g;
        keys = new boolean[256];
        for(int i = 0; i < keys.length; i++){
            keys[i] = false;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent k){
        keys[k.getKeyCode()] = true;
        g.keyPressed(k);
    }
    
    @Override
    public void keyReleased(KeyEvent k){
        keys[k.getKeyCode()] = false;
        g.keyReleased(k);
    }
    
    public void keyTyped(KeyEvent ke){}    
}
