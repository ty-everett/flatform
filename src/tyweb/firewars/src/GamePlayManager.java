package tyweb.firewars.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import tyweb.firewars.src.GameObjects.Enemy;
import tyweb.firewars.src.GameObjects.Player;
import tyweb.firewars.src.GameObjects.Block;
import tyweb.firewars.src.levels.Level1;

public class GamePlayManager {
    
    Game g;
    Player p;
    Handler h;
    
    public GamePlayManager(Game g){
        this.g = g;
    }
    
    public void tick(){
        
    }
    
    public void renderBG(Graphics g){
        g.drawImage(Textures.lvl1background.getSubimage(this.g.getCamX(), this.g.getCamY(), this.g.getWidth(), this.g.getHeight()), this.g.getCamX(), this.g.getCamY(), null);
    }
    
    public void render(Graphics g){
        g.drawImage(Textures.coin, this.g.getCamX() + 25, this.g.getCamY() + 25, null);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.setColor(Color.yellow);
        g.drawString(String.valueOf(p.getCoins()), this.g.getCamX() + 60, this.g.getCamY() + 50);
        for(int i = 0; i < p.getHealth(); i++){
            g.drawImage(Textures.heart, this.g.getCamX() + 120 + i*32, this.g.getCamY() + 25, null);
        }
        g.setColor(Color.blue);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Power", this.g.getCamX() + 50, this.g.getCamY() + 90);
        if(p.getAttackTimer() == 0){
            g.setColor(Color.green);
        }else{
            g.setColor(Color.yellow);
        }
        for(int i = 0; i < 10-Math.abs(p.getAttackTimer()/60); i++){
            g.fillRect(this.g.getCamX() + 120 + i*16, this.g.getCamY() + 72, 16, 16);
        }
    }
    public void setHandler(Handler h){
        this.h = h;
    }
    public void setPlayer(Player p){
        this.p = p;
    }
    
}
