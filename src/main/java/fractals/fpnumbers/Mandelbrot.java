package fractals.fpnumbers;

public class Mandelbrot<T extends FPNumber<T>, F extends FPNumberFactory<T>> implements Fractal<T> {

    private final ComplexNumber<T>[][] baseField;
    public final int[][][] colorField;
    private final F numberFactory;
    private final int heightOfField, widthOfField;

    public Mandelbrot(F numberFactory, int heightOfField, int widthOfField) {
        this.numberFactory = numberFactory;
        this.widthOfField = widthOfField;
        this.heightOfField = heightOfField;
        baseField = new ComplexNumber[heightOfField][widthOfField];
        colorField = new int[heightOfField][widthOfField][3];
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

    public void process(int iterations) {
        double xBeginning = -2;
        double xEnd = 0.5;
        double yBeginning = -1.12;
        double yEnd = 1.12;

        double xRange = Math.abs(xBeginning) + Math.abs(xEnd);
        double yRange = Math.abs(yBeginning) + Math.abs(yEnd);

        for (int i = 0; i < heightOfField; i++) {
            String x1 = String.valueOf(xBeginning + ((xRange * i) / heightOfField));
            var x = numberFactory.createFPNumber(x1);
            for (int j = 0; j < widthOfField; j++) {
                String y1 = String.valueOf(yBeginning + ((yRange * j) / widthOfField));
                var y = numberFactory.createFPNumber(y1);
                ComplexNumber<T> xy = new ComplexNumber<T>(x, y);
                ComplexNumber<T> current = baseField[i][j];
                colorField[i][j][0] = 0;
                colorField[i][j][1] = 0;
                colorField[i][j][2] = 0;
                for (int num = 0; num < iterations; num++) {
                    current.square();
                    current.add(xy);
                    if (Math.abs(current.real().getScaledInteger() + current.im().getScaledInteger()) >= 4) {
                        colorField[i][j][0] = ((255 / iterations) * num);
                        colorField[i][j][1] = 255;
                        colorField[i][j][2] = 255;
                        break;
                    }
                }
            }
        }
    }

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
}
