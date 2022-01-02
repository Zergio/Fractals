package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointNumberOfDigitsTest {

    @Test
    public void digitCounterTestPowerOfTen() {
        FPNumber2 num = new FPNumber2("100000");
        int digits = num.getNumberOfDigits(num.getMantissa());
        Assertions.assertEquals(6, digits);
    }

    @Test
    public void digitCounterTestPowerOfTenMinusOne() {
        FPNumber2 num = new FPNumber2("999999");
        int digits = num.getNumberOfDigits(num.getMantissa());
        Assertions.assertEquals(6, digits);
    }

    @Test
    public void digitCounterTestPowerOfTenPlusOne() {
        FPNumber2 num = new FPNumber2("100001");
        int digits = num.getNumberOfDigits(num.getMantissa());
        Assertions.assertEquals(6, digits);
    }

    @Test
    public void digitCounterTestOne() {
        FPNumber2 num = new FPNumber2("1");
        int digits = num.getNumberOfDigits(num.getMantissa());
        Assertions.assertEquals(1, digits);
    }

    @Test
    public void digitCounterTestZero() {
        FPNumber2 num = new FPNumber2("0");
        int digits = num.getNumberOfDigits(num.getMantissa());
        Assertions.assertEquals(1, digits);
    }

    @Test
    public void digitCounterTestTenToPowerOfX() {
        long c = 1;
        for (int i = 1; i <= 19; i++) {
            FPNumber2 num = new FPNumber2(c);
            int digits = num.getNumberOfDigits(num.getMantissa());
            Assertions.assertEquals(i, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestTenToPowerOfXPlusOne() {
        long c = 1;
        for (int i = 1; i <= 19; i++) {
            FPNumber2 num = new FPNumber2(c + 1);
            int digits = num.getNumberOfDigits(num.getMantissa());
            Assertions.assertEquals(i, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestTenToPowerOfXMinusOne() {
        long c = 10;
        for (int i = 2; i <= 19; i++) {
            FPNumber2 num = new FPNumber2(c - 1);
            int digits = num.getNumberOfDigits(num.getMantissa());
            Assertions.assertEquals(i - 1, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestMinusTenToPowerOfX() {
        long c = -1;
        for (int i = 1; i <= 19; i++) {
            FPNumber2 num = new FPNumber2(c);
            int digits = num.getNumberOfDigits(num.getMantissa());
            Assertions.assertEquals(i, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestMinusTenToPowerOfXMinusOne() {
        long c = -1;
        for (int i = 1; i <= 19; i++) {
            FPNumber2 num = new FPNumber2(c - 1);
            int digits = num.getNumberOfDigits(num.getMantissa());
            Assertions.assertEquals(i, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestMinusTenToPowerOfXPlusOne() {
        long c = -10;
        for (int i = 2; i <= 19; i++) {
            FPNumber2 num = new FPNumber2(c + 1);
            int digits = num.getNumberOfDigits(num.getMantissa());
            Assertions.assertEquals(i - 1, digits);
            c *= 10;
        }
    }

    @Test
    public void digitCounterTestMaxValue() {
        FPNumber2 num = new FPNumber2(Long.MAX_VALUE);
        int digits = num.getNumberOfDigits(num.getMantissa());
        Assertions.assertEquals(19, digits);
    }

    @Test
    public void digitCounterTestMaxValueMinusOne() {
        FPNumber2 num = new FPNumber2(Long.MAX_VALUE - 1);
        int digits = num.getNumberOfDigits(num.getMantissa());
        Assertions.assertEquals(19, digits);
    }

    @Test
    public void digitCounterTestMinValue() {
        FPNumber2 num = new FPNumber2(Long.MIN_VALUE);
        int digits = num.getNumberOfDigits(num.getMantissa());
        Assertions.assertEquals(19, digits);
    }

    @Test
    public void digitCounterTestMinValuePlusOne() {
        FPNumber2 num = new FPNumber2(Long.MIN_VALUE + 1);
        int digits = num.getNumberOfDigits(num.getMantissa());
        Assertions.assertEquals(19, digits);
    }
}
