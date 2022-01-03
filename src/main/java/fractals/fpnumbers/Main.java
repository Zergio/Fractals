package fractals.fpnumbers;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    private static final int heightOfField = 1000;

    private static final int widthOfField = 1500;

    private static final int SIZE = 1;

    private static final Mandelbrot<FPDouble, FPDoubleFactory> fractal1 =
            new Mandelbrot<>(new FPDoubleFactory(), heightOfField, widthOfField);

    private static final Mandelbrot<FPBigDecimal, FPBigDecimalFactory> fractal2 =
            new Mandelbrot<>(new FPBigDecimalFactory(), heightOfField, widthOfField);

    private static final Mandelbrot<FPDecimal1, FPDecimal1Factory> fractal3 =
            new Mandelbrot<>(new FPDecimal1Factory(), heightOfField, widthOfField);

    private static final int width = widthOfField * SIZE;

    private static final int height = heightOfField * SIZE;

    public static void main(String[] args) {
        run(fractal1);
    }

    private static <T extends FPNumber<T>> void run(Fractal<T> fractal) {
        JFrame frame = new JFrame("Fractals");

        FractalPanel<T> panel = new FractalPanel<>(fractal, width, height);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fractal.setup();

        long time = System.nanoTime();

        fractal.process(60);

        System.out.println((double)(System.nanoTime() - time) / 1_000_000_000);

        panel.paintField(SIZE);

        System.out.println((double)(System.nanoTime() - time) / 1_000_000_000);
    }
}