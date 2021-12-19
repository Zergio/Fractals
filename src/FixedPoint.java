import org.jetbrains.annotations.NotNull;

public class FixedPoint {
    private long NUMBER;
    private long SCALE;

    FixedPoint(String number) {
        SCALE = getDecimalFractionLengh(number);
        NUMBER = 0;
        boolean dot = false;
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= '0' && ch <= '9') {
                // NUMBER * 10 + character
                NUMBER = (NUMBER << 1) + (NUMBER << 3) + (ch - '0');
            } if ((ch == '-' && i > 0) || (ch == '.' && dot)) {
                throw new IllegalArgumentException();
            }
            else if (ch == '.') {
                dot = true;
            }
        }
        if (number.charAt(0) == '-') {
            NUMBER = -NUMBER;
        }
    }
    FixedPoint(long number) {
        NUMBER = number;
        SCALE = 0;
    }
    FixedPoint(long number, long scale) {
        NUMBER = number;
        SCALE = scale;
    }

    public double getProperNumber() {
        return (NUMBER / (Math.pow(10, SCALE)));
    }

    public long getScale() {
        return SCALE;
    }

    public long getNumber() {
        return NUMBER;
    }

    /**
     * @param decimal String representation of the decimal
     * @return length of fractional part of the decimal
     */
    private int getDecimalFractionLengh(@NotNull String decimal) {
        return decimal.length() - decimal.indexOf('.') - 1;
    }

    /**
     * Addition
     * @param fixedNumber this number is the number that is being added
     */
    public void add(@NotNull FixedPoint fixedNumber) {
        long diff = Math.abs(fixedNumber.getScale() - SCALE);
        if (SCALE == fixedNumber.getScale()) {
            NUMBER += fixedNumber.getNumber();
        } else if (SCALE > fixedNumber.getScale()) {
            NUMBER += fixedNumber.getNumber() * (Math.pow(10, diff));
        } else if (fixedNumber.getScale() > SCALE) {
            NUMBER *= (Math.pow(10, diff));
            NUMBER += fixedNumber.getNumber();
            SCALE += diff;
        }
    }

    /**
     * Subtraction
     * @param fixedNumber this is the number that is being subtracted
     */
    public void subtract(@NotNull FixedPoint fixedNumber) {
        long diff = Math.abs(fixedNumber.getScale() - SCALE);
        if (SCALE == fixedNumber.getScale()) {
            NUMBER -= fixedNumber.getNumber();
        } else if (SCALE > fixedNumber.getScale()) {
            NUMBER -= fixedNumber.getNumber() * (Math.pow(10, diff));
        } else if (fixedNumber.getScale() > SCALE) {
            NUMBER *= (Math.pow(10, diff));
            NUMBER -= fixedNumber.getNumber();
            SCALE += diff;
        }
    }

    /**
     * Multiplication
     * @param fixedNumber this is the number that ius being multiplied by
     */
    public void multiply(@NotNull FixedPoint fixedNumber) {
        NUMBER *= fixedNumber.getNumber();
        SCALE += fixedNumber.getScale();
    }

    /**
     * Division
     * @param fixedNumber this is the number that is being devided by
     */
    public void divide(@NotNull FixedPoint fixedNumber) {
        //how to implement!?
    }
}
