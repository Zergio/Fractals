/*
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
            } else if ((ch == '-' && i > 0) || (ch == '.' && dot)) {
                throw new IllegalArgumentException();
            } else if (ch == '.') {
                dot = true;
            }
        }
        if (number.charAt(0) == '-') {
            NUMBER = ~NUMBER;
            NUMBER++;
        }
        //checkForExtraZero();
    }
    FixedPoint(long number) {
        NUMBER = number;
        SCALE = 0;
    }
    FixedPoint(long number, long scale) {
        NUMBER = number;
        SCALE = scale;
        checkForExtraZero();
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

    private int getDecimalFractionLengh(@NotNull String decimal) {
        return decimal.length() - decimal.indexOf('.') - 1;
    }

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
        //checkForExtraZero();
    }

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

    public void multiply(@NotNull FixedPoint fixedNumber) {
        //checkForExtraZero();
        long[] number = new long[]{Math.multiplyHigh(NUMBER, fixedNumber.getNumber()),
                NUMBER * fixedNumber.getNumber()};

        if (number[0] != 0) {
            while (number[0] > 0) {
                number = divideByTen(number);
                SCALE++;
            }

            if (number[1] < 0
                    && ((NUMBER > 0 && fixedNumber.getNumber() > 0) || (NUMBER < 0 && fixedNumber.getNumber() < 0))) {
                number = divideByTen(number);
                SCALE++;
            }
        }
        NUMBER = number[1];

        //checkForExtraZero();
    }

    static long[] POWERS_OF_TEN = new long[19];

    static long[] NEG_POWERS_OF_TEN = new long[19];

    static {
        long c = 1;
        for (int i = 0; i < 19; i++) {
            POWERS_OF_TEN[i] = c;
            NEG_POWERS_OF_TEN[i] = -c;
            c *= 10;
        }
    }

    public void multiply2(@NotNull FixedPoint fixedNumber) {
        //checkForExtraZero();
        long number = Math.multiplyHigh(NUMBER, fixedNumber.getNumber());
        long factor = fixedNumber.getNumber();
        if (number != 0 && number != -1) { // left 64bit is empty
            int digits = getNumberOfDigits(number);
            SCALE += digits;
            long ratio;
            if (NUMBER > factor) {
                ratio = NUMBER / factor;
                int ratioDigits = getNumberOfDigits(ratio) - 1;
                if (ratioDigits >= digits) {
                    NUMBER /= POWERS_OF_TEN[digits]; //Return to this for optimization
                } else {
                    int shift = (digits - ratioDigits) >> 1;
                    NUMBER /= POWERS_OF_TEN[digits - shift]; //Return to this for optimization
                    factor /= POWERS_OF_TEN[shift];
                }
            } else {
                ratio = fixedNumber.getNumber() / NUMBER;
                int ratioDigits = getNumberOfDigits(ratio) - 1;
                if (ratioDigits >= digits) {
                    factor /= POWERS_OF_TEN[digits]; //Return to this for optimization
                } else {
                    int shift = (digits - ratioDigits) >> 1;
                    factor /= POWERS_OF_TEN[digits - shift]; //Return to this for optimization
                    NUMBER /= POWERS_OF_TEN[shift];
                }
            }

        }
        NUMBER *= factor;

        //checkForExtraZero();
    }

    public int getNumberOfDigits(long number) {
        int result, step, count;
        if (number >= POWERS_OF_TEN[16] || number <= NEG_POWERS_OF_TEN[16]) {
            result = 18;
            step = 1;
            count = 2;
        } else {
            result = 8;
            step = 4;
            count = 4;
        }
        if (number >= 0) {
            for (int i = 0; i < count; i++) {
                if (result == 19) {
                    break;
                }
                if (number >= POWERS_OF_TEN[result]) {
                    result += step;
                    if (step == 0) {
                        return result + 1;
                    }
                } else if (number < POWERS_OF_TEN[result]) {
                    result -= step;
                }
                step >>= 1;
            }
        } else {
            for (int i = 0; i < count; i++) {
                if (result == 19) {
                    break;
                }
                if (number <= NEG_POWERS_OF_TEN[result]) {
                    result += step;
                    if (step == 0) {
                        return result + 1;
                    }
                } else if (number > NEG_POWERS_OF_TEN[result]) {
                    result -= step;
                }
                step >>= 1;
            }
        }
        return result;
    }

    public void divide(@NotNull FixedPoint fixedNumber) {
        //how to implement!?
    }

    public void square() {
        NUMBER *= NUMBER;
        SCALE *= 2;
    }

    private void checkForExtraZero() {
        while (NUMBER % 10 == 0 && NUMBER != 0) {
            NUMBER = divideByTen(new long[] {0, NUMBER})[1];
            SCALE--;
        }
    }

    public static long[] divideByTen(long[] input) {
        long[] q = new long[] { input[0], input[1] }, r = new long[2];
        q = shiftRight(q, 1);               //  q = (NUMBER >> 1);
        q = add(q, shiftRight(q, 1));       //  q += (q >> 1);
        q = add(q, shiftRight(q, 4));       //  q += (q >> 4);
        q = add(q, shiftRight(q, 8));       //  q += (q >> 8);
        q = add(q, shiftRight(q, 16));      //  q += (q >> 16);
        q = add(q, shiftRight(q, 32));      //  q += (q >> 32);
        q = add(q, new long[]{input[0] > 0?  0L : -1L, input[0]});     //  q += (q >> 64);
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

    }

    public long divideByTen(long input) {
       return input / 10;
    }

    public long[] addShiftRight(long[] number, int shift) {
        long[] shiftedNum = shiftRight(number, shift);
        return add(number, shiftedNum);
    }

    @NotNull
    public static long[] shiftRight(long[] number, int shift) {
        long[] shiftedNum = new long[2];
        shiftedNum[0] = number[0] >> shift;
        shiftedNum[1] = number[1] >>> shift;
        shiftedNum[1] |= number[0] << (64 - shift);
        return shiftedNum;
    }

    @NotNull
    public static long[] shiftLeft(long[] number, int shift) {
        long[] shiftedNum = new long[2];
        shiftedNum[0] = number[0] << shift;
        shiftedNum[1] = number[1] << shift;
        shiftedNum[0] |= number[1] >>> (64 - shift);
        return shiftedNum;
    }

    @NotNull
    public static long[] add(long[] num1, long[] num2) {
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
}
*/