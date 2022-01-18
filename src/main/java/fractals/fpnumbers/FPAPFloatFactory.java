package fractals.fpnumbers;

public class FPAPFloatFactory extends FPNumberFactory<FPAPFloat> {
    @Override
    public FPAPFloat createFPNumber(long number) {
        return new FPAPFloat(number);
    }

    @Override
    public FPAPFloat createFPNumber(String number) {
        return new FPAPFloat(number);
    }
}
