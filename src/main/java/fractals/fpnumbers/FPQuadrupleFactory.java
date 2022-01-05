package fractals.fpnumbers;

public class FPQuadrupleFactory extends FPNumberFactory<FPQuadruple> {

    @Override
    public FPQuadruple createFPNumber(long number) {
        return new FPQuadruple(number);
    }

    @Override
    public FPQuadruple createFPNumber(String number) {
        return new FPQuadruple(number);
    }

    @Override
    public FPQuadruple createFPNumber(long longPart, long scalePart) {
        if (scalePart > 0) {
            return new FPQuadruple(longPart * (Utils.POWERS_OF_TEN[(int) scalePart]));
        } else {
            return new FPQuadruple(longPart / (Utils.NEG_POWERS_OF_TEN[(int) -scalePart]));
        }
    }
}
