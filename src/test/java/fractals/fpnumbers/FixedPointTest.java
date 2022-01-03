package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointTest {

    @Test
    public void constructorTestPositiveFractional() {
        FPDecimal1 fixedPoint = new FPDecimal1("2.345").multiply(1000);
        Assertions.assertEquals(2345, fixedPoint.getLong());
    }

    @Test
    public void constructorTestPositiveWhole() {
        FPDecimal1 fixedPoint = new FPDecimal1("2");
        Assertions.assertEquals(2, fixedPoint.getLong());
    }

    @Test
    public void constructorTestNegativeFractional() {
        FPDecimal1 fixedPoint = new FPDecimal1("-2.345").multiply(1000);
        Assertions.assertEquals(-2345, fixedPoint.getLong());
    }

    @Test
    public void constructorTestNegativeWhole() {
        FPDecimal1 fixedPoint = new FPDecimal1("-2345");
        Assertions.assertEquals(-2345, fixedPoint.getLong());
    }

    @Test
    public void invalidMultiDotPositive() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FPDecimal1 fixedPoint = new FPDecimal1("2.34.5");
        });
    }

    @Test
    public void invalidMultiDotNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FPDecimal1 fixedPoint = new FPDecimal1("-2.34.5");
        });
    }

    @Test
    public void invalidMultiNegativeSymbols() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FPDecimal1 fixedPoint = new FPDecimal1("-2.34-5");
        });
    }

    @Test
    public void invalidNegativeSymbolInWrongPlace() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FPDecimal1 fixedPoint = new FPDecimal1("2-34.5");
        });
    }
}
