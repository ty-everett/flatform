package tyweb.firewars.src.GameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.text.Keymap;
import tyweb.firewars.src.Animation;
import tyweb.firewars.src.BlockManager;
import tyweb.firewars.src.Game;
import tyweb.firewars.src.GameState;
import tyweb.firewars.src.ID;
import tyweb.firewars.src.KeyManager;
import tyweb.firewars.src.SoundManager;
import tyweb.firewars.src.Textures;


public class Player extends Creature {
    
    KeyManager km;
    Game g;
    int health;
    boolean isDead;
    boolean jumping = false;
    boolean falling = true;
    String pose = "stopped";
    final double gravity = 1;
    int facing = 1;
    BlockManager bm;
    int coins;
    boolean firing = false;
    SoundManager som;
    int timer = 0;
    Animation walk = new Animation(10, Textures.player0, Textures.player1, Textures.player2, Textures.player3, Textures.player4);
    Animation walkLeft = new Animation(10, Textures.player5, Textures.player6, Textures.player7, Textures.player8, Textures.player9);
    int attackTimer = 0;
    int animationTimer = 0;
    
    public Player(int x, int y, int he, ID ID, GameState isFor, int r, KeyManager km, SoundManager som, Game g) {
        super(x, y, ID, isFor, r, g, he);
        this.km = km;
        this.g = g;
        isDead = false;
        coins = 0;
        this.som = som;
    }

    @Override
    public void tick() {
        x+=vx;
        y+=vy;
        if(falling || jumping){
            vy+=gravity;
        }
        if(flinch > 0){
            vx = flinch;
            flinch--;
        }else if(flinch < 0){
            vx = flinch;
            flinch++;
        }else{
            if(health <= 0) isDead = true;
        }
        timer++;
        if(attackTimer > 0) attackTimer--;
        if(timer == 80){
            timer = 0;
            if(hit){
                setHealth(getHealth() - 1);
                som.playSound("wav/Explosion2.wav");
                hit = false;
            }
        }
        animationTimer++;
        if(vy > 100) vy = 100;
        if(x < 0) x = 1;
        if(y < 0) y = 1;
        if(x+w+1 > g.getLevelWidth()) x = g.getLevelWidth()-w-1;
        if(y+h+1 > g.getLevelHeight()) y = g.getLevelHeight()-h-1;
    }

    @Override
    public void render(Graphics g) {
        if(attackTimer > 590){
            int i = Math.abs(attackTimer-600)*12;
            g.setColor(Color.yellow);
            g.fillOval((x-i/2)+16, (y-i/2)+32, i, i);
        }
        if(vx > 0){
            walk.RunAnimation(animationTimer, g, x, y);
        }else if(vx < 0){
            walkLeft.RunAnimation(animationTimer, g, x, y);
        }else if(facing < 0){
            g.drawImage(Textures.player5, x, y, null);
        }else{
            g.drawImage(Textures.player0, x, y, null);
        }
    }
    
    @Override
    public void handleInput(boolean in, KeyEvent ke) {
        if(in){
            if(ke.getKeyCode() == KeyEvent.VK_W){
                if(!jumping && vy > -1){
                    jumping = true;
                    som.playSound("wav/jump.wav");
                    vy = -16;
                    y-=4;
                }
            }
            if(ke.getKeyCode() == KeyEvent.VK_E){
                if(attackTimer == 0){
                    isAttacking = true;
                    attackTimer = 600;
                    som.playSound("wav/Explosion2.wav");
                }
            }
            if(ke.getKeyCode() == KeyEvent.VK_Q){
                if(attackTimer == 0){
                    setHealth(getHealth() + 2);
                    som.playSound("wav/Powerup.wav");
                    if(getHealth() > 10) setHealth(10);
                    attackTimer = 600;
                }
            }
            if(ke.getKeyCode() == KeyEvent.VK_SPACE){
                firing = true;
            }
            if(ke.getKeyCode() == KeyEvent.VK_A){
                vx=-5;
                facing = -1;
            }
            if(ke.getKeyCode() == KeyEvent.VK_S){
                vy=5;
            }
            if(ke.getKeyCode() == KeyEvent.VK_D){
                vx=5;
                facing = 1;
            }
        }else{
            if(ke.getKeyCode() == KeyEvent.VK_W){
                vy = 0; //DEBUG !Remove!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
            if(ke.getKeyCode() == KeyEvent.VK_A){
                if(vx < 0) vx=0;
            }
            if(ke.getKeyCode() == KeyEvent.VK_S){
                if(vy > 0) vy=0;
            }
            if(ke.getKeyCode() == KeyEvent.VK_D){
                if(vx > 0) vx=0;
            }
            if(ke.getKeyCode() == KeyEvent.VK_E){
                isAttacking = false;
            }
        }
    }
    public int getAttackTimer(){
        return attackTimer;
    }
    public void setAttackTimer(int i){
        attackTimer = i;
    }
    public int getFacing(){
        return facing;
    }
    public void setIsForState(GameState gs){
        this.isFor = gs;
    }
    public void setFiring(boolean b){
        firing = b;
    }
    public boolean getFiring(){
        return firing;
    }
    public int getCoins(){
        return coins;
    }
    public void setCoins(int c){
        coins = c;
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
}