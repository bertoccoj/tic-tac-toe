package app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import app.shapes.Shape;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Canvas extends JPanel implements ComponentListener {

    public ArrayList<Shape> shapes = new ArrayList<Shape>();
    public Color bg;
    public int strokeWeight;

    public Canvas() {
        this.addComponentListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (bg != null) {
            g2d.setBackground(bg);
        }
        for (Shape shape: this.shapes) {
            if (shape.stroke != null) {
                g2d.setStroke(shape.stroke);
            }
            g2d.setColor((shape.color != null ? shape.color : Color.BLACK));
            shape.draw(g2d);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) { };

    @Override
    public void componentMoved(ComponentEvent e) { }

    @Override
    public void componentHidden(ComponentEvent e) { }

    @Override
    public void componentShown(ComponentEvent e) { }

}
