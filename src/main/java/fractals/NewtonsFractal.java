package fractals;

import fractals.Fractal;
import fractals.fpnumbers.ComplexNumber;
import fractals.fpnumbers.FPNumber;
import fractals.fpnumbers.FPNumberFactory;

public class NewtonsFractal <T extends FPNumber<T>, F extends FPNumberFactory<T>> implements Fractal<T> {

    private final ComplexNumber<T>[][] baseField;
    public final int[][][] colorField;
    private final F numberFactory;
    private final T heightOfField, widthOfField;
    private final T[] fraction;

    public NewtonsFractal(F numberFactory, int heightOfField, int widthOfField, T[] fraction) {
        this.numberFactory = numberFactory;
        this.fraction = fraction;
        this.widthOfField = numberFactory.createFPNumber(widthOfField);
        this.heightOfField = numberFactory.createFPNumber(heightOfField);
        baseField = new ComplexNumber[widthOfField][heightOfField];
        colorField = new int[widthOfField][heightOfField][3];
    }

    public ComplexNumber<T> step(ComplexNumber<T> z, ComplexNumber<T> c) {
        ComplexNumber<T> result = new ComplexNumber<>(numberFactory.createFPNumber(0L), numberFactory.createFPNumber(0L));
        for(int i = 0; i < fraction.length; i++) {
            result.add(deriveAndEvaluate(c, fraction[i]));
        }
        return result;
    }

    /**
     * Derives x ^ num, and evaluates it.
     * @param x input
     * @param num power
     * @return num * x ^ (num - 1)
     */
    private ComplexNumber<T> deriveAndEvaluate(ComplexNumber<T> x, T num) {
        ComplexNumber<T> number = new ComplexNumber<>(num, numberFactory.createFPNumber(0L));
        if ((x.real().getLong() == 0L && x.im().getLong() == 0L) || num.getLong() == 0L) {
            return new ComplexNumber<>(numberFactory.createFPNumber(0L), numberFactory.createFPNumber(0L));
        } else if (num.getLong() == 1L) {
            return new ComplexNumber<>(numberFactory.createFPNumber(1L), numberFactory.createFPNumber(0L));
        } else {
            ComplexNumber newX = x.clone();
            for (int i = 0; i < num.getLong() - 1; i++) {
                newX.multiply(x);
            }
            number.multiply(newX);
            return number;
        }
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

    public void process(int iterations, T xBeginning, T yBeginning, T xRange, T yRange, int cores) {
        var ZERO = numberFactory.createFPNumber(0L);

        ComplexNumber<T> xy = new ComplexNumber<>(xBeginning.clone(), yBeginning.clone());

        long width = widthOfField.getLong();
        long height = heightOfField.getLong();

        //Decimal1 division is not implemented
        T realStep = xRange.divide(widthOfField);

        T imaginaryStep = yRange.divide(heightOfField);

        double[] colors = new double[iterations];

        for (int i = 0; i < iterations; i++) {
            colors[i] = (255.0 / iterations) * i;
        }

        for (int i = 0; i < width; i++) {
            xy.setImaginary(yBeginning.clone());
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
                    if (Math.abs(r.getLong() + im.getLong()) >= 4) {
                        colorField[i][j] = new int[]{ (int) colors[num], (int) colors[num], 255};
                        break;
                    }
                }
                xy.im().add(imaginaryStep);
            }
            xy.real().add(realStep);
            System.out.println("Progress: " + (i * 100) / width + "%, " + i);
        }
    }

    @Override
    public void subProcess(int numberOfCores, long width, long height, int iterations, T realStep, T imaginaryStep, T yBeginning, T xBeginning, int coreIndex) {

    }
}