package tyweb.firewars.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import tyweb.firewars.src.GameObjects.Button;


public class GameOverScreen {
    
    Button respawnButton;
    Button quitButton;
    Game g;
    
    public GameOverScreen(Game g, Handler h){
        this.g = g;
         respawnButton = new Button(0, 200, ID.button, GameState.over, 3, "respawn", 48);
         quitButton = new Button(0, 300, ID.button, GameState.over, 3, "Quit to title", 48);
         h.addObject(quitButton);
         h.addObject(respawnButton);
    }
    
    public void render(Graphics g){
        g.drawImage(Textures.background, this.g.getCamX(), this.g.getCamY(), 1024, 576, null);
        g.setFont(new Font("Arial", Font.BOLD, 72));
        g.setColor(Color.blue);
        g.drawString("You died!", this.g.getCamX()+512-(g.getFontMetrics().stringWidth("You died!")/2), this.g.getCamY()+100);
        respawnButton.setX(this.g.getCamX()+512 - respawnButton.getWidth()/2);
        quitButton.setX(this.g.getCamX()+512 - quitButton.getWidth()/2);
    }
    
}
