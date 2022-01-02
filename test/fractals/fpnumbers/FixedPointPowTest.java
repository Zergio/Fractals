package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointPowTest {

    @Test
    public void powPositive() {
        FPNumber1 num = new FPNumber1("1.234");
        num.square().multiply(1000000);
        Assertions.assertEquals(1522756, num.getScaledInteger());
    }

    @Test
    public void powNegative() {
        FPNumber1 num = new FPNumber1("-1.234");
        num.square().multiply(1000000);
        Assertions.assertEquals(1522756, num.getScaledInteger());
    }
}
