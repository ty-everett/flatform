package tyweb.firewars.src;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {
    
    public int mouseStatus, x, y = 0;
    
    public void mousePressed(MouseEvent m){
        mouseStatus = 1;
    }
    public void mouseReleased(MouseEvent m){
        mouseStatus = 0;
    }
    public void mouseMoved(MouseEvent m){
        x = m.getX();
        y = m.getY();
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
    }
    
    public int getMouseStatus(){
        return mouseStatus;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
