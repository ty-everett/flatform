package tyweb.firewars.src;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Animation {
    
    int speed;
    int counter = 0;
    BufferedImage[] frames;
    
    public Animation(int speed, BufferedImage... args){
        frames = new BufferedImage[args.length];
        this.speed = speed;
        for(int i = 0; i < args.length; i++){
            frames[i] = args[i];
        }
    }
    
    public void RunAnimation(int timer, Graphics g, int x, int y){
        if(timer % speed == 0) counter++;
        if(counter > frames.length - 1) counter = 0;
        g.drawImage(frames[counter], x, y, null);
    }
    public void RunAnimation(int timer, Graphics g, int x, int y, int w, int h){
        if(timer % speed == 0) counter++;
        if(counter > frames.length - 1) counter = 0;
        g.drawImage(frames[counter], x, y, w, h, null);
    }
    
    
}
