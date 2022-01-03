package fractals.fpnumbers;

public class FPDecimal2 extends AbstractFPDecimal<FPDecimal2> {

    FPDecimal2(String number) {
        super(number);
    }

    FPDecimal2(long number) {
        super(number);
    }

    FPDecimal2(long number, long scale) {
        super(number, scale);
    }

    @Override
    public FPDecimal2 multiply(FPDecimal2 number) {
        long high = Math.multiplyHigh(mantissa, number.mantissa);
        long factor = number.mantissa;
        if (high != 0 && high != -1) { // left 64bit is empty
            int digits = getNumberOfDigits(high);
            scale += digits;
            long ratio;
            if (mantissa > factor) {
                ratio = mantissa / factor;
                int ratioDigits = getNumberOfDigits(ratio) - 1;
                if (ratioDigits >= digits) {
                    mantissa /= Utils.POWERS_OF_TEN[digits]; //Return to this for optimization
                } else {
                    int shift = (digits - ratioDigits) >> 1;
                    mantissa /= Utils.POWERS_OF_TEN[digits - shift]; //Return to this for optimization
                    factor /= Utils.POWERS_OF_TEN[shift];
                }
            } else {
                ratio = number.mantissa / mantissa;
                int ratioDigits = getNumberOfDigits(ratio) - 1;
                if (ratioDigits >= digits) {
                    factor /= Utils.POWERS_OF_TEN[digits]; //Return to this for optimization
                } else {
                    int shift = (digits - ratioDigits) >> 1;
                    factor /= Utils.POWERS_OF_TEN[digits - shift]; //Return to this for optimization
                    mantissa /= Utils.POWERS_OF_TEN[shift];
                }
            }

        } else {
            scale += number.scale;
        }
        mantissa *= factor;
        return this;
    }

    @Override
    public FPDecimal2 multiply(long number) {
        return multiply(new FPDecimal2(number));
    }

    @Override
    public FPDecimal2 divide(FPDecimal2 number) {
        throw new IllegalArgumentException();
    }

    @Override
    public FPDecimal2 clone() {
        return new FPDecimal2(mantissa, scale);
    }

    public int getNumberOfDigits(long number) {
        int result, step, count;
        if (number >= Utils.POWERS_OF_TEN[16] || number <= Utils.NEG_POWERS_OF_TEN[16]) {
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
                if (number >= Utils.POWERS_OF_TEN[result]) {
                    result += step;
                    if (step == 0) {
                        return result + 1;
                    }
                } else if (number < Utils.POWERS_OF_TEN[result]) {
                    result -= step;
                }
                step >>= 1;
            }
        } else {
            for (int i = 0; i < count; i++) {
                if (result == 19) {
                    break;
                }
                if (number <= Utils.NEG_POWERS_OF_TEN[result]) {
                    result += step;
                    if (step == 0) {
                        return result + 1;
                    }
                } else if (number > Utils.NEG_POWERS_OF_TEN[result]) {
                    result -= step;
                }
                step >>= 1;
            }
        }
        return result;
    }
}
