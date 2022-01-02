package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointTest {

    @Test
    public void constructorTestPositiveFractional() {
        FPNumber1 fixedPoint = new FPNumber1("2.345");
        Assertions.assertEquals(2345, fixedPoint.getMantissa());
        Assertions.assertEquals(-3, fixedPoint.getScale());
    }

    @Test
    public void constructorTestPositiveWhole() {
        FPNumber1 fixedPoint = new FPNumber1("2");
        Assertions.assertEquals(2, fixedPoint.getMantissa());
        Assertions.assertEquals(0, fixedPoint.getScale());
    }

    @Test
    public void constructorTestNegativeFractional() {
        FPNumber1 fixedPoint = new FPNumber1("-2.345");
        Assertions.assertEquals(-2345, fixedPoint.getMantissa());
        Assertions.assertEquals(-3, fixedPoint.getScale());
    }

    @Test
    public void constructorTestNegativeWhole() {
        FPNumber1 fixedPoint = new FPNumber1("-2345");
        Assertions.assertEquals(-2345, fixedPoint.getMantissa());
        Assertions.assertEquals(0, fixedPoint.getScale());
    }

    @Test
    public void invalidMultiDotPositive() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FPNumber1 fixedPoint = new FPNumber1("2.34.5");
        });
    }

    @Test
    public void invalidMultiDotNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FPNumber1 fixedPoint = new FPNumber1("-2.34.5");
        });
    }

    @Test
    public void invalidMultiNegativeSymbols() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FPNumber1 fixedPoint = new FPNumber1("-2.34-5");
        });
    }

    @Test
    public void invalidNegativeSymbolInWrongPlace() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FPNumber1 fixedPoint = new FPNumber1("2-34.5");
        });
    }
}
