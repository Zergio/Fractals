package fractals.fpnumbers;

public class FPDoubleFactory extends FPNumberFactory<FPDouble> {

    @Override
    public FPDouble createFPNumber(long number) {
        return new FPDouble(number);
    }

    @Override
    public FPDouble createFPNumber(String number) {
        return new FPDouble(number);
    }
}
