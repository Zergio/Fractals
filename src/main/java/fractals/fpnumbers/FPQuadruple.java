package fractals.fpnumbers;

import com.mvohm.quadruple.Quadruple;

public class FPQuadruple implements FPNumber<FPQuadruple>{
    private final Quadruple internalValue = new Quadruple();

    public FPQuadruple(Quadruple number) {
        internalValue.assign(number);
    }

    public FPQuadruple(long number) {
        internalValue.assign(number);
    }

    public FPQuadruple(String number) {
        internalValue.assign(number);
    }

    @Override
    public FPQuadruple add(FPQuadruple number) {
        internalValue.add(number.internalValue);
        return this;
    }

    @Override
    public FPQuadruple subtract(FPQuadruple number) {
        internalValue.subtract(number.internalValue);
        return this;
    }

    @Override
    public FPQuadruple multiply(FPQuadruple number) {
        internalValue.multiply(number.internalValue);
        return this;
    }

    @Override
    public FPQuadruple multiply(long number) {
        internalValue.multiply(number);
        return this;
    }

    @Override
    public FPQuadruple divide(FPQuadruple number) {
        internalValue.divide(number.internalValue);
        return this;
    }

    @Override
    public FPQuadruple square() {
        internalValue.multiply(internalValue);
        return this;
    }

    @Override
    public long getLong() {
        return internalValue.longValue();
    }

    @Override
    public FPQuadruple clone() {
        return new FPQuadruple(new Quadruple(internalValue));
    }

    @Override
    public String toString() {
        return internalValue.format("%f");
    }
}
