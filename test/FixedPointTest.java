import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointTest {

    @Test
    public void constructorTestPositive() {
        FixedPoint fixedPoint = new FixedPoint("2.345");
        Assertions.assertEquals(2345, fixedPoint.getNumber());
        Assertions.assertEquals(3, fixedPoint.getScale());
    }

    @Test
    public void constructorTestNegative() {
        FixedPoint fixedPoint = new FixedPoint("-2.345");
        Assertions.assertEquals(-2345, fixedPoint.getNumber());
        Assertions.assertEquals(3, fixedPoint.getScale());
    }

    @Test
    public void invalidMultiDotPositive() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FixedPoint fixedPoint = new FixedPoint("2.34.5");
        });
    }

    @Test
    public void invalidMultiDotNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FixedPoint fixedPoint = new FixedPoint("-2.34.5");
        });
    }

    @Test
    public void invalidMultiNegativeSymbols() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FixedPoint fixedPoint = new FixedPoint("-2.34-5");
        });
    }

    @Test
    public void invalidNegativeSymbolInWrongPlace() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FixedPoint fixedPoint = new FixedPoint("2-34.5");
        });
    }
}
