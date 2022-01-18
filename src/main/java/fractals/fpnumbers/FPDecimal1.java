package fractals.fpnumbers;

public class FPDecimal1 extends AbstractFPDecimal<FPDecimal1> {

    public FPDecimal1(String number) {
        super(number);
    }

    public FPDecimal1(long number) {
        super(number);
    }

    public FPDecimal1(long number, long scale) {
        super(number, scale);
    }

    @Override
    public FPDecimal1 multiply(FPDecimal1 number) {
//        if (scale + number.scale < -40) {
//            return Utils.DECIMAL_1_ZERO.clone();
//        }
//        BigDecimal dec1 = new BigDecimal(new BigInteger(String.valueOf(mantissa)), (int)-scale, Utils.CONTEXT);
//        BigDecimal dec2 = new BigDecimal(new BigInteger(String.valueOf(number.mantissa)), (int)-number.scale, Utils.CONTEXT);
//        double result = dec1.doubleValue() * dec2.doubleValue();
//        mantissa = String.valueOf(result)
//        scale = 0;
//
//        return new FPDecimal1(dec1.multiply(dec2).toPlainString());
        long[] parts = new long[]{Math.multiplyHigh(mantissa, number.mantissa),
                mantissa * number.mantissa};

        if (parts[0] != 0 && parts[0] != -1) {
            while (parts[0] > 0) {
                parts = divideByTen(parts);
                scale++;
            }

            if (parts[1] < 0
                    && ((mantissa > 0 && number.mantissa > 0) || (mantissa < 0 && number.mantissa < 0))) {
                parts = divideByTen(parts);
                scale++;
            }
        } else {
            scale += number.scale;
        }
        mantissa = parts[1];
        return this;
    }

    @Override
    public FPDecimal1 multiply(long number) {
        return multiply(new FPDecimal1(number));
    }

    public static long[] divideByTen(long[] input) {
        long[] q = new long[] { input[0], input[1] };
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

    public static long[] shiftRight(long[] number, int shift) {
        long[] shiftedNum = new long[2];
        shiftedNum[0] = number[0] >> shift;
        shiftedNum[1] = number[1] >>> shift;
        shiftedNum[1] |= number[0] << (64 - shift);
        return shiftedNum;
    }

    public static long[] shiftLeft(long[] number, int shift) {
        long[] shiftedNum = new long[2];
        shiftedNum[0] = number[0] << shift;
        shiftedNum[1] = number[1] << shift;
        shiftedNum[0] |= number[1] >>> (64 - shift);
        return shiftedNum;
    }

    @Override
    public FPDecimal1 divide(FPDecimal1 number) {
        throw new IllegalArgumentException();
    }

    @Override
    public FPDecimal1 clone() {
        return new FPDecimal1(mantissa, scale);
    }

    private void checkForExtraZero() {
        while (mantissa % 10 == 0 && mantissa != 0) {
            mantissa = divideByTen(new long[] {0, mantissa})[1];
            scale--;
        }
    }
}
