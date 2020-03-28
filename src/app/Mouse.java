package app;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class Mouse implements MouseMotionListener, MouseListener{
    private int x, y;

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public int getMouseX() {
        return this.x;
    }

    public int getMouseY() {
        return this.y;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) { }

    @Override
    public void mouseEntered(MouseEvent arg0) { }

    @Override
    public void mousePressed(MouseEvent arg0) { }

    @Override
    public void mouseReleased(MouseEvent arg0) { }

    @Override
    public void mouseDragged(MouseEvent arg0) { }

    @Override
    public void mouseExited(MouseEvent arg0) { }

    @Override
    public String toString() {
        return "x: " + this.getMouseX() + ", y: " + this.getMouseY();
    }

}
