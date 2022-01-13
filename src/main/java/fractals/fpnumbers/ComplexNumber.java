package fractals.fpnumbers;

public class ComplexNumber<T extends FPNumber<T>> {
    private final T r;
    private T i;

    public ComplexNumber(T real, T im) {
        r = real;
        i = im;
    }

    public T real() {
        return r;
    }

    public T im() {
        return i;
    }

    public void setImaginary(T imaginary) {
        i = imaginary;
    }

    public void add(ComplexNumber<T> number) {
        r.add(number.real());
        i.add(number.im());
    }

    public void multiply(ComplexNumber<T> number) {
        r.multiply(number.real());
        i.multiply(number.im());
    }

    public void subtract(ComplexNumber<T> number) {
        r.subtract(number.real());
        i.subtract(number.im());
    }

    public void square() {
        T rCloned = r.clone();
        T iCloned = i.clone();
        r.square(); // real part calculation
        i.square();
        r.subtract(i);

        i = iCloned.multiply(rCloned).multiply(2); // imaginary part calculation
    }

    public ComplexNumber clone() {
        return new ComplexNumber(r, i);
    }
}