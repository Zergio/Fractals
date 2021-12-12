import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.math.MathContext;
import java.util.Arrays;
import javax.swing.JPanel;

public class DirectDraw extends JPanel {

    private BufferedImage canvas;

    public DirectDraw(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
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

    public void paintField(Complex1[][] field, int size, Fractal fractalType) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                Complex1 current = field[i][j];
                float[] hsb;
                if (i == field.length/2 && j == field[i].length/2) {
                    hsb = rgb2hsb(0,0,255);
                } else {
                    if (Math.abs(current.real()
                            .pow(2, Utils.CONTEXT)
                            .add(current.imag()
                                            .pow(2, Utils.CONTEXT),
                                    Utils.CONTEXT).intValue()) > 4) {
                        hsb = rgb2hsb(255, 255, 255);
                    } else {
                        hsb = rgb2hsb(0, 0, 0);
                    }
                }
                drawRect(Color.getHSBColor(hsb[0], hsb[1], hsb[2]), j * size, i * size, size, size);
            }
        }
        repaint();
    }

    public void drawRect(Color c, int x1, int y1, int width, int height) {
        int color = c.getRGB();
        canvas.setRGB(x1, y1, color);
        // Implement rectangle drawing
//        for (int x = x1; x < x1 + width; x++) {
//            for (int y = y1; y < y1 + height; y++) {
//                canvas.setRGB(x, y, color);
//            }
//        }
//        repaint();
    }
}
