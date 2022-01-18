package fractals.fpnumbers;

import org.apfloat.Apfloat;

public class FPAPFloat implements FPNumber<FPAPFloat> {
    private Apfloat internalValue;

    public FPAPFloat(Apfloat number) {
        this.internalValue = number;
    }

    public FPAPFloat(long number) {
        this.internalValue = new Apfloat(number, 100);
    }

    public FPAPFloat(String number) {
        this.internalValue = new Apfloat(number, 100);
    }

    @Override
    public FPAPFloat add(FPAPFloat number) {
        internalValue = internalValue.add(number.internalValue);
        return this;
    }

    @Override
    public FPAPFloat subtract(FPAPFloat number) {
        internalValue = internalValue.subtract(number.internalValue);
        return this;
    }

    @Override
    public FPAPFloat multiply(FPAPFloat number) {
        internalValue = internalValue.multiply(number.internalValue);
        return this;
    }

    @Override
    public FPAPFloat multiply(long number) {
        internalValue = internalValue.multiply(new Apfloat(number, 100));
        return this;
    }

    @Override
    public FPAPFloat divide(FPAPFloat number) {
        internalValue = internalValue.divide(number.internalValue);
        return this;
    }

    @Override
    public FPAPFloat square() {
        internalValue = internalValue.multiply(internalValue);
        return this;
    }

    @Override
    public long getLong() {
        return internalValue.longValue();
    }

    @Override
    public FPAPFloat clone() {
        return new FPAPFloat(internalValue);
    }

    @Override
    public String toString() {
        return internalValue.toString(true);
    }
}
