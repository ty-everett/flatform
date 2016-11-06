package tyweb.firewars.src.GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import tyweb.firewars.src.Game;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.ID;


public abstract class Creature extends GameObject {
    
    Game g;
    boolean isDead;
    boolean jumping = false;
    boolean falling = true;
    String pose = "stopped";
    final double gravity = 1;
    int facing = 1;
    int health;
    int flinch = 0;
    boolean hit = false;
    boolean isAttacking;
    
    public Creature(int x, int y, ID ID, GameState isFor, int r, Game g, int health) {
        super(x, y, ID, isFor, r);
        this.g = g;
        this.health = health;
    }

    @Override
    public void handleInput(boolean in, KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void jump(){
    if(!jumping && vy > -1){
                    jumping = true;
                    vy = -16;
                    y-=4;
                }
    }
    
    public void hit(){
        hit = true;
    }
    public boolean getIsAttacking(){
        return isAttacking;
    }
    public void setIsAttacking(boolean b){
        isAttacking = b;
    }
    public int getFlinch(){
        return flinch;
    }
    public void setFlinch(int i){
        flinch = i;
    }
    public int getFacing(){
        return facing;
    }
    public void setIsForState(GameState gs){
        this.isFor = gs;
    }
    public boolean getJumping(){
        return jumping;
    }
    public boolean getFalling(){
        return falling;
    }
    public Rectangle getBottom(){
        return new Rectangle(x + w/4, y+h/2, w/2, h/2);
    }
    public Rectangle getTop(){
        return new Rectangle(x + w/4, y, w/2, h/2);
    }
    public Rectangle getLeft(){
        return new Rectangle(x, y+3, w/2, h-6);
    }
    public Rectangle getRight(){
        return new Rectangle(x+w/2, y+3, w/2, h-6);
    }
    public void setFalling(boolean b){
        this.falling = b;
    }
    public void setJumping(boolean b){
        this.jumping = b;
    }
    public boolean getIsDead(){
        return isDead;
    }
    public void setIsDead(boolean isDead){
        this.isDead = isDead;
    }
    public void setPose(String p){
        pose = p;
    }
    public String getPose(){
        return pose;
    }
    public Rectangle getRectangle(){
        return new Rectangle(x, y, w, h);
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int i){
        this.health = i;
    }
    
}
