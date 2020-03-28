package app;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.function.Predicate;
import java.awt.event.ComponentEvent;
import java.awt.BasicStroke;

import app.shapes.Ellipse;
import app.shapes.Line;
import app.shapes.Text;

public abstract class Engine {

    static int width;
    static int height;
    private static JFrame window;
    private static Color currentColor = Color.BLACK;
    private static BasicStroke currentStrokeWeight = new BasicStroke(10);
    protected static int mouseX;
    protected static int mouseY;
    private Canvas canvas = new Canvas() {
        @Override
        public void componentResized(ComponentEvent e) {
            Dimension size = e.getComponent().getSize();
            width = (int) size.getWidth();
            height = (int) size.getHeight();
            onWindowResize();
        }
    };

    public Mouse m = new Mouse() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
            onMouseClick();
            canvas.repaint();
        }
    };

    abstract void onWindowResize();

    abstract void setup();

    abstract void draw();

    abstract void onMouseClick();

    public Engine() {
        this.init();
    }

    private void init() {
        canvas.addMouseMotionListener(m);
        canvas.addMouseListener(m);
        setup();
        show();
        while (true) {
            mouseX = this.m.getMouseX();
            mouseY = this.m.getMouseY();
            draw();
            canvas.repaint();
            sleep(100);
        }
    }

    void createWindow(final int w, final int h) {
        createWindow(w, h, false);
    }

    void createWindow(final int w, final int h, boolean resizable) {
        width = w;
        height = h;
        this.canvas.setPreferredSize(new Dimension(width, height));
        window = new JFrame();
        window.add(canvas);
        // window.setResizable(false);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static void show() {
        window.setLocation(getDesktopSize().width / 2 - (width / 2), getDesktopSize().height / 2 - (height / 2));
        window.setVisible(true);
    }

    void resetCanvas() {
        canvas.shapes.clear();
    }

    void text(String text, int x, int y) {
        canvas.shapes.add(new Text(text, x, y, currentColor));
    }

    void drawLine(int x1, int y1, int x2, int y2) {
        canvas.shapes.add(new Line(x1, y1, x2, y2, currentColor, currentStrokeWeight));
    }

    void drawEllipse(int x, int y, int width, int height) {
        canvas.shapes.add(new Ellipse(x, y, width, height, currentColor, currentStrokeWeight));
    }

    void background(int r, int g, int b) {
        canvas.bg = new Color(r, g, b);
    }

    void background(Color c) {
        canvas.bg = c;
    }

    static void strokeWeight(int pixels) {
        currentStrokeWeight = new BasicStroke(pixels);
    }

    static void color(Color c) {
        currentColor = c;
    }

    static void color(int r, int g, int b) {
        currentColor = new Color(r, g, b);
    }

    static void resetColor() {
        currentColor = Color.BLACK;
    }

    static Dimension getDesktopSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    // runnable: função anonima sem parametro sem retorno
    // consumer: função anonima com parametro sem retorno
    // predicate: função anonima com parametro e retorno

    void repeat(int times, int interval, Predicate<Integer> action) {
        for (int i = 0; i < times; i++) {
            if (action.test(i)) {
                break;
            }
            canvas.repaint();
            sleep(interval);
        }
    }

    static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
