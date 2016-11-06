package tyweb.firewars.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import tyweb.firewars.src.GameObjects.Button;


public class GameTitleScreen {
    
    Game g;
    Handler h;
    Button start, options, quit;
    
    public GameTitleScreen(Game g, Handler h){
        this.g = g;
        this.h = h;
        start = new Button(0, 0, ID.button, GameState.title, 3, "Play!", 0);
        h.addObject(start);
        options = new Button(0, 0, ID.button, GameState.title, 3, "Credits", 0);
        h.addObject(options);
        quit = new Button(0, 0, ID.button, GameState.title, 3, "Quit", 0);
        h.addObject(quit);
    }
    
    public void tick(){
        start.setX(g.getWidth()/2-start.getWidth()/2);
        start.setY(g.getHeight()/3);
        start.setSize(g.getHeight()/12);
        options.setX(g.getWidth()/2-options.getWidth()/2);
        options.setY(g.getHeight()/2);
        options.setSize(g.getHeight()/12);
        quit.setX(g.getWidth()/2-quit.getWidth()/2);
        quit.setY((int)(g.getHeight()/1.5));
        quit.setSize(g.getHeight()/12);
    }
    
    public void render(Graphics g){
        g.drawImage(Textures.background, 0, 0, null);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, (int)this.g.getHeight()/10));
        String str = Game.title + ":";
        g.drawString(str, this.g.getWidth()/2-g.getFontMetrics().stringWidth(str)/2, this.g.getHeight()/10);
        g.setColor(Color.blue);
        g.setFont(new Font("Arial", Font.BOLD, (int)this.g.getHeight()/15));
        str = Game.subtitle;
        g.drawString(str, this.g.getWidth()/2-g.getFontMetrics().stringWidth(str)/2, this.g.getHeight()/5);
        g.setFont(new Font("Arial", Font.BOLD, (int)this.g.getHeight()/30));
        str = "Copyright © 2016 Ty Everett, DO NOT DISTRIBUTE!";
        g.drawString(str, this.g.getWidth()/2-g.getFontMetrics().stringWidth(str)/2, (int)(this.g.getHeight()*0.9));
        str = "Version 0.4 Alpha. Build 0004";
        g.drawString(str, this.g.getWidth()/2-g.getFontMetrics().stringWidth(str)/2, (int)(this.g.getHeight()*0.85));
        str = "Visit tyweb.us.to/FlatForm for Updates and Help";
        g.drawString(str, this.g.getWidth()/2-g.getFontMetrics().stringWidth(str)/2, (int)(this.g.getHeight()*0.95));
    }
    
}
