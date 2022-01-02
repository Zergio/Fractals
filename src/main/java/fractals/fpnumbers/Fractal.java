package fractals.fpnumbers;

public interface Fractal<T extends FPNumber<T>> {

    ComplexNumber<T> step(ComplexNumber<T> z, ComplexNumber<T> c);

    void setup();

    ComplexNumber<T>[][] getField();

    int[] getColorField(int i, int j);
}
