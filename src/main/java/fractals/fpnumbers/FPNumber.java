package fractals.fpnumbers;

import java.math.BigInteger;

public interface FPNumber<T extends FPNumber<T>> {
    long getMantissa();
    BigInteger getMantissaAsBigInteger();
    long getScale();

    T add(T number);
    T subtract(T number);
    T multiply(T number);
    T multiply(long number);
    T divide(T number);
    T square();

    // transforms the number into scale 0
    long getScaledInteger();

    T clone();
}
