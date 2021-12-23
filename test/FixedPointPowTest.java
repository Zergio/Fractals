import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointPowTest {
    @Test
    public void powPositive() {
        FixedPoint num = new FixedPoint("1.234");
        num.square();
        Assertions.assertEquals(1.522756, num.getProperNumber());
    }
    @Test
    public void powNegative() {
        FixedPoint num = new FixedPoint("-1.234");
        num.square();
        Assertions.assertEquals(1.522756, num.getProperNumber());
    }
}
