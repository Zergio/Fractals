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

    @Override
    public FPDouble createFPNumber(long longPart, long scalePart) {
        if (scalePart > 0) {
            return new FPDouble(longPart * (Utils.POWERS_OF_TEN[(int) scalePart]));
        } else {
            return new FPDouble(longPart / (Utils.NEG_POWERS_OF_TEN[(int) -scalePart]));
        }
    }
}
