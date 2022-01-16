package fractals.fpnumbers;

public interface Fractal<T extends FPNumber<T>> {

    ComplexNumber<T> step(ComplexNumber<T> z, ComplexNumber<T> c);

    void setup();

    void process(int steps, T xBeg, T yBeg, T xRan, T yRan, int cores) throws InterruptedException;

    ComplexNumber<T>[][] getField();

    int[] getColorField(int i, int j);
}
