package fractals.fpnumbers;

public class FPNumber1Factory extends FPNumberFactory<FPNumber1> {

    @Override
    public FPNumber1 createFPNumber(long number) {
        return new FPNumber1(number);
    }

    @Override
    public FPNumber1 createFPNumber(String number) {
        return new FPNumber1(number);
    }
}