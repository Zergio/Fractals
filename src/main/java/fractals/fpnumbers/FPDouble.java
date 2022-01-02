package fractals.fpnumbers;

public class FPDouble extends AbstractFPNumber<FPDouble>{
    FPDouble(String number) {
        super(number.replaceFirst(".",""));
        scale = getDecimalFractionLengh(number);
    }

    FPDouble(long number) {
        super(number);
    }

    FPDouble(long number, long scale) {
        super(number, scale);
    }

    @Override
    public FPDouble multiply(FPDouble number) {
        return null;
    }

    @Override
    public FPDouble multiply(long number) {
        return null;
    }

    @Override
    public FPDouble divide(FPDouble number) {
        return null;
    }
}
