package fractals;

import fractals.fpnumbers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Mandelbrot<T extends FPNumber<T>, F extends FPNumberFactory<T>> implements Fractal<T> {

    private final ComplexNumber<T>[][] baseField;
    public final int[][][] colorField;
    private final F numberFactory;
    private final T heightOfField, widthOfField;
    private double[] colors;
    private final ExecutorService pool;

    public Mandelbrot(F numberFactory, int heightOfField, int widthOfField, int cores) {
        this.numberFactory = numberFactory;
        this.widthOfField = numberFactory.createFPNumber(widthOfField);
        this.heightOfField = numberFactory.createFPNumber(heightOfField);
        baseField = new ComplexNumber[widthOfField][heightOfField];
        colorField = new int[widthOfField][heightOfField][3];
        pool = Executors.newFixedThreadPool(cores);
    }

    public ComplexNumber<T> step(ComplexNumber<T> z, ComplexNumber<T> c) {
        z.square();
        z.add(c);
        return z;
    }

    public void setup() {
        for (int i = 0; i < baseField.length; i++) {
            for (int j = 0; j < baseField[i].length; j++) {
                baseField[i][j] = new ComplexNumber<>(numberFactory.createFPNumber(0L), numberFactory.createFPNumber(0L));
            }
        }
    }

    public ComplexNumber<T>[][] getField() {
        return baseField;
    }

    @Override
    public int[] getColorField(int i, int j) {
        return colorField[i][j];
    }

    public void process(int iterations, T xBeginning, T yBeginning, T xRange, T yRange, int cores) throws InterruptedException {
        int width = (int)widthOfField.getLong();
        int height = (int)heightOfField.getLong();

        //Decimal1 division is not implemented
        T realStep = xRange.divide(widthOfField);

        T imaginaryStep = yRange.divide(heightOfField);

        colors = new double[iterations];

        for (int i = 0; i < iterations; i++) {
            colors[i] = 255.0 * i / iterations;
        }

        long ns = System.nanoTime();
        List<ProcessingThread<T>> threads = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(cores);

        for (int i = 0; i < cores; i++) {
            ProcessingThread<T> thread = new ProcessingThread<>(this, cores, i, width, height, latch);
            thread.setPriority(10);
            threads.add(thread);
            //if (i == 3) break;
        }
        for (int i = 0; i < cores; i++) {
            threads.get(i).set(iterations, realStep, imaginaryStep, xBeginning, yBeginning);
            //if (i == 3) break;
            threads.get(i).start();
        }
        //pool.invokeAll(threads);
        System.out.println((System.nanoTime() - ns) / 1_000_000_000.0);
        latch.await();
        System.out.println((System.nanoTime() - ns) / 1_000_000_000.0);
    }

    public void subProcess(int numberOfCores, long width, long height, int iterations,
                            T realStep, T imaginaryStep, T xBeginning, T yBeginning, int coreIndex) {
        //long ns = System.nanoTime();
        long addition = coreIndex * width * height / numberOfCores;
        for (int i = 0; i < width * height / numberOfCores; i++) {
            long j = i + addition;
            int x = (int) (j % width);
            int y = (int) (j / width);
            ComplexNumber<T> current = baseField[x][y];
            colorField[x][y] = new int[]{0, 0, 0};
            for (int num = 0; num < iterations; num++) {
                current.square();
                current.add(new ComplexNumber<>(
                        realStep.clone()
                                .multiply(x)
                                .add(xBeginning),
                        imaginaryStep.clone()
                                .multiply(y)
                                .add(yBeginning)));
                var im = current.im().clone()
                        .square();
                var r = current.real().clone()
                        .square()
                        .subtract(im);
                T sum = r.clone().add(im);
                long abs = Math.abs(sum.getLong());
                if (abs >= 4) {
                    colorField[x][y] = new int[]{
                            (int) colors[(int)Math.min((abs - 4) / 64.0, iterations - 2)],
                            (int) colors[num], 255
                    };
                    break;
                }
            }
        }
        //System.out.println(coreIndex + ": " + ns + " @ " + (System.nanoTime() - ns));
    }
    /*
    private void subProcess(int startI, int startJ, int skipEvery, long width, long height, int iterations,
                            ComplexNumber<T> xy, T realStep, T imaginaryStep, T xRange, T yRange, T yBeginning) {
        for (int i = startI; i < width; i ++) {
            xy.setImaginary(yBeginning.clone()
                    .add(imaginaryStep.clone()
                            .multiply(startJ)));
            for (int j = startJ; j < height; j += skipEvery) {
                ComplexNumber<T> current = baseField[i][j];
                colorField[i][j] = new int[]{0, 0, 0};
                for (int num = 0; num < iterations; num++) {
                    current.square();
                    current.add(xy);
                    var im = current.im().clone()
                            .square();
                    var r = current.real().clone()
                            .square()
                            .subtract(im);
                    T sum = r.clone().add(im);
                    long abs = Math.abs(sum.getLong());
                    if (abs >= 4) {
                        colorField[i][j] = new int[]{
                                (int) colors[(int)Math.min((abs - 4) / 81.0, iterations - 2)],
                                (int) colors[num], 255
                        };
                        break;
                    }
                }
                xy.im().add(imaginaryStep);
            }
            xy.real().add(realStep);
        }
    }*/
}