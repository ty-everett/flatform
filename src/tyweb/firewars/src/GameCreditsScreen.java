package tyweb.firewars.src;

import java.awt.Font;
import java.awt.Graphics;
import tyweb.firewars.src.GameObjects.Button;

public class GameCreditsScreen {
    
    Game g;
    Handler h;
    
    Button backButton = new Button(0, 500, ID.button, GameState.credits, 3, "Back", 24);
    
    public GameCreditsScreen(Game g, Handler h){
        this.g = g;
        this.h = h;
        h.addObject(backButton);
    }
    public void tick(){
        
    }
    public void render(Graphics g){
        backButton.setX(512-backButton.getWidth()/2);
        g.drawImage(Textures.credits, 0, 0, null);
    }
    
}
