package fractals.fpnumbers;

import fractals.Utils;

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
        decimal = decimal.multiply(new BigDecimal(number, Utils.CONTEXT), Utils.CONTEXT);
        return this;
    }

    @Override
    public FPBigDecimal divide(FPBigDecimal number) {
        decimal = decimal.divide(number.decimal, Utils.CONTEXT);
        return this;
    }

    @Override
    public FPBigDecimal square() {
        decimal = decimal.multiply(decimal, Utils.CONTEXT);
        return this;
    }

    BigDecimal MAX_LONG = new BigDecimal(Long.MAX_VALUE);
    BigDecimal MIN_LONG = new BigDecimal(Long.MIN_VALUE);

    @Override
    public long getLong() {
        if (decimal.signum() >= 0) {
            if (decimal.compareTo(MAX_LONG) >= 0) {
                return new BigDecimal(decimal.unscaledValue()).movePointLeft(decimal.scale()).longValue();
            } else {
                return decimal.longValue();
            }
        } else {
            if (decimal.compareTo(MIN_LONG) < 0) {
                return new BigDecimal(decimal.unscaledValue()).movePointLeft(decimal.scale()).longValue();
            } else {
                return decimal.longValue();
            }
        }
    }

    @Override
    public FPBigDecimal clone() {
        return new FPBigDecimal(new BigDecimal(decimal.unscaledValue(), decimal.scale(), Utils.CONTEXT));
    }

    @Override
    public String toString() {
        return decimal.toPlainString();
    }
}
