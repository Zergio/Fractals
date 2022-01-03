package fractals.fpnumbers;

public interface FPNumber<T extends FPNumber<T>> {
    T add(T number);
    T subtract(T number);
    T multiply(T number);
    T multiply(long number);
    T divide(T number);
    T square();

    // transforms the number into scale 0
    long getLong();

    T clone();
}
