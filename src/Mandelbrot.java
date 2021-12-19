import java.math.BigDecimal;

public class Mandelbrot extends Fractal{
    public Mandelbrot() {
    }

    public Complex1 step(Complex1 z, Complex1 c) {
        z.square();
        z.plus(c);
        return z;
    }

    public float[] colorPicker(BigDecimal dec) {
        return null;
    }
    public Complex1[][] setup(Complex1[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Complex1(0.0,0.0);
            }
        }
        return field;
    }
}
