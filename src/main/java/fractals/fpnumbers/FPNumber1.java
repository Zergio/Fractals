package fractals.fpnumbers;

import org.jetbrains.annotations.NotNull;

public class FPNumber1 extends AbstractFPNumber<FPNumber1> {

    FPNumber1(String number) {
        super(number);
    }

    FPNumber1(long number) {
        super(number);
    }

    FPNumber1(long number, long scale) {
        super(number, scale);
    }

    @Override
    public FPNumber1 multiply(FPNumber1 number) {
        long[] parts = new long[]{Math.multiplyHigh(mantissa, number.getMantissa()),
                mantissa * number.getMantissa()};

        if (parts[0] != 0 && parts[0] != -1) {
            while (parts[0] > 0) {
                parts = divideByTen(parts);
                scale++;
            }

            if (parts[1] < 0
                    && ((mantissa > 0 && number.getMantissa() > 0) || (mantissa < 0 && number.getMantissa() < 0))) {
                parts = divideByTen(parts);
                scale++;
            }
        } else {
            scale += number.getScale();
        }
        mantissa = parts[1];
        return this;
    }

    @Override
    public FPNumber1 multiply(long number) {
        return multiply(new FPNumber1(number));
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

    @Override
    public FPNumber1 divide(FPNumber1 number) {
        throw new IllegalArgumentException();
    }

    @Override
    public FPNumber1 clone() {
        return new FPNumber1(mantissa, scale);
    }

    private void checkForExtraZero() {
        while (mantissa % 10 == 0 && mantissa != 0) {
            mantissa = divideByTen(new long[] {0, mantissa})[1];
            scale--;
        }
    }
}
