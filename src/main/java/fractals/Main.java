package fractals;

import fractals.fpnumbers.*;
import fractals.ui.FractalPanel;

import javax.swing.JFrame;
import java.awt.event.*;

public class Main extends JFrame {

    static final int widthOfField = 320;

    static final int heightOfField = 240;

    static int stepCorrection = 5;

    static int maxSteps = 1200;

    static int cores = 16; //Runtime.getRuntime().availableProcessors();

    static int size = 1;

    static final Mandelbrot<FPDouble, FPDoubleFactory> fractal1 =
            new Mandelbrot<>(new FPDoubleFactory(), heightOfField, widthOfField, cores);

    static final Mandelbrot<FPBigDecimal, FPBigDecimalFactory> fractal2 =
            new Mandelbrot<>(new FPBigDecimalFactory(), heightOfField, widthOfField, cores);

    static final Mandelbrot<FPDecimal1, FPDecimal1Factory> fractal3 =
            new Mandelbrot<>(new FPDecimal1Factory(), heightOfField, widthOfField, cores);

    static final Mandelbrot<FPQuadruple, FPQuadrupleFactory> fractal4 =
            new Mandelbrot<>(new FPQuadrupleFactory(), heightOfField, widthOfField, cores);

    static final Mandelbrot<FPAPFloat, FPAPFloatFactory> fractal5 =
            new Mandelbrot<>(new FPAPFloatFactory(), heightOfField, widthOfField, cores);

    private final static FractalSettings<FPDouble, FPDoubleFactory> settings1 =
            new FractalSettings<>(fractal1, new FPDoubleFactory(), heightOfField, widthOfField,
//                    "-0.99090922573827030",
//                    "0.27759916268717680",
//                    "0.00000000000007963",
//                    "0.00000000000007963",
                    "-2.99090922573827030",
                    "-1.7224008373128232",
                    "4.0",
                    "4.0",
                    size, cores, 1, 0, 50);

    private final static FractalSettings<FPBigDecimal, FPBigDecimalFactory> settings2 =
            new FractalSettings<>(fractal2, new FPBigDecimalFactory(), heightOfField, widthOfField,
                    "-0.7464585640370714728020151719694677211356460643704035878034230594540471757998294197099276965444244443",
                    "-0.09607183553858121395093281081893726822082010091561231035430727573308878485144667538950203922583291324",
                    "0.0000000000000000000000000000002392130968880201238706966372074177214499050143026162508468875730780726198207879747335498228241523925",
                    "0.0000000000000000000000000000003189507958506934984942621829432236285998733524034883344625167641040968264277172996447330970988698567",
                    size, cores, 1, -500, 930);

    private final static FractalSettings<FPQuadruple, FPQuadrupleFactory> settings4 =
            new FractalSettings<>(fractal4, new FPQuadrupleFactory(), heightOfField, widthOfField,
                    "-2.0",//"-2.0", //"-0.89999890448998089999987999999999999754",
                    "-2.0",//"-1.12", //"-0.270133998879972897799999999998989934515",
                    "4.0",//"2.5",//"0.10000000000000",
                    "4.0",//"2.24",//"0.10000000000000");
                    size, cores, 1, 0, 50);

    private final static FractalSettings<FPAPFloat, FPAPFloatFactory> settings5 =
            new FractalSettings<>(fractal5, new FPAPFloatFactory(), heightOfField, widthOfField,
                    "-2.0000001",//"-2.0", //"-0.89999890448998089999987999999999999754",
                    "-2.0000001",//"-1.12", //"-0.270133998879972897799999999998989934515",
                    "4.0000001",//"2.5",//"0.10000000000000",
                    "4.0000001",//"2.24",//"0.10000000000000");
                    size, cores, 1, 0, 50);

    private final static FractalSettings<FPAPFloat, FPAPFloatFactory> settings6 =
            new FractalSettings<>(fractal5, new FPAPFloatFactory(), heightOfField, widthOfField,
                    "-0.7464585640370714728020151719694677211356460643704035878034230594540471757998294197099276965444244443",
                    "-0.09607183553858121395093281081893726822082010091561231035430727573308878485144667538950203922583291324",
                    "0.0000000000000000000000000000002392130968880201238706966372074177214499050143026162508468875730780726198207879747335498228241523925",
                    "0.0000000000000000000000000000003189507958506934984942621829432236285998733524034883344625167641040968264277172996447330970988698567",
                    size, cores, 1, -700, 1347);

    private final static FractalSettings<FPAPFloat, FPAPFloatFactory> settings7 =
            new FractalSettings<>(fractal5, new FPAPFloatFactory(), heightOfField, widthOfField,
                    "-2.99090922573827030",
                    "-1.7224008373128232",
                    "4.0",
                    "4.0",
                    size, cores, 1, 0, 50);

    //private final static FractalSettings<FPBigDecimal, FPBigDecimalFactory> settings = settings2;
    //private final static FractalSettings<FPBigDecimal, FPBigDecimalFactory> settings = settings2;
    private final static FractalSettings<FPDouble, FPDoubleFactory> settings = settings1;
    //private final static FractalSettings<FPQuadruple, FPQuadrupleFactory> settings = settings4;
    //private final static FractalSettings<FPAPFloat, FPAPFloatFactory> settings = settings6;
    //private final static FractalSettings<FPAPFloat, FPAPFloatFactory> settings = settings7;

