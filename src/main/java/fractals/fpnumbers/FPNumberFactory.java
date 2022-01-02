package fractals.fpnumbers;

public abstract class FPNumberFactory<T extends FPNumber<T>> {

    public abstract T createFPNumber(long number);

    public abstract T createFPNumber(String number);
}
