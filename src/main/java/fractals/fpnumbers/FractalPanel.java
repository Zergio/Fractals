package fractals.fpnumbers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
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

    public static float[] rgb2hsb(int rgbR, int rgbG, int rgbB) {
        assert 0 <= rgbR && rgbR <= 255;
        assert 0 <= rgbG && rgbG <= 255;
        assert 0 <= rgbB && rgbB <= 255;
        int[] rgb = new int[] { rgbR, rgbG, rgbB };
        Arrays.sort(rgb);
        int max = rgb[2];
        int min = rgb[0];

        float hsbB = max/255.0f;
        float hsbS = max == 0 ? 0 : (max - min)/(float) max;

        float hsbH = 0;
        if (max == rgbR && rgbG >= rgbB) {
            hsbH = (rgbG - rgbB) * 60f/(max - min) + 0;
        } else if (max == rgbR && rgbG < rgbB) {
            hsbH = (rgbG - rgbB) * 60f/(max - min) + 360;
        } else if (max == rgbG) {
            hsbH = (rgbB - rgbR) * 60f/(max - min) + 120;
        } else if (max == rgbB) {
            hsbH = (rgbR - rgbG) * 60f/(max - min) + 240;
        }

        return new float[] { hsbH, hsbS, hsbB };
    }

    public static float[] RGBtoHSV(int r, int g, int b){

        float h, s, v;

        int min, max, delta;

        min = Math.min(Math.min(r, g), b);
        max = Math.max(Math.max(r, g), b);

        // V
        v = max;

        delta = max - min;

        // S
        if( max != 0 )
            s = delta / max;
        else {
            s = 0;
            h = -1;
            return new float[]{h,s,v};
        }

        // H
        if( r == max )
            h = ( g - b ) / delta; // between yellow & magenta
        else if( g == max )
            h = 2 + ( b - r ) / delta; // between cyan & yellow
        else
            h = 4 + ( r - g ) / delta; // between magenta & cyan

        h *= 60;    // degrees

        if( h < 0 )
            h += 360;

        return new float[]{h,s,v};
    }

    public void paintField(int size) {
        ComplexNumber<T>[][] field = fractal.getField();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                int[] temp = fractal.getColorField(i, j);
                float[] hsb = RGBtoHSV(temp[0], temp[1], temp[2]);
                hsb = Color.RGBtoHSB(temp[0], temp[1], temp[2], null);
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
