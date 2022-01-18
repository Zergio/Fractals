package fractals.ui;

import fractals.Fractal;
import fractals.fpnumbers.ComplexNumber;
import fractals.fpnumbers.FPNumber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class FractalPanel<T extends FPNumber<T>> extends JPanel {

    private final BufferedImage canvas;
    private final Fractal<T> fractal;

    public FractalPanel(Fractal<T> fractal, int width, int height) {
        this.fractal = fractal;
        this.canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillCanvas(Color.WHITE);
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }

    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }

    public void paintField(int size) {
        ComplexNumber<T>[][] field = fractal.getField();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int[] temp = fractal.getColorField(i, j);
                float[] hsb = Color.RGBtoHSB(temp[0], temp[1], temp[2], null);
                drawRect(Color.getHSBColor(hsb[0], hsb[1], hsb[2]),
                        i * size, j * size, size, size);
            }
        }
        repaint();
    }

    public void drawRect(Color c, int x1, int y1, int width, int height) {
        int color = c.getRGB();
        // Implement rectangle drawing
        for (int x = x1; x < x1 + width; x++) {
            for (int y = y1; y < y1 + height; y++) {
                canvas.setRGB(x, y, color);
            }
        }
    }
}
