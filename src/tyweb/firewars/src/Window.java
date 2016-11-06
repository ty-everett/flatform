package tyweb.firewars.src;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;


public class Window extends ComponentAdapter {
    
    JFrame f;
    
    public Window(Game g, String title){
        f = new JFrame(title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.add(g);
        f.pack();
        f.setMinimumSize(new Dimension(f.getWidth() + 1, f.getHeight() + 1));
        f.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        f.addComponentListener(new ComponentAdapter() 
{  
        public void componentResized(ComponentEvent evt) {
            g.setScaleX((double)getActualWidth()/1024.0);
            g.setScaleY((double)getActualHeight()/576.0);
        }
});
        f.setVisible(true);
        g.start();
    }

    public JFrame getWindow(){
        return f;
    }
    public int getActualWidth(){
        return f.getWidth() - f.getInsets().left - f.getInsets().right;
    }
    public int getActualHeight(){
        return f.getHeight() - f.getInsets().top - f.getInsets().bottom;
    }
    
}
