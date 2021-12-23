import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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
        if (SCALE == fixedNumber.getScale()) {
            NUMBER += fixedNumber.getNumber();
        } else if (SCALE > fixedNumber.getScale()) {
            NUMBER += fixedNumber.getNumber() * (Math.pow(10, SCALE - fixedNumber.getScale()));
        } else if (fixedNumber.getScale() > SCALE) {
            NUMBER *= (Math.pow(10, fixedNumber.getScale() - SCALE));
            NUMBER += fixedNumber.getNumber();
            SCALE += fixedNumber.getScale() - SCALE;
        }
        checkForExtraZero();
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
        checkForExtraZero();
    }

    /**
     * Multiplication
     * @param fixedNumber this is the number that ius being multiplied by
     */
    public void multiply(@NotNull FixedPoint fixedNumber) {
        checkForExtraZero();
        long high = Math.multiplyHigh(NUMBER, fixedNumber.getNumber());
        long low = NUMBER * fixedNumber.getNumber();

        //checkForExtraZero();
    }

    /**
     * Division
     * @param fixedNumber this is the number that is being devided by
     */
    public void divide(@NotNull FixedPoint fixedNumber) {
        //how to implement!?
    }

    /**
     * Power of 2
     */
    public void square() {
        NUMBER *= NUMBER;
        SCALE *= 2;
    }

    private void checkForExtraZero() {
        while (NUMBER % 10 == 0 && NUMBER != 0) {
            NUMBER /= 10;
            SCALE--;
        }
    }

    static long[] divideByTen(long[] input) {
        long[] q = new long[] { input[0], input[1] }, r = new long[2];
        q = shiftRight(q, 1);               //  q = (NUMBER >> 1);
        q = add(q, shiftRight(q, 1));       //  q += (q >> 1);
        q = add(q, shiftRight(q, 4));       //  q += (q >> 4);
        q = add(q, shiftRight(q, 8));       //  q += (q >> 8);
        q = add(q, shiftRight(q, 16));      //  q += (q >> 16);
        q = add(q, shiftRight(q, 32));      //  q += (q >> 32);
        q = add(q, new long[]{0, input[0]});     //  q += (q >> 64);
        q = shiftRight(q, 3);               //  q = q >> 3;

        long[] round;

        round = shiftLeft(add(q, shiftLeft(q,2)),1);    // r = (((q << 2) + q) << 1);

        round[0] = ~round[0];
        round[1] = ~round[1];
        long[] number = { input[0], input[1] };
        long[] one = { 0, 1 };
        number = add(number, one);
        round = add(round, number);                               // r = NUMBER - r;

        if (round[1] > 9) {                                       // if (r > 9) { q++; } // adjust answer by error term
            q = add(q, one);
        }

        return q;

        /*
        long q, r;
        q = (NUMBER >> 1) + (NUMBER >> 2);
        q += (q >> 4);
        q += (q >> 8);
        q += (q >> 16);
        q += (q >> 32);
        // note: q is now roughly 0.8n
        q = q >> 3;
        r = NUMBER - (((q << 2) + q) << 1);
        if (r > 9) { q++; } // adjust answer by error term
        NUMBER = q;
         */                             // THIS IS FOR SAFEKEEPING DO NOT DELETE
    }

    /**
     * number += number >> shift
     * @param number
     * @param shift
     * @return
     */
    public long[] addShiftRight(long[] number, int shift) {
        long[] shiftedNum = shiftRight(number, shift);
        return add(number, shiftedNum);
    }

    @NotNull
    static long[] shiftRight(long[] number, int shift) {
        long[] shiftedNum = new long[2];
        shiftedNum[0] = number[0] >>> shift;
        shiftedNum[1] = number[1] >>> shift;
        shiftedNum[1] |= number[0] << (64 - shift);
        return shiftedNum;
    }

    @NotNull
    static long[] shiftLeft(long[] number, int shift) {
        long[] shiftedNum = new long[2];
        shiftedNum[0] = number[0] << shift;
        shiftedNum[1] = number[1] << shift;
        shiftedNum[0] |= number[1] >>> (64 - shift);
        return shiftedNum;
    }

    @NotNull
    static long[] add(long[] num1, long[] num2) {
        long[] result = new long[2];
        result[1] = num1[1] + num2[1];
        result[0] = num1[0] + num2[0];
        // checking for carry from the leftmost bits
        if (num1[1] < 0 && num2[1] < 0) {
            result[0] += 1;
        } else if (num1[1] < 0) {
            if (-num1[1] <= num2[1]) {
                result[0] += 1;
            }
        } else if (num2[1] < 0) {
            if (-num2[1] <= num1[1]) {
                result[0] += 1;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FixedPoint that = (FixedPoint) o;
        return NUMBER == that.NUMBER &&
                SCALE == that.SCALE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NUMBER, SCALE);
    }
}
