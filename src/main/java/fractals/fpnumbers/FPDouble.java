package fractals.fpnumbers;

import fractals.Utils;

import java.text.DecimalFormat;

public class FPDouble implements FPNumber<FPDouble> {
    private double internalValue;

    FPDouble(double number) {
        this.internalValue = number;
    }

    FPDouble(String number) {
        this.internalValue = Double.parseDouble(number);
    }

    FPDouble(long number) {
        this.internalValue = (double) number;
    }

    FPDouble(long number, long scale) {
        if (scale > 0) {
            this.internalValue = ((double) number) * Utils.POWERS_OF_TEN[(int) scale];
        } else if (scale < 0) {
            this.internalValue = ((double) number) / Utils.NEG_POWERS_OF_TEN[(int) -scale];
        }
    }
    @Override
    public FPDouble add(FPDouble number) {
        this.internalValue += number.internalValue;
        return this;
    }

    @Override
    public FPDouble subtract(FPDouble number) {
        this.internalValue -= number.internalValue;
        return this;
    }

    @Override
    public FPDouble multiply(FPDouble number) {
        this.internalValue *= number.internalValue;
        return this;
    }

    @Override
    public FPDouble multiply(long number) {
        this.internalValue *= (double) number;
        return this;
    }

    @Override
    public FPDouble divide(FPDouble number) {
        this.internalValue /= number.internalValue;
        return this;
    }

    @Override
    public FPDouble square() {
        internalValue *= internalValue;
        return this;
    }

    @Override
    public long getLong() {
        return (long) internalValue;
    }

    @Override
    public FPDouble clone() {
        return new FPDouble(internalValue);
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("0.00000000000000000"); // Here you can also deal with rounding if you wish..
        return formatter.format(internalValue);
    }
}
