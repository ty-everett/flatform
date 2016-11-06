package tyweb.firewars.src.GameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.Handler;


public class Button extends GameObject {
    
    int size;
    String text;
    
    public Button(int x, int y, tyweb.firewars.src.ID ID, GameState isFor, int r, String text, int size) {
        super(x, y, ID, isFor, r);
        this.text = text;
        this.size = size;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, size));
        w = g.getFontMetrics().stringWidth(text)+g.getFontMetrics().stringWidth(text)/4;
        h = g.getFontMetrics().getHeight()+g.getFontMetrics().getHeight()/8;
        g.setColor(Color.blue.brighter());
        g.fillRect(x, y, w, h);
        g.setColor(Color.white);
        g.drawRect(x, y, w, h);
        g.drawString(text, x + w/10, y+h-(h/4));
    }
    
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }
    public void setSize(int s){
        this.size = s;
    }

    @Override
    public void handleInput(boolean in, KeyEvent ke) {
        
    }
    
    
}
