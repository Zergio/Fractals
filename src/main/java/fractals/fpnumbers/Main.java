package fractals.fpnumbers;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    private static final int heightOfField = 1000;

    private static final int widthOfField = 1500;

    private static final int SIZE = 1;

    private static final Mandelbrot<FPBigDecimal, FPBigDecimalFactory> fractal =
            new Mandelbrot<>(new FPBigDecimalFactory(), heightOfField, widthOfField);

//    private static final Mandelbrot<FPNumber1, FPNumber1Factory> fractal =
//            new Mandelbrot<>(new FPNumber1Factory(), heightOfField, widthOfField);

    private static final int width = widthOfField * SIZE;

    private static final int height = heightOfField * SIZE;

    private static final FractalPanel<FPBigDecimal> panel = new FractalPanel<>(fractal, width, height);

//    private static final FractalPanel<FPNumber1> panel = new FractalPanel<>(fractal, width, height);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractals");

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fractal.setup();
        fractal.process(20);
        panel.paintField(SIZE);
    }
}