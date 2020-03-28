package app.shapes;

import java.awt.Graphics2D;
import java.awt.Color;

public class Text extends Shape {

  private String text;
  private int x;
  private int y;

  public Text(String text, int x, int y, Color c) {
    this.text = text;
    this.x = x;
    this.y = y;
    this.color = c;
  }

  @Override
  public void draw(Graphics2D g) {
    g.drawString(this.text, this.x, this.y);
  }

}
