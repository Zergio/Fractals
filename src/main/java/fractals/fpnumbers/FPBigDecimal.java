package fractals.fpnumbers;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FPBigDecimal implements FPNumber<FPBigDecimal> {

    private BigDecimal decimal;

    FPBigDecimal(String number) {
        decimal = new BigDecimal(number, Utils.CONTEXT);
    }

    FPBigDecimal(long number) {
        decimal = new BigDecimal(number, Utils.CONTEXT);
    }

    FPBigDecimal(long number, long scale) {
        decimal = new BigDecimal(BigInteger.valueOf(number), (int)-scale, Utils.CONTEXT);
    }

    private FPBigDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    @Override
    public long getMantissa() {
        return decimal.unscaledValue().longValue();
    }

    @Override
    public BigInteger getMantissaAsBigInteger() {
        return decimal.unscaledValue();
    }

    @Override
    public long getScale() {
        return -decimal.scale();
    }

    @Override
    public FPBigDecimal add(FPBigDecimal number) {
        decimal = decimal.add(number.decimal, Utils.CONTEXT);
        return this;
    }

    @Override
    public FPBigDecimal subtract(FPBigDecimal number) {
        decimal = decimal.subtract(number.decimal, Utils.CONTEXT);
        return this;
    }

    @Override
    public FPBigDecimal multiply(FPBigDecimal number) {
        decimal = decimal.multiply(number.decimal, Utils.CONTEXT);
        return this;
    }

    @Override
    public FPBigDecimal multiply(long number) {
        decimal = decimal.divide(new BigDecimal(number, Utils.CONTEXT), Utils.CONTEXT);
        return this;
    }

    @Override
    public FPBigDecimal divide(FPBigDecimal number) {
        decimal = decimal.divide(number.decimal, Utils.CONTEXT);
        return this;
    }

    @Override
    public FPBigDecimal square() {
        decimal = decimal.multiply(Utils.TWO, Utils.CONTEXT);
        return this;
    }

    @Override
    public long getScaledInteger() {
        return new BigDecimal(decimal.unscaledValue()).movePointLeft(decimal.scale()).longValue();
    }

    @Override
    public FPBigDecimal clone() {
        return new FPBigDecimal(new BigDecimal(decimal.unscaledValue(), decimal.scale(), Utils.CONTEXT));
    }
}
