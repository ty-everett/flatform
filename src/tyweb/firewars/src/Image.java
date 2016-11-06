package tyweb.firewars.src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Image {
    
    public static BufferedImage load(String image){
        try {
            return ImageIO.read(new File("/img/" + image));
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("Image not loaded! /img/" + image);
        return null;
    }
    
}
