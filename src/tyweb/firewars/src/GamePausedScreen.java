package tyweb.firewars.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class GamePausedScreen {
    
    Game g;
    Handler h;
    
    public GamePausedScreen(Game g, Handler h){
        this.g = g;
        this.h = h;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Game Paused, Press escape to resume...", 240, 200);
    }
    
}
