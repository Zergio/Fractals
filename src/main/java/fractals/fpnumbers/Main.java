package fractals.fpnumbers;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

public class Main extends JPanel implements KeyListener {

    private static final int heightOfField = 100;

    private static final int widthOfField = 150;

    private static final int SIZE = 1;

    private static final Mandelbrot<FPDouble, FPDoubleFactory> fractal1 =
            new Mandelbrot<>(new FPDoubleFactory(), heightOfField, widthOfField);

    private static final Mandelbrot<FPBigDecimal, FPBigDecimalFactory> fractal2 =
            new Mandelbrot<>(new FPBigDecimalFactory(), heightOfField, widthOfField);

    private static final Mandelbrot<FPDecimal1, FPDecimal1Factory> fractal3 =
            new Mandelbrot<>(new FPDecimal1Factory(), heightOfField, widthOfField);

    private static final Mandelbrot<FPQuadruple, FPQuadrupleFactory> fractal4 =
            new Mandelbrot<>(new FPQuadrupleFactory(), heightOfField, widthOfField);

    private static final int width = widthOfField * SIZE;

    private static final int height = heightOfField * SIZE;

    public static void main(String[] args) {
        run(fractal2, new FPBigDecimalFactory());
    }

    private static <T extends FPNumber<T>, F extends FPNumberFactory<T>> void run(Fractal<T> fractal, F numberFactory) {
        JFrame frame = new JFrame("Fractals");

        FractalPanel<T> panel = new FractalPanel<>(fractal, width, height);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var xBeginning = numberFactory.createFPNumber("-0.89999890448998089999987999999999999754");//-2.0      -0.89999890448999
        var yBeginning = numberFactory.createFPNumber("-0.270133998879972897799999999998989934515");//-1.12     -0.27013399887998
             var xRange = numberFactory.createFPNumber("0.10000000000000");//2.5       0.00000000000007
             var yRange = numberFactory.createFPNumber("0.10000000000000");//2.33      0.00000000000007

        int start = 868;//868;
        int max = 869;
        DecimalFormat formatter = new DecimalFormat("0.00000000000000000");
        T coefficient = numberFactory.createFPNumber(formatter.format(Math.pow(1.1, start)));
        T multiplier = numberFactory.createFPNumber("1.1");
        for (int i = start; i < max; i++) {
            //T xStart = xBeginning.clone().add(numberFactory.createFPNumber("-0.025").multiply(numberFactory.createFPNumber(i)));
            T newXRange = xRange.clone().divide(coefficient);
            T newYRange = yRange.clone().divide(coefficient);
            generateAndPaint(fractal, panel, 1000, xBeginning.clone(), yBeginning.clone(), newXRange, newYRange);
            coefficient.multiply(multiplier);
            System.out.printf("%s -> zoom: %s, x-range: %s, y-range: %s%n", i+1, coefficient.toString(), newXRange.toString(), newYRange.toString());
        }
    }

    private static <T extends FPNumber<T>> void generateAndPaint(Fractal<T> fractal, FractalPanel<T> panel, int steps,
                                                                 T xBeginning, T yBeggining, T xRange, T yRange) {
        //long time = System.nanoTime();
        fractal.setup();
        fractal.process(steps, xBeginning, yBeggining, xRange, yRange);

        //System.out.println((double)(System.nanoTime() - time) / 1_000_000_000);

        panel.paintField(SIZE);

        //System.out.println((double)(System.nanoTime() - time) / 1_000_000_000);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyChar();
        if (key == 'w') {

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}