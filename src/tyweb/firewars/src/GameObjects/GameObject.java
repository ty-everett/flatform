package tyweb.firewars.src.GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import tyweb.firewars.src.ID;
import tyweb.firewars.src.GameState;

public abstract class GameObject {
    
    int x, y, vx, vy, w, h, render = 0;
    ID ID;
    GameState isFor;
    
    public GameObject(int x, int y, ID ID, GameState isFor, int render){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.isFor = isFor;
        this.render = render;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void handleInput(boolean in, KeyEvent ke);
    
    
    public int getRender(){
        return render;
    }
    public void setRender(int i){
        render = i;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public ID getID(){
        return ID;
    }
    public int getVX(){
        return vx;
    }
    public int getVY(){
        return vy;
    }
    public int getWidth(){
        return w;
    }
    public int getHeight(){
        return h;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setVX(int vx){
        this.vx = vx;
    }
    public void setVY(int vy){
        this.vy = vy;
    }
    public void setWidth(int w){
        this.w = w;
    }
    public void setHeight(int h){
        this.h = h;
    }
    public void setID(ID ID){
        this.ID = ID;
    }
    public GameState isFor(){
        return isFor;
    }
    public Rectangle getRectangle(){
        return new Rectangle(x, y, w, h);
    }
}