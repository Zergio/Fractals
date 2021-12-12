import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Utils {
    public static MathContext CONTEXT = new MathContext(34, RoundingMode.HALF_EVEN);
    public static BigDecimal TWO = new BigDecimal(2, CONTEXT);
}
