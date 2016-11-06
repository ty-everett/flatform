package tyweb.firewars.src;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tyweb.firewars.src.GameObjects.BossEnemy;
import tyweb.firewars.src.GameObjects.Bullet;
import tyweb.firewars.src.GameObjects.Coin;
import tyweb.firewars.src.GameObjects.Enemy;
import tyweb.firewars.src.GameObjects.Player;
import tyweb.firewars.src.levels.Level1;
import tyweb.firewars.src.GameObjects.Block;

public class Game extends Canvas implements Runnable {
    
    public static final String title = "FlatForm";
    public static final String subtitle = "A Classic '80s platformer made in 2K16";
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 576;
    public int FPS, TPS;
    public boolean debug = false;
    public GameState state = GameState.title;
    public GameTitleScreen ts;
    public GameOverScreen os;
    public GameCreditsScreen cs;
    public GamePausedScreen ps;
    Handler h;
    Player p;
    Window w;
    KeyManager km;
    MouseManager mm;
    SpriteManager sm;
    SoundManager som;
    public int camX;
    public int camY;
    public double scaleX;
    public double scaleY;
    BufferedImage wholeLevel;
    public int levelWidth = 1024;
    public int levelHeight = 576;
    GamePlayManager gpm;
    BlockManager bm;
    int tickload = 0;
    int renderload = 0;
    double delta;
    GameState origState;
    
    //levels
    Level1 lvl1;
    
    public Game(){
        setMinimumSize(new Dimension(1024, 576));
        setPreferredSize(new Dimension(1024, 576));
        wholeLevel = new BufferedImage(levelWidth, levelHeight, BufferedImage.TYPE_INT_RGB);
        km = new KeyManager(this);
        mm = new MouseManager();
        sm = new SpriteManager();
        som = new SoundManager();
        new Textures(sm);
        
        //turn this stinkin' music off!
        //som.playSound("wav/music.wav");
        addKeyListener(km);
        addMouseListener(mm);
        addMouseMotionListener(mm);
        p = new Player(WIDTH/2, HEIGHT/2, 10, ID.player, GameState.level1, 2, km, som, this);
        p.setWidth(32);
        p.setHeight(64);
        gpm = new GamePlayManager(this);
        h = new Handler(this, mm, km, gpm, som, p);
        ts = new GameTitleScreen(this, h);
        os = new GameOverScreen(this, h);
        ps = new GamePausedScreen(this, h);
        cs = new GameCreditsScreen(this, h);
        p.setWidth(32);
        p.setHeight(64);
        camX = 0;
        camY = 0;
        w = new Window(this, title);
        scaleX = (double) w.getActualWidth() / (double) WIDTH;
        scaleY = (double) w.getActualHeight() / (double) HEIGHT;
    }
    
    public void start(){
        Thread t = new Thread(this);
        t.start();
    }
    
    public void run(){
       requestFocus();
       long lastTime = System.nanoTime();
       final double amountOfTicks = 60.0;
       double ns = 1000000000 / amountOfTicks;
       delta = 0;
       int updates = 0;
       int frames = 0;
       long timer = System.currentTimeMillis();
       while(true) {
           long now = System.nanoTime();
           delta += (now - lastTime) / ns;
           lastTime = now;
           if (delta >= 1){
               tick();
               updates++;
               delta--;
           }
           render();
           frames++;
           if (System.currentTimeMillis() - timer >= 1000){
               FPS = frames;
               TPS = updates;
               timer+=1000;
               frames = 0;
               updates = 0;
           }
       }
    }
    
