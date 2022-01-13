package fractals.fpnumbers;

import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class Main extends JFrame {

    static final int heightOfField = 750;

    static final int widthOfField = 1500;

    static int steps = 60;

    static int n = 0;

    static final Mandelbrot<FPDouble, FPDoubleFactory> fractal1 =
            new Mandelbrot<>(new FPDoubleFactory(), heightOfField, widthOfField);

    static final Mandelbrot<FPBigDecimal, FPBigDecimalFactory> fractal2 =
            new Mandelbrot<>(new FPBigDecimalFactory(), heightOfField, widthOfField);

    static final Mandelbrot<FPDecimal1, FPDecimal1Factory> fractal3 =
            new Mandelbrot<>(new FPDecimal1Factory(), heightOfField, widthOfField);

    static final Mandelbrot<FPQuadruple, FPQuadrupleFactory> fractal4 =
            new Mandelbrot<>(new FPQuadrupleFactory(), heightOfField, widthOfField);

    private final static FractalSettings<FPDouble, FPDoubleFactory> settings =
            new FractalSettings<>(fractal1, new FPDoubleFactory(), heightOfField, widthOfField,
                    "-2.0", //"-0.89999890448998089999987999999999999754",
                    "-1.12", //"-0.270133998879972897799999999998989934515",
                    "2.5",//"0.10000000000000",
                    "2.24",//"0.10000000000000");
                    1,
                    1);


    static KeyListener keyListen = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyChar();
            //Move Left
            if (key == 'd') {
                generateAndPaint(settings.fractal, settings.panel, steps,
                        settings.xBeginning
                                .add(settings.numberFactory.createFPNumber("0.1")
                                        .multiply(settings.xRange)),
                        settings.yBeginning,
                        settings.xRange.clone(),
                        settings.yRange.clone());
            }
            //Move Right
            if (key == 'a') {
                generateAndPaint(settings.fractal, settings.panel, steps,
                        settings.xBeginning
                                .add(settings.numberFactory.createFPNumber("-0.1")
                                        .multiply(settings.xRange)),
                        settings.yBeginning,
                        settings.xRange.clone(),
                        settings.yRange.clone());
            }
            //Move Up
            if (key == 'w') {
                generateAndPaint(settings.fractal, settings.panel, steps,
                        settings.xBeginning,
                        settings.yBeginning
                                .add(settings.numberFactory.createFPNumber("-0.1")
                                        .multiply(settings.yRange)),
                        settings.xRange.clone(),
                        settings.yRange.clone());
            }
            //Move Down
            if (key == 's') {
                generateAndPaint(settings.fractal, settings.panel, steps,
                        settings.xBeginning,
                        settings.yBeginning
                                .add(settings.numberFactory.createFPNumber("0.1")
                                .multiply(settings.yRange)),
                        settings.xRange.clone(),
                        settings.yRange.clone());
            }

            //zoom in
            if (key == 'z') {
                settings.zoom.divide(settings.numberFactory.createFPNumber("1.1"));
                n--;
                steps = Math.max(Math.max((int)Math.pow(-n, 1.7), 60), steps);
                generateAndPaint(settings.fractal, settings.panel, steps,
                        settings.xBeginning
                                .add((settings.xRange.clone()
                                        .subtract(settings.xRange.clone()
                                                .multiply(settings.zoom)))
                                        .divide(settings.numberFactory.createFPNumber(2L))),
                        settings.yBeginning
                                .add((settings.yRange.clone()
                                        .subtract(settings.yRange.clone()
                                                .multiply(settings.zoom)))
                                        .divide(settings.numberFactory.createFPNumber(2L))),
                        settings.xRange.clone().multiply(settings.zoom),
                        settings.yRange.clone().multiply(settings.zoom));
                settings.xRange.multiply(settings.zoom);
                settings.yRange.multiply(settings.zoom);
            }
            //zoom out * buggy
            if (key == 'x') {
                settings.zoom.multiply(settings.numberFactory.createFPNumber("1.1"));
                n++;
                steps = Math.max(Math.max((int)Math.pow(-n, 1.7), 60), steps);
                generateAndPaint(settings.fractal, settings.panel, steps,
                        settings.xBeginning
                                .add(settings.xRange.clone()
                                        .subtract(settings.xRange.clone()
                                                .multiply(settings.zoom))
                                        .divide(settings.numberFactory.createFPNumber(2L))),
                        settings.yBeginning
                                .add(settings.yRange.clone()
                                        .subtract(settings.yRange.clone()
                                                .multiply(settings.zoom))
                                        .divide(settings.numberFactory.createFPNumber(2L))),
                        settings.xRange.clone().multiply(settings.zoom),
                        settings.yRange.clone().multiply(settings.zoom));
                settings.xRange.multiply(settings.zoom);
                settings.yRange.multiply(settings.zoom);
            }
            //Add more Steps
            if (key == 't') {
                steps += 10;
                generateAndPaint(settings.fractal, settings.panel, steps,
                        settings.xBeginning,
                        settings.yBeginning,
                        settings.xRange.clone(),
                        settings.yRange.clone());
            }
            //Add less Steps
            if (key == 'g') {
                steps -= 10;
                generateAndPaint(settings.fractal, settings.panel, steps,
                        settings.xBeginning,
                        settings.yBeginning,
                        settings.xRange.clone(),
                        settings.yRange.clone());
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    };

    public static void main(String[] args) {
        run(settings.fractal, settings.numberFactory);
    }

    private static <T extends FPNumber<T>, F extends FPNumberFactory<T>> void run(Fractal<T> fractal, F numberFactory) {
        JFrame frame = new JFrame("Fractals");

        frame.add(settings.panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(keyListen);
        int start = 1;//868;
        int max = 2;
        DecimalFormat formatter = new DecimalFormat("0.00000000000000000");
        T coefficient = numberFactory.createFPNumber(formatter.format(Math.pow(1.1, start)));
        T multiplier = numberFactory.createFPNumber("1.1");
        for (int i = start; i < max; i++) {
            //T xStart = xBeginning.clone().add(numberFactory.createFPNumber("-0.025").multiply(numberFactory.createFPNumber(i)));
            T newXRange = (T) settings.xRange.clone();//.divide((FPDouble) numberFactory.createFPNumber(2L * i));
            T newYRange = (T) settings.yRange.clone();//.divide((FPDouble) numberFactory.createFPNumber(2L * i));
            FractalPanel<T> panel = (FractalPanel<T>) settings.panel;
            generateAndPaint(fractal, panel, steps, (T) settings.xBeginning.clone(), (T) settings.yBeginning.clone(), newXRange, newYRange);
            coefficient.multiply(multiplier);
            System.out.printf("%s -> zoom: %s, x-range: %s, y-range: %s%n", i + 1, coefficient.toString(), newXRange.toString(), newYRange.toString());
        }
    }

    private static <T extends FPNumber<T>> void generateAndPaint(Fractal<T> fractal, FractalPanel<T> panel, int steps,
                                                                 T xBeginning, T yBeggining, T xRange, T yRange) {
        //long time = System.nanoTime();
        fractal.setup();
        fractal.process(steps, xBeginning, yBeggining, xRange, yRange);

        //System.out.println((double)(System.nanoTime() - time) / 1_000_000_000);

        panel.paintField(settings.SIZE);

        //System.out.println((double)(System.nanoTime() - time) / 1_000_000_000);
    }
}