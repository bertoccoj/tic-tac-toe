package app.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Ellipse extends Shape {
  public int x, y, width, height;
  public Ellipse(
    int x,
    int y,
    int width,
    int height,
    Color color,
    BasicStroke stroke
  ) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
    this.stroke = stroke;
  }

  @Override
  public void draw(Graphics2D g) {
    g.drawOval(this.x, this.y, this.width, this.height);
  }
}
