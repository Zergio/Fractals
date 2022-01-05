package fractals.fpnumbers;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Utils {
    public static MathContext CONTEXT = new MathContext(100, RoundingMode.HALF_EVEN); //new MathContext(128, RoundingMode.HALF_EVEN);

    public static BigDecimal TWO = new BigDecimal(2, CONTEXT);

    public static int getDecimalFractionLengh(@NotNull String decimal) {
        return decimal.length() - decimal.indexOf('.') - 1;
    }

    public static final long[] POWERS_OF_TEN = new long[19];

    public static final long[] NEG_POWERS_OF_TEN = new long[19];

    public static final FPDecimal1 DECIMAL_1_ZERO = new FPDecimal1("0");

    static {
        long c = 1;
        for (int i = 0; i < 19; i++) {
            POWERS_OF_TEN[i] = c;
            NEG_POWERS_OF_TEN[i] = -c;
            c *= 10;
        }
    }
}
