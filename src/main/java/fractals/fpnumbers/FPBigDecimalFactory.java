package fractals.fpnumbers;

public class FPBigDecimalFactory extends FPNumberFactory<FPBigDecimal> {
    @Override
    public FPBigDecimal createFPNumber(long number) {
        return new FPBigDecimal(number);
    }

    @Override
    public FPBigDecimal createFPNumber(String number) {
        return new FPBigDecimal(number);
    }
}