    static <T extends Exception> void hideException(RunnableWithException<T> runnable) {
        try {
            runnable.run();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    static KeyListener keyListen = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyChar();
            //Move Left
            if (key == 'd') {
                Main.hideException(() -> generateAndPaint(settings.fractal, settings.panel, settings.steps,
                        settings.xBeginning
                                .add(settings.numberFactory.createFPNumber("0.1")
                                        .multiply(settings.xRange)),
                        settings.yBeginning,
                        settings.xRange.clone(),
                        settings.yRange.clone(),
                        settings.cores));
                printState();
            }
            //Move Right
            if (key == 'a') {
                Main.hideException(() -> generateAndPaint(settings.fractal, settings.panel, settings.steps,
                        settings.xBeginning
                                .add(settings.numberFactory.createFPNumber("-0.1")
                                        .multiply(settings.xRange)),
                        settings.yBeginning,
                        settings.xRange.clone(),
                        settings.yRange.clone(),
                        settings.cores));
                printState();
            }
            //Move Up
            if (key == 'w') {
                Main.hideException(() -> generateAndPaint(settings.fractal, settings.panel, settings.steps,
                        settings.xBeginning,
                        settings.yBeginning
                                .add(settings.numberFactory.createFPNumber("-0.1")
                                        .multiply(settings.yRange)),
                        settings.xRange.clone(),
                        settings.yRange.clone(),
                        settings.cores));
                printState();
            }
            //Move Down
            if (key == 's') {
                Main.hideException(() -> generateAndPaint(settings.fractal, settings.panel, settings.steps,
                        settings.xBeginning,
                        settings.yBeginning
                                .add(settings.numberFactory.createFPNumber("0.1")
                                .multiply(settings.yRange)),
                        settings.xRange.clone(),
                        settings.yRange.clone(),
                        settings.cores));
                printState();
            }

            //zoom in
            if (key == 'z') {
                Main.hideException(Main::zoomIn);
            }
            if (key == 'x') {
                Main.hideException(Main::zoomOut);
            }
            //Add more Steps
            if (key == 't') {
                stepCorrection += 10;
                Main.hideException(() -> generateAndPaint(settings.fractal, settings.panel, settings.steps,
                        settings.xBeginning,
                        settings.yBeginning,
                        settings.xRange.clone(),
                        settings.yRange.clone(),
                        settings.cores));
                printState();
            }
            //Add less Steps
            if (key == 'g') {
                stepCorrection -= Math.min(10, stepCorrection);
                Main.hideException(() -> generateAndPaint(settings.fractal, settings.panel, settings.steps,
                        settings.xBeginning,
                        settings.yBeginning,
                        settings.xRange.clone(),
                        settings.yRange.clone(),
                        settings.cores));
                printState();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    };

    private static void printState() {
        System.out.printf("-> zoom: %s, steps: %s,\n xBeginning: %s,\n yBeginning: %s,\n xRange: %s,\n yRange: %s\n---------------------------------------------------------------\n",
                -settings.zoomSteps, settings.steps, settings.xBeginning.toString(), settings.yBeginning.toString(), settings.xRange.toString(), settings.yRange.toString());
    }

    private static void zoomIn() throws InterruptedException {
        settings.zoom = settings.numberFactory.createFPNumber("0.909090909090909");
        settings.zoomSteps--;
        settings.steps = Math.min(Math.max(Math.max((int)Math.pow(-settings.zoomSteps, 1.2), 50), settings.steps) + stepCorrection, maxSteps);
        generateAndPaint(settings.fractal, settings.panel, settings.steps,
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
                settings.yRange.clone().multiply(settings.zoom),
                settings.cores);
        settings.xRange.multiply(settings.zoom);
        settings.yRange.multiply(settings.zoom);
        printState();
    }

    private static void zoomOut() throws InterruptedException {
        settings.zoom = settings.numberFactory.createFPNumber("1.1");
        settings.zoomSteps++;
        settings.steps = Math.min(Math.max(Math.max((int)Math.pow(-settings.zoomSteps, 1.2), 50), settings.steps) + stepCorrection, maxSteps);
        generateAndPaint(settings.fractal, settings.panel, settings.steps,
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
                settings.xRange.clone()
                        .subtract((settings.xRange.clone()
                                .subtract(settings.xRange.clone()
                                        .multiply(settings.zoom)))),
                settings.yRange.clone()
                        .subtract((settings.yRange.clone()
                                .subtract(settings.yRange.clone()
                                        .multiply(settings.zoom)))),
                settings.cores);
        settings.xRange
                .subtract((settings.xRange.clone()
                        .subtract(settings.xRange.clone()
                                .multiply(settings.zoom))));
        settings.yRange
                .subtract((settings.yRange.clone()
                        .subtract(settings.yRange.clone()
                                .multiply(settings.zoom))));
        printState();
    }

    public static void main(String[] args) throws InterruptedException {
        run(settings);
    }

    private static <T extends FPNumber<T>, F extends FPNumberFactory<T>> void run(FractalSettings<T, F> settings) throws InterruptedException {
        JFrame frame = new JFrame("Fractals");

        frame.add(settings.panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(keyListen);

        //generateAndPaint(settings.fractal, settings.panel, settings.steps, settings.xBeginning, settings.yBeginning, settings.xRange, settings.yRange, cores);
        //printState();

         int start = 0;
         int max = 330;
         for (int i = start; i < max; i++) {
             zoomIn();
         }
    }

    private static <T extends FPNumber<T>> void generateAndPaint(Fractal<T> fractal, FractalPanel<T> panel, int steps,
                                                                 T xBeginning, T yBeggining, T xRange, T yRange, int cores) throws InterruptedException {
        fractal.setup();
        fractal.process(steps, xBeginning, yBeggining, xRange, yRange, cores);
        panel.paintField(settings.SIZE);
    }
}