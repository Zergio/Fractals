package fractals.fpnumbers;

public class FPNumber2 extends AbstractFPNumber<FPNumber2> {

    FPNumber2(String number) {
        super(number);
    }

    FPNumber2(long number) {
        super(number);
    }

    FPNumber2(long number, long scale) {
        super(number, scale);
    }

    @Override
    public FPNumber2 multiply(FPNumber2 number) {
        long high = Math.multiplyHigh(mantissa, number.getMantissa());
        long factor = number.getMantissa();
        if (high != 0 && high != -1) { // left 64bit is empty
            int digits = getNumberOfDigits(high);
            scale += digits;
            long ratio;
            if (mantissa > factor) {
                ratio = mantissa / factor;
                int ratioDigits = getNumberOfDigits(ratio) - 1;
                if (ratioDigits >= digits) {
                    mantissa /= POWERS_OF_TEN[digits]; //Return to this for optimization
                } else {
                    int shift = (digits - ratioDigits) >> 1;
                    mantissa /= POWERS_OF_TEN[digits - shift]; //Return to this for optimization
                    factor /= POWERS_OF_TEN[shift];
                }
            } else {
                ratio = number.getMantissa() / mantissa;
                int ratioDigits = getNumberOfDigits(ratio) - 1;
                if (ratioDigits >= digits) {
                    factor /= POWERS_OF_TEN[digits]; //Return to this for optimization
                } else {
                    int shift = (digits - ratioDigits) >> 1;
                    factor /= POWERS_OF_TEN[digits - shift]; //Return to this for optimization
                    mantissa /= POWERS_OF_TEN[shift];
                }
            }

        } else {
            scale += number.getScale();
        }
        mantissa *= factor;
        return this;
    }

    @Override
    public FPNumber2 multiply(long number) {
        return multiply(new FPNumber2(number));
    }

    @Override
    public FPNumber2 divide(FPNumber2 number) {
        throw new IllegalArgumentException();
    }

    @Override
    public FPNumber2 clone() {
        return new FPNumber2(mantissa, scale);
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
}
