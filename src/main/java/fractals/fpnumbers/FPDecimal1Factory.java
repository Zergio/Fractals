package fractals.fpnumbers;

public class FPDecimal1Factory extends FPNumberFactory<FPDecimal1> {

    @Override
    public FPDecimal1 createFPNumber(long number) {
        return new FPDecimal1(number);
    }

    @Override
    public FPDecimal1 createFPNumber(String number) {
        return new FPDecimal1(number);
    }

    @Override
    public FPDecimal1 createFPNumber(long longPart, long scalePart) {
        return new FPDecimal1(longPart, scalePart);
    }
}