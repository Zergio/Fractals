package fractals.fpnumbers;

import fractals.Utils;

import java.util.Objects;

public abstract class AbstractFPDecimal<T extends AbstractFPDecimal<T>> implements FPNumber<T> {
    protected long mantissa;
    protected long scale;

    AbstractFPDecimal(String number) {
        int decimalFractionLength = Utils.getDecimalFractionLengh(number);
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

    AbstractFPDecimal(long number) {
        this.mantissa = number;
        this.scale = 0;
    }

    AbstractFPDecimal(long number, long scale) {
        this.mantissa = number;
        this.scale = scale;
    }

    @Override
    public T add(T number) {
        if (scale == number.scale) {
            mantissa += number.mantissa;
        } else if (scale > number.scale) {
            if (scale - number.scale < 19) {
                mantissa = mantissa * Utils.POWERS_OF_TEN[(int) (scale - number.scale)] + number.mantissa;
                scale = number.scale;
            } else {
                int downScale = (int) (scale - number.scale - 18);
                number.mantissa /= Utils.POWERS_OF_TEN[downScale];
                number.scale += downScale;
                mantissa = mantissa * Utils.POWERS_OF_TEN[18] + number.mantissa;
                scale = number.scale;
            }
        } else {
            if (number.scale - scale < 19) {
                mantissa += number.mantissa * Utils.POWERS_OF_TEN[(int) (number.scale - scale)];
            } else {
                // Copy number because our number is too small
                mantissa = number.mantissa;
                scale = number.scale;
            }
        }
        return (T)this;
    }

    @Override
    public T subtract(T number) {
        if (scale == number.scale) {
            mantissa -= number.mantissa;
        } else if (scale > number.scale) {
            if (scale - number.scale < 19) {
                mantissa = mantissa * Utils.POWERS_OF_TEN[(int) (scale - number.scale)] - number.mantissa;
                scale = number.scale;
            } else {
                if (scale - number.scale - 18 < 19) {
                    int downScale = (int) (scale - number.scale - 18);
                    number.mantissa /= Utils.POWERS_OF_TEN[downScale];
                    number.scale += downScale;
                    mantissa = mantissa * Utils.POWERS_OF_TEN[18] - number.mantissa;
                    scale = number.scale;
                } else {
                    var i = 1;
                }
            }
        } else {
            if (number.scale - scale < 19) {
                mantissa -= number.mantissa * Utils.POWERS_OF_TEN[(int) (number.scale - scale)];
            } else {
                // Copy number because our number is too small
                mantissa = -number.mantissa;
                scale = number.scale;
            }
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
        return mantissa == that.mantissa &&
                scale == that.scale;
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
    public long getLong() {
        if (scale >= 0) {
            return mantissa * Utils.POWERS_OF_TEN[(int)scale];
        } else if (scale < -18) {
            return 0;
        } else {
            return mantissa / Utils.POWERS_OF_TEN[(int) -scale];
        }
    }

    public T clone() {
        throw new IllegalArgumentException();
    }
}
