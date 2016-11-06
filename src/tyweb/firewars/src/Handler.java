package tyweb.firewars.src;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import tyweb.firewars.src.GameObjects.Block;
import tyweb.firewars.src.GameObjects.Bullet;
import tyweb.firewars.src.GameObjects.Button;
import tyweb.firewars.src.GameObjects.Coin;
import tyweb.firewars.src.GameObjects.Creature;
import tyweb.firewars.src.GameObjects.GameObject;
import tyweb.firewars.src.GameObjects.Ladder;
import tyweb.firewars.src.GameObjects.Player;
import tyweb.firewars.src.levels.Level;
import tyweb.firewars.src.levels.Level1;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<GameObject>();

    Game g;
    MouseManager mm;
    KeyManager km;
    GamePlayManager gpm;
    SoundManager som;
    Player p;
    int cc = 0;
    int tickload = 0;
    int renderload = 0;
    int bullets = 0;
    
    
    public Handler(Game g, MouseManager mm, KeyManager km, GamePlayManager gpm, SoundManager som, Player p) {
        this.g = g;
        this.mm = mm;
        this.p = p;
        this.km = km;
        this.gpm = gpm;
        this.som = som;
        gpm.setHandler(this);
        gpm.setPlayer(p);
    }

    public void tick() {
        tickload = 0;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).isFor() == g.state) {
                GameObject tempObject = objects.get(i);
                if (tempObject.getX() > g.getCamX() - 200
                        && tempObject.getX() < g.getCamX() + g.getWidth() + 25
                        && tempObject.getY() > g.getCamY() - 50
                        && tempObject.getY() < g.getCamY() + g.getHeight() + 25) {
                    tempObject.tick();
                    tickload++;
                    if (tempObject instanceof Button) {
                        if (mm.getMouseStatus() == 1) {
                            if (mm.getX() >= (int) tempObject.getX() * g.getScaleX()
                                    && mm.getX() <= (int) (tempObject.getX() * g.getScaleX()) + (int) (tempObject.getWidth() * g.getScaleX())
                                    && mm.getY() >= (int) (tempObject.getY() * g.getScaleY())
                                    && mm.getY() <= (int) (tempObject.getY() * g.getScaleY()) + (int) (tempObject.getHeight() * g.getScaleY())) {
                                Button tempButton = (Button) objects.get(i);
                                if (g.state == GameState.title) {
                                    if (tempButton.getText().equals("Play!")) {
                                        g.state = GameState.level1;
                                        new Level1(this, p, g);
                                    } else if (tempButton.getText().equals("Credits")) {
                                        g.state = GameState.credits;
                                    } else if (tempButton.getText().equals("Quit")) {
                                        System.exit(0);
                                    }
                                }else if(g.state == GameState.over){
                                    if(tempButton.getText().equals("respawn")){
                                        g.reset();
                                        g.state = GameState.level1;
                                    }
                                    if(tempButton.getText().equals("Quit to title")){
                                        System.exit(0);
                                    }
                                }else if(g.state == GameState.credits){
                                    if(tempButton.getText().equals("Back")){
                                        g.state = GameState.title;
                                    }
                                }
                            }
                        }
                    }
                    if (tempObject instanceof Creature) {
                        Creature tempCreature = (Creature) tempObject;
                        if(!(tempCreature instanceof Player)){
                            if(tempCreature.getIsDead()) {
                                removeObject(tempCreature);
                                continue;
                            }
                        }
                            for (int j = 0; j < objects.size(); j++) {
                                if (objects.get(j).isFor() == g.state){
                                if (objects.get(j) instanceof Block) {
                                    Block tb = (Block) objects.get(j);
                                    if (tb.getRectangle().intersects(tempCreature.getBottom())) {
                                        tempCreature.setY(tb.getY() - tempCreature.getHeight() + 1);
                                        tempCreature.setVY(0);
                                        tempCreature.setFalling(false);
                                        tempCreature.setJumping(false);
                                    } else {
                                        tempCreature.setFalling(true);
                                    }
                                    if (tb.getRectangle().intersects(tempCreature.getTop())) {
                                        tempCreature.setVY(0);
                                        tempCreature.setY(tb.getY() + tb.getHeight() + 1);
                                    }
                                    if (tb.getRectangle().intersects(tempCreature.getRight())) {
                                        tempCreature.setX(tb.getX() - tempCreature.getWidth());
                                        tempCreature.jump();
                                    }
                                    if (tb.getRectangle().intersects(tempCreature.getLeft())) {
                                        tempCreature.setX(tb.getX() + tb.getWidth());
                                        tempCreature.jump();
                                    }
                                }
                                if(!(objects.get(j) instanceof Player) &&
                                        objects.get(j) instanceof Creature &&
                                        tempObject instanceof Player){
                                    Creature tc = (Creature) objects.get(j);
                                        if(tempCreature.getRectangle().intersects(tc.getRectangle())){
                                            tempCreature.hit();
                                        }
                                        if(tempCreature.getIsAttacking()){
                                            if(Math.abs(tc.getX() - tempCreature.getX()) < 100 &&
                                                    tempCreature.getY() - tc.getY() > -48 &&
                                                    tempCreature.getY() - tc.getY() < 128){
                                                if(tc.getX() < p.getX()){
                                                    tc.setFlinch(-24);
                                                }else{
                                                    tc.setFlinch(24);
                                                }
                                                tc.jump();
                                                tc.setHealth(tc.getHealth() - 2);
                                            }
                                        }
                                }
                            }
                            }
                    }
                    if(tempObject instanceof Bullet) {
                        Bullet tempBullet = (Bullet) tempObject;
                        for(int j = 0; j < objects.size(); j++){
                            if(objects.get(j) instanceof Block){
                                Block tempBlock = (Block) objects.get(j);
                                if(tempBlock.getRectangle().intersects(tempBullet.getRectangle())){
                                    objects.remove(tempBullet);
                                    bullets--;
                                    som.playSound("wav/delete.wav");
                                }
                            }
                            if(objects.get(j) instanceof Creature){
                                Creature tempCreature = (Creature) objects.get(j);
                                if(tempCreature.getRectangle().intersects(tempBullet.getRectangle())){
                                    if(tempCreature.getFlinch() == 0){
                                    objects.remove(tempBullet);
                                    bullets--;
                                    j--;
                                    bullets--;
                                    if(tempObject.getVX() < 0){
                                        tempCreature.setFlinch(-25);
                                    }else{
                                        tempCreature.setFlinch(25);
                                    }
                                    tempCreature.setHealth(tempCreature.getHealth() - 4);
                                    som.playSound("wav/Explosion2.wav");
                                }else{
                                    removeObject(tempObject);
                                    bullets--;
                                }
                                }
                            }
                        }
                    }
                    if(tempObject instanceof Ladder){
                        for(int j = 0; j < objects.size(); j++){
                            if(objects.get(j) instanceof Player){
                                if(p.getRectangle().intersects(tempObject.getRectangle())){
                                    if(!km.keys[KeyEvent.VK_S]){
                                        p.setVY(-5);
                                    }else{
                                        p.setVY(5);
                                    }
                                }
                            }
                        }
                    }
                    
                    
                }
                if(bullets < 0) bullets = 0;
                //coins
                if (tempObject instanceof Coin) {
                    if (Math.abs(tempObject.getX() - p.getX()) < p.getWidth()
                            && Math.abs(tempObject.getY() - p.getY()) < p.getHeight()) {
                        Coin c = (Coin) tempObject;
                        if (c.getVanishing() == false) {
                            c.setVanishing(true);
                            p.setCoins(p.getCoins() + 1);
                        }
                    }
                    if (tempObject.getX() < g.getCamX() && tempObject.getY() < g.getCamY()) {
                        objects.remove(i);
                        som.playSound("wav/coin.wav");
                    }
                }
            }

        }
        //camera
        if (p.getX() + p.getWidth() >= g.getCamX() + (int) (g.getWidth() * 0.6)) {
            if (p.getVX() > 0) {
                g.setCamX(g.getCamX() + p.getVX());
            }
            if (g.getCamX() + g.getWidth() >= g.getLevelWidth()) {
                g.setCamX(g.getLevelWidth() - g.getWidth());
            }
        }
        if (p.getX() <= g.getCamX() + g.getWidth() * 0.4) {
            if (p.getVX() < 0) {
                g.setCamX(g.getCamX() + p.getVX());
            }
            if (g.getCamX() < 0) {
                g.setCamX(0);
            }
        }
        if (p.getY() + p.getHeight() >= g.getCamY() + g.getHeight() * 0.7) {
            if (p.getVY() > 0) {
                g.setCamY(g.getCamY() + p.getVY());
            }
            if (g.getCamY() + g.getHeight() >= g.getLevelHeight()) {
                g.setCamY(g.getLevelHeight() - g.getHeight());
            }
        }
        if (p.getY() <= g.getCamY() + g.getHeight() * 0.3) {
            if (p.getVY() < 0) {
                g.setCamY(g.getCamY() + p.getVY());
            }
            if (g.getCamY() < 0) {
                g.setCamY(0);
            }
        }
        if(g.state.toString().startsWith("level")){
            if(p.getX() + p.getWidth() > g.getCamX()) g.setCamX(g.getCamX()+8);
            if(p.getY() + p.getHealth() > g.getCamY()) g.setCamY(g.getCamY()+8);
            if(p.getX() < g.getCamX() + g.getWidth()) g.setCamX(g.getCamX()-8);
            if(p.getY() < g.getCamY() + g.getHeight()) g.setCamY(g.getCamY()-8);
        }
        // firing
        if (p.getFiring()) {
            p.setFiring(false);
            if(bullets < 2){
            bullets++;
            som.playSound("wav/fire.wav");
            if(p.getFacing() > 0){
                addObject(new Bullet(p.getX() + 32, p.getY() + 20, p.getFacing() * 10, ID.bullet, g.state, 2));
            }else{
                addObject(new Bullet(p.getX() - 16, p.getY() + 20, p.getFacing() * 10, ID.bullet, g.state, 2));
            }
            }
        }
        if(p.getHealth() <= 0){
            g.setCamX(0);
            g.setCamY(0);
            g.state = GameState.over;
        }
        g.setTickLoad(tickload);
    }

    public void render(Graphics g) {
        renderload = 0;
        for(int r = 1; r <= 3; r++){
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).isFor() == this.g.state) {
                GameObject temp = objects.get(i);
                if (temp.getX() + temp.getWidth() >= this.g.getCamX()
                        && temp.getX() <= this.g.getCamX() + this.g.getWidth() + temp.getWidth()
                        && temp.getY() + temp.getHeight() >= this.g.getCamY()
                        && temp.getY() <= this.g.getCamY() + this.g.getHeight() + temp.getWidth()) {
                    if(temp.getRender() == r){
                        temp.render(g);
                        renderload++;
                    }
                } else if (temp.getID() == ID.bullet) {
                    removeObject(temp);
                    bullets--;
                }
            }
        }
        }
        this.g.setRenderLoad(renderload);
    }

    public void addObject(GameObject o) {
        objects.add(o);
    }

    public void removeObject(GameObject o) {
        objects.remove(o);
    }

    public LinkedList<GameObject> getObjects() {
        return objects;
    }
    public int getBullets(){
        return bullets;
    }
}
