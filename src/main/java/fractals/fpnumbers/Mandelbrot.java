package fractals.fpnumbers;

public class Mandelbrot<T extends FPNumber<T>, F extends FPNumberFactory<T>> implements Fractal<T> {

    private final ComplexNumber<T>[][] baseField;
    public final int[][][] colorField;
    private final F numberFactory;
    private final T heightOfField, widthOfField;
    private double[] colors;

    public Mandelbrot(F numberFactory, int heightOfField, int widthOfField) {
        this.numberFactory = numberFactory;
        this.widthOfField = numberFactory.createFPNumber(widthOfField);
        this.heightOfField = numberFactory.createFPNumber(heightOfField);
        baseField = new ComplexNumber[widthOfField][heightOfField];
        colorField = new int[widthOfField][heightOfField][3];
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
        var ZERO = numberFactory.createFPNumber(0L);

        //ComplexNumber<T> xy = new ComplexNumber<>(xBeginning.clone(), yBeginning.clone());

        long width = widthOfField.getLong();
        long height = heightOfField.getLong();

        //Decimal1 division is not implemented
        T realStep = xRange.divide(widthOfField);

        T imaginaryStep = yRange.divide(heightOfField);

        colors = new double[iterations];

        for (int i = 0; i < iterations; i++) {
            colors[i] = 255.0 * i / iterations;
        }

        Thread[] threads = new Thread[cores];
        for (int i = 0; i < cores; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> subProcess(cores, width, height, iterations, realStep.clone(),
                    imaginaryStep.clone(), yBeginning.clone(), xBeginning.clone(), finalI));
            threads[i] = thread;
            thread.start();
        }
        for (int i = 0; i < cores; i++) {
            threads[i].join();
        }
    }

    private void subProcess(int numberOfCores, long width, long height, int iterations,
                            T realStep, T imaginaryStep, T yBeginning, T xBeginning, int coreIndex) {
        for (int i = 0; i < width * height / numberOfCores; i++) {
            int j = i * numberOfCores + coreIndex;
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
                            (int) colors[(int)Math.min((abs - 4) / 81.0, iterations - 2)],
                            (int) colors[num], 255
                    };
                    break;
                }
            }
        }
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