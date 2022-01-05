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

        for (int i = 0; i < iterations; i++) {
            //if (iterations >= 255.0) {
            //    colors[i] = i % 255;
            //} else {
                colors[i] = (255.0 / iterations) * i;
            //}
            //colors[i] = (int)Math.pow(Math.pow(255, 1.0/iterations), i);
        }
        for (int i = 0; i < width; i++) {
            //var x = xBeginning.add((xRange.multiply(i)).divide(heightOfField));
            xy.setImaginary(yBeginning.clone());
            for (int j = 0; j < height; j++) {
                //var y = yBeginning.add((yRange.multiply(j)).divide(widthOfField));
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
/*
    public void processOLD(int iterations) {
        var decHeight = numberFactory.createFPNumber(heightOfField);
        var decWidth = numberFactory.createFPNumber(widthOfField);
        var zoomX = numberFactory.createFPNumber("0");//0.5    //30
        var offsetX = numberFactory.createFPNumber("0");//0.5  //0.7
        var zoomY = numberFactory.createFPNumber("0");//0.5    //30
        var offsetY = numberFactory.createFPNumber("0");//0.0  //0.3
//        FixedPoint two = new FixedPoint("2.0");
//        decWidth.multiply(zoomX);
//        two.divide(decWidth);
        var zoomXByWidth = numberFactory.createFPNumber("0.00075");
//        two = new FixedPoint("2.0");
//        decHeight.multiply(zoomY);
//        two.divide(decHeight);
        var zoomYByHeight = numberFactory.createFPNumber("0.001");
        int startI = -widthOfField / 2;
        int startJ = -heightOfField / 2;
        for (int i = startI; i < startI + widthOfField; i++) {
            var x = numberFactory.createFPNumber(i)
                    .multiply(zoomXByWidth)
                    .subtract(offsetX);
            for (int j = startJ; j < startJ + heightOfField; j++) {
                var y = numberFactory.createFPNumber(j)
                        .multiply(zoomYByHeight)
                        .subtract(offsetY);
                ComplexNumber<T> xy = new ComplexNumber<T>(x, y);
                colorField[j - startJ][i - startI][0] = 0;
                colorField[j - startJ][i - startI][1] = 0;
                colorField[j - startJ][i - startI][2] = 0;
                ComplexNumber<T> current = baseField[j - startJ][i - startI];
                for (int num = 0; num < iterations; num++) {
                    current.square();
                    current.subtract(xy);
                    var im = current.im()
                            .clone()
                            .square();
                    var r = current.real()
                            .clone()
                            .square()
                            .subtract(im);
                    if (Math.abs(r.getScaledInteger() + im.getScaledInteger()) >= 4) {
                        colorField[j - startJ][i - startI][0] = (255 / iterations) * num;
                        colorField[j - startJ][i - startI][1] = 255;
                        colorField[j - startJ][i - startI][2] = 255;
                        break;
                    }
                }
            }
        }
    }
 */
}
