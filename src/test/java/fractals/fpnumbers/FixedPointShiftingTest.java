package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointShiftingTest {
   
    @Test
    public void normalShiftRightByOne() {
        FPNumber number = new FPDecimal1("4");
        long[] n = new long[] {0, number.getLong()};
        n = FPDecimal1.shiftRight(n, 1);
        Assertions.assertEquals(2, n[1]);
    }

    @Test
    public void normalShiftRightByTen() {
        FPDecimal1 number = new FPDecimal1("1024");
        long[] n = new long[] {0, number.getLong()};
        n = FPDecimal1.shiftRight(n, 10);
        Assertions.assertEquals(1, n[1]);
    }

    @Test
    public void normalShiftLeftByOne() {
        FPDecimal1 number = new FPDecimal1("1");
        long[] n = new long[] {0, number.getLong()};
        n = FPDecimal1.shiftLeft(n, 1);
        Assertions.assertEquals(2, n[1]);
    }

    @Test
    public void normalShiftLeftByTen() {
        FPDecimal1 number = new FPDecimal1("1");
        long[] n = new long[] {0, number.getLong()};
        n = FPDecimal1.shiftLeft(n, 10);
        Assertions.assertEquals(1024, n[1]);
    }

    @Test
    public void carryShiftRight1() {
        FPDecimal1 number = new FPDecimal1("1");
        long[] n = new long[] {number.getLong(), 0};
        n = FPDecimal1.shiftRight(n, 1);
        Assertions.assertEquals(-9223372036854775808L, n[1]);
        Assertions.assertEquals(0, n[0]);
    }

    @Test
    public void carryShiftRight2() {
        FPDecimal1 number = new FPDecimal1("1");
        long[] n = new long[] {number.getLong(), 0};
        n = FPDecimal1.shiftRight(n, 63);
        Assertions.assertEquals(2, n[1]);
        Assertions.assertEquals(0, n[0]);
    }

    @Test
    public void carryShiftRight3() {
        FPDecimal1 number = new FPDecimal1("16");
        long[] n = new long[] {number.getLong(), 0};
        n = FPDecimal1.shiftRight(n, 20);
        Assertions.assertEquals(2, n[1]);
        Assertions.assertEquals(0, n[0]);
    }

    @Test
    public void carryShiftLeft1() {
        FPDecimal1 number = new FPDecimal1("-9223372036854775808");
        long[] n = new long[] {0, number.getLong()};
        n = FPDecimal1.shiftLeft(n, 1);
        Assertions.assertEquals(0, n[1]);
        Assertions.assertEquals(1, n[0]);
    }

    @Test
    public void carryShiftLeft2() {
        FPDecimal1 number = new FPDecimal1("1");
        long[] n = new long[] {0, number.getLong()};
        n = FPDecimal1.shiftLeft(n, 63);
        Assertions.assertEquals(0, n[1]);
        Assertions.assertEquals(-9223372036854775808L, n[0]);
    }

    @Test
    public void carryShiftLeft3() {
        FPDecimal1 number = new FPDecimal1("16");
        long[] n = new long[] {0, number.getLong()};
        n = FPDecimal1.shiftLeft(n, 20);
        Assertions.assertEquals(0, n[1]);
        Assertions.assertEquals(-9223372036854775808L, n[0]);
    }
}
