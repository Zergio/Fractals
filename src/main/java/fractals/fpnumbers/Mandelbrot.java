package fractals.fpnumbers;

public class Mandelbrot<T extends FPNumber<T>, F extends FPNumberFactory<T>> implements Fractal<T> {

    private final ComplexNumber<T>[][] baseField;
    public final int[][][] colorField;
    private final F numberFactory;
    private final T heightOfField, widthOfField;

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

    public void process(int iterations, T xBeginning, T yBeginning, T xRange, T yRange) {
        var ZERO = numberFactory.createFPNumber(0L);

        ComplexNumber<T> xy = new ComplexNumber<>(xBeginning.clone(), yBeginning.clone());

        long width = widthOfField.getLong();
        long height = heightOfField.getLong();

        //Decimal1 division is not implemented
        T realStep = xRange.divide(widthOfField);

        T imaginaryStep = yRange.divide(heightOfField);

        double[] colors = new double[iterations];

//        for (int i = 0; i < iterations; i++) {
//            colors[i] = Math.pow(255.0, (double)i/iterations);
//        }

        for (int i = 0; i < iterations; i++) {
            colors[i] = 255.0 * i/iterations;
        }

        for (int i = 0; i < width; i++) {
            xy.setImaginary(yBeginning.clone());
            //System.out.println(xy.real().toString());
            //System.out.println(xy.im().toString());
            //System.out.println();
            for (int j = 0; j < height; j++) {
                ComplexNumber<T> current = baseField[i][j];
                colorField[i][j] = new int[]{0, 0, 0};
                for (int num = 0; num < iterations; num++) {
                    current.square();
                    current.add(xy);
                    var im = current.im()
                            .clone()
                            .square();
                    var r = current.real()
                            .clone()
                            .square()
                            .subtract(im);
                    T sum = r.clone().add(im);
                    long abs = Math.abs(sum.getLong());
                    if (abs >= 4) {
                        colorField[i][j] = new int[]{ (int) colors[(int)Math.min((abs - 4) / 81.0, iterations - 2)], (int) colors[num], 255};
                        break;
                    }
                }
                xy.im().add(imaginaryStep);
            }
            xy.real().add(realStep);
            //if ((i * 100) / width > ((i - 1) * 100) / width) {
            //    System.out.println("Progress: " + (i * 100) / width + "%, " + i);
            //}
        }
    }
}