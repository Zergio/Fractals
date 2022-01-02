package fractals.fpnumbers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Utils {
    public static MathContext CONTEXT = MathContext.DECIMAL128; //new MathContext(128, RoundingMode.HALF_EVEN);
    public static BigDecimal TWO = new BigDecimal(2, CONTEXT);
}
