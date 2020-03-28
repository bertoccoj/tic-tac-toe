package app.shapes;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public abstract class Shape {
  public Color color;
  public BasicStroke stroke;

  public abstract void draw(Graphics2D g);
}
