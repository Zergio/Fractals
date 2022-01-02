package fractals.fpnumbers;

public class ComplexNumber<T extends FPNumber<T>> {
    private final T x, y;

    public ComplexNumber(T real, T im) {
        x = real;
        y = im;
    }

    public T real() {
        return x;
    }

    public T im() {
        return y;
    }

    public void add(ComplexNumber<T> number) {
        x.add(number.real());
        y.add(number.im());
    }

    public void subtract(ComplexNumber<T> number) {
        x.subtract(number.real());
        y.subtract(number.im());
    }

    public void square() {
        T temp = x.clone();
        x.square();
        y.square();
        x.subtract(y);
        y.multiply(temp);
        x.multiply(2);
    }
}