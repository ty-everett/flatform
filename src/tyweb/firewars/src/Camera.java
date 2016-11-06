package tyweb.firewars.src;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Camera {
    
    public static BufferedImage render(Game g, BufferedImage in, int x, int y){
        try{
            return in.getSubimage(x, y, g.getWidth(), g.getHeight());
        }catch(Exception e){
            e.printStackTrace();
            BufferedImage error = new BufferedImage(1024, 576, BufferedImage.TYPE_INT_RGB);
            Graphics grap = error.getGraphics();
            grap.drawString("Unfortunately, the camera can't render this frame. Try moving back to your previous position?", 100, 100);
            return error;
        }
    }
    
}