    public void tick(){
        h.tick();
        if(state == GameState.title){
            ts.tick();
        }
    }
    
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            return;
        }
        Graphics g = wholeLevel.getGraphics();
        
        g.setColor(Color.black);
        g.fillRect(camX, camY, getWidth(), getHeight());
        
        if(state == GameState.title){
            ts.render(g);
        }else if(state.toString().startsWith("level")){
            gpm.renderBG(g);
        }else if(state == GameState.over){
            os.render(g);
        }else if(state == GameState.credits){
            cs.render(g);
        }else if(state == GameState.paused){
            ps.render(g);
        }
        
        h.render(g);
        
        if(state.toString().startsWith("level")){
            gpm.render(g);
        }
        
        if(debug){
            g.setColor(Color.yellow);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("FPS: " + FPS, camX + 25, camY + 100);
            g.drawString("jumping: " + p.getJumping(), camX + 25, camY + 125);
            g.drawString("falling: " + p.getFalling(), camX + 25, camY + 150);
            g.drawString("X: " + (int)(p.getX()/32), camX + 25, camY + 175);
            g.drawString("Y: " + (int)(p.getY()/32), camX + 75, camY + 175);
            g.drawString("Objects: " + h.getObjects().size(), camX + 25, camY + 200);
            g.drawString("Tickload: " + tickload, camX + 25, camY + 225);
            g.drawString("Renderload: " + renderload, camX + 25, camY + 250);
            g.drawString("Bullets: " + h.getBullets(), camX + 25, camY + 275);
        }
        
        g.dispose();
        Graphics g0 = bs.getDrawGraphics();
        g0.drawImage(Camera.render(this, wholeLevel, camX, camY), 0, 0, w.getActualWidth(), w.getActualHeight(), null);
        g0.dispose();
        bs.show();
    }
    
    public void keyPressed(KeyEvent k){
        switch (k.getKeyCode()) {
            case KeyEvent.VK_F3:
                debug = true;
                break;
            case KeyEvent.VK_ESCAPE:
                if(state.toString().startsWith("level")){
                    origState = state;
                    state = GameState.paused;
                }else if(state == GameState.paused){
                    state = origState;
                }
                break;
            default:
                p.handleInput(true, k);
                break;
        }
    }
    public void keyReleased(KeyEvent k){
        if(k.getKeyCode() == KeyEvent.VK_F3){
            debug = false;
        }else{
            //for(int i = 0; i < h.getObjects().size(); i++){
                //h.getObjects().get(i).handleInput(false, k);
            //}
            p.handleInput(false, k);
        }
    }
    public void reset(){
        restart();
    }
    public void restart() {
        try {
            final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            final File currentJar = new File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            String isjar;
            /* is it a jar file? */
            if(!currentJar.getName().endsWith(".jar")){
                isjar = "";
            }else{
                isjar = "-jar";
            }
            final ArrayList<String> command = new ArrayList<String>();
            command.add(javaBin);
            command.add(isjar);
            command.add(currentJar.getPath());
            
            final ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void setScaleX(double x){
        scaleX = x;
    }
    public void setScaleY(double y){
        scaleY = y;
    }
    public void setCamX(int x){
        camX = x;
    }
    public void setCamY(int y){
        camY = y;
    }
    public int getCamX(){
        return camX;
    }
    public int getCamY(){
        return camY;
    }
    public int getWidth(){
        return WIDTH;
    }
    public int getHeight(){
        return HEIGHT;
    }
    public int clamp(int val, int min, int max){
        if(val <= min) return min;
        if(val > min && val < max) return val;
        return max;
    }
    public double getScaleX(){
        return scaleX;
    }
    public double getScaleY(){
        return scaleY;
    }
    public void setLevelWidth(int lw){
        this.levelWidth = lw;
        wholeLevel = new BufferedImage(levelWidth, levelHeight, BufferedImage.TYPE_INT_RGB);
    }
    public void setLevelHeight(int lh){
        this.levelHeight = lh;
        wholeLevel = new BufferedImage(levelWidth, levelHeight, BufferedImage.TYPE_INT_RGB);
    }
    public int getLevelWidth(){
        return levelWidth;
    }
    public int getLevelHeight(){
        return levelHeight;
    }
    public void setTickLoad(int i){
        tickload = i;
    }
    public void setRenderLoad(int i){
        renderload = i;
    }
    public static void main(String[] args){
        new Game();
    }
}