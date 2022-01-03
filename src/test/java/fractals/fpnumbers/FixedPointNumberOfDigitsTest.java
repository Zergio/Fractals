package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointNumberOfDigitsTest {

    @Test
    public void digitCounterTestPowerOfTen() {
        FPDecimal2 num = new FPDecimal2("100000");
        int digits = num.getNumberOfDigits(num.getLong());
        Assertions.assertEquals(6, digits);
    }

    @Test
    public void digitCounterTestPowerOfTenMinusOne() {
        FPDecimal2 num = new FPDecimal2("999999");
        int digits = num.getNumberOfDigits(num.getLong());
        Assertions.assertEquals(6, digits);
    }

    @Test
    public void digitCounterTestPowerOfTenPlusOne() {
        FPDecimal2 num = new FPDecimal2("100001");
        int digits = num.getNumberOfDigits(num.getLong());
        Assertions.assertEquals(6, digits);
    }

    @Test
    public void digitCounterTestOne() {
        FPDecimal2 num = new FPDecimal2("1");
        int digits = num.getNumberOfDigits(num.getLong());
        Assertions.assertEquals(1, digits);
    }

    @Test
    public void digitCounterTestZero() {
        FPDecimal2 num = new FPDecimal2("0");
        int digits = num.getNumberOfDigits(num.getLong());
        Assertions.assertEquals(1, digits);
    }

    @Test
    public void digitCounterTestTenToPowerOfX() {
        long c = 1;
        for (int i = 1; i <= 19; i++) {
            FPDecimal2 num = new FPDecimal2(c);
            int digits = num.getNumberOfDigits(num.getLong());
            Assertions.assertEquals(i, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestTenToPowerOfXPlusOne() {
        long c = 1;
        for (int i = 1; i <= 19; i++) {
            FPDecimal2 num = new FPDecimal2(c + 1);
            int digits = num.getNumberOfDigits(num.getLong());
            Assertions.assertEquals(i, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestTenToPowerOfXMinusOne() {
        long c = 10;
        for (int i = 2; i <= 19; i++) {
            FPDecimal2 num = new FPDecimal2(c - 1);
            int digits = num.getNumberOfDigits(num.getLong());
            Assertions.assertEquals(i - 1, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestMinusTenToPowerOfX() {
        long c = -1;
        for (int i = 1; i <= 19; i++) {
            FPDecimal2 num = new FPDecimal2(c);
            int digits = num.getNumberOfDigits(num.getLong());
            Assertions.assertEquals(i, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestMinusTenToPowerOfXMinusOne() {
        long c = -1;
        for (int i = 1; i <= 19; i++) {
            FPDecimal2 num = new FPDecimal2(c - 1);
            int digits = num.getNumberOfDigits(num.getLong());
            Assertions.assertEquals(i, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestMinusTenToPowerOfXPlusOne() {
        long c = -10;
        for (int i = 2; i <= 19; i++) {
            FPDecimal2 num = new FPDecimal2(c + 1);
            int digits = num.getNumberOfDigits(num.getLong());
            Assertions.assertEquals(i - 1, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestMaxValue() {
        FPDecimal2 num = new FPDecimal2(Long.MAX_VALUE);
        int digits = num.getNumberOfDigits(num.getLong());
        Assertions.assertEquals(19, digits);
    }

    @Test
    public void digitCounterTestMaxValueMinusOne() {
        FPDecimal2 num = new FPDecimal2(Long.MAX_VALUE - 1);
        int digits = num.getNumberOfDigits(num.getLong());
        Assertions.assertEquals(19, digits);
    }

    @Test
    public void digitCounterTestMinValue() {
        FPDecimal2 num = new FPDecimal2(Long.MIN_VALUE);
        int digits = num.getNumberOfDigits(num.getLong());
        Assertions.assertEquals(19, digits);
    }

    @Test
    public void digitCounterTestMinValuePlusOne() {
        FPDecimal2 num = new FPDecimal2(Long.MIN_VALUE + 1);
        int digits = num.getNumberOfDigits(num.getLong());
        Assertions.assertEquals(19, digits);
    }
}
