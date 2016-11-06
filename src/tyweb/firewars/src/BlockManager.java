package tyweb.firewars.src;

import tyweb.firewars.src.GameObjects.Player;
import tyweb.firewars.src.GameObjects.Block;

public class BlockManager {
    
    Game g;
    Handler h;
    
    public BlockManager(Handler h, Game g){
        this.g = g;
        this.h = h;
    }
    
    public Block getBlock(int x, int y){
        for(int i = 0; i < h.getObjects().size(); i++){
            if(h.getObjects().get(i) instanceof Block){
                if(h.getObjects().get(i).getX() == x &&
                h.getObjects().get(i).getY() == y){
                    return (Block)h.getObjects().get(i);
                }
            }
        }
        return null;
    }
    
    public boolean testForBlock(int x1, int y1, int w1, int h1){
        for(int i = 0; i < h.getObjects().size(); i++){
            if(h.getObjects().get(i) instanceof Block){
                Block temp = (Block) h.getObjects().get(i);
                if(temp.getX() >= x1 && temp.getY() >= y1 && temp.getX() <= x1 + w1 && temp.getY() <= y1 + h1){
                    return true;
                }
            }
        }
        return false;
    }
    
}
