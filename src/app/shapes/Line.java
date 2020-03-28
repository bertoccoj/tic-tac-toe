package app.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Line extends Shape {
  public int x1, y1, x2, y2;
  public Line(
    int x1,
    int y1,
    int x2,
    int y2,
    Color color,
    BasicStroke stroke
  ) {
      this.x1 = x1;
      this.x2 = x2;
      this.y1 = y1;
      this.y2 = y2;
      this.color = color;
      this.stroke = stroke;
  }

  @Override
  public void draw(Graphics2D g) {
    g.drawLine(this.x1, this.y1, this.x2, this.y2);
  }
}
