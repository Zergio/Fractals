package fractals.fpnumbers;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Objects;

public abstract class AbstractFPNumber<T extends AbstractFPNumber<T>> implements FPNumber<T> {
    protected long mantissa;
    protected long scale;

    protected static final long[] POWERS_OF_TEN = new long[19];

    protected static final long[] NEG_POWERS_OF_TEN = new long[19];

    static {
        long c = 1;
        for (int i = 0; i < 19; i++) {
            POWERS_OF_TEN[i] = c;
            NEG_POWERS_OF_TEN[i] = -c;
            c *= 10;
        }
    }

    AbstractFPNumber(String number) {
        int decimalFractionLength = getDecimalFractionLengh(number);
        if (decimalFractionLength == number.length()) {
            scale = 0;
        } else {
            scale = -decimalFractionLength;
        }
        mantissa = 0;
        boolean dot = false;
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= '0' && ch <= '9') {
                // NUMBER * 10 + character
                mantissa = (mantissa << 1) + (mantissa << 3) + (ch - '0');
            } else if ((ch == '-' && i > 0) || (ch == '.' && dot)) {
                throw new IllegalArgumentException();
            } else if (ch == '.') {
                dot = true;
            }
        }
        if (number.charAt(0) == '-') {
            mantissa = ~mantissa + 1;
        }
    }

    AbstractFPNumber(long number) {
        this.mantissa = number;
        this.scale = 0;
    }

    AbstractFPNumber(long number, long scale) {
        this.mantissa = number;
        this.scale = scale;
    }

    @Override
    public long getMantissa() {
        return mantissa;
    }

    @Override
    public BigInteger getMantissaAsBigInteger() {
        return BigInteger.valueOf(mantissa);
    }

    @Override
    public long getScale() {
        return scale;
    }

    @Override
    public T add(T number) {
        if (scale == number.getScale()) {
            mantissa += number.getMantissa();
        } else if (scale > number.getScale()) {
            mantissa = mantissa * FPNumber1.POWERS_OF_TEN[(int) (scale - number.getScale())] + number.getMantissa();
            scale = number.getScale();
        } else if (number.getScale() > scale) {
            mantissa += number.getMantissa() * FPNumber1.POWERS_OF_TEN[(int) (number.getScale() - scale)];
        }
        return (T)this;
    }

    @Override
    public T subtract(T number) {
        if (scale == number.getScale()) {
            mantissa -= number.getMantissa();
        } else if (scale > number.getScale()) {
            mantissa = mantissa * FPNumber1.POWERS_OF_TEN[(int) (scale - number.getScale())] - number.getMantissa();
            scale = number.getScale();
        } else if (number.getScale() > scale) {
            mantissa -= number.getMantissa() * FPNumber1.POWERS_OF_TEN[(int) (number.getScale() - scale)];
        }
        return (T)this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        T that = (T) o;
        return mantissa == that.getMantissa() &&
                scale == that.getScale();
    }

    @Override
    public int hashCode() {
        return Objects.hash(mantissa, scale);
    }

    /**
     * Power of 2
     */
    public T square() {
        mantissa *= mantissa;
        scale <<= 1;
        return (T)this;
    }

    @Override
    public long getScaledInteger() {
        if (scale >= 0) {
            return mantissa * POWERS_OF_TEN[(int)scale];
        } else if (scale < -18) {
            return 0;
        } else {
            return mantissa / POWERS_OF_TEN[(int) -scale];
        }
    }

    public T clone() {
        throw new IllegalArgumentException();
    }

    public int getDecimalFractionLengh(@NotNull String decimal) {
        return decimal.length() - decimal.indexOf('.') - 1;
    }
}
