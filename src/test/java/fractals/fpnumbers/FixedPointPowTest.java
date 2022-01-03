package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointPowTest {

    @Test
    public void powPositive() {
        FPDecimal1 num = new FPDecimal1("1.234");
        num.square().multiply(1000000);
        Assertions.assertEquals(1522756, num.getLong());
    }

    @Test
    public void powNegative() {
        FPDecimal1 num = new FPDecimal1("-1.234");
        num.square().multiply(1000000);
        Assertions.assertEquals(1522756, num.getLong());
    }
}
