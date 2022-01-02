package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointShiftingTest {
   
    @Test
    public void normalShiftRightByOne() {
        FPNumber number = new FPNumber1("4");
        long[] n = new long[] {0, number.getMantissa()};
        n = FPNumber1.shiftRight(n, 1);
        Assertions.assertEquals(2, n[1]);
    }

    @Test
    public void normalShiftRightByTen() {
        FPNumber1 number = new FPNumber1("1024");
        long[] n = new long[] {0, number.getMantissa()};
        n = FPNumber1.shiftRight(n, 10);
        Assertions.assertEquals(1, n[1]);
    }

    @Test
    public void normalShiftLeftByOne() {
        FPNumber1 number = new FPNumber1("1");
        long[] n = new long[] {0, number.getMantissa()};
        n = FPNumber1.shiftLeft(n, 1);
        Assertions.assertEquals(2, n[1]);
    }

    @Test
    public void normalShiftLeftByTen() {
        FPNumber1 number = new FPNumber1("1");
        long[] n = new long[] {0, number.getMantissa()};
        n = FPNumber1.shiftLeft(n, 10);
        Assertions.assertEquals(1024, n[1]);
    }

    @Test
    public void carryShiftRight1() {
        FPNumber1 number = new FPNumber1("1");
        long[] n = new long[] {number.getMantissa(), 0};
        n = FPNumber1.shiftRight(n, 1);
        Assertions.assertEquals(-9223372036854775808L, n[1]);
        Assertions.assertEquals(0, n[0]);
    }

    @Test
    public void carryShiftRight2() {
        FPNumber1 number = new FPNumber1("1");
        long[] n = new long[] {number.getMantissa(), 0};
        n = FPNumber1.shiftRight(n, 63);
        Assertions.assertEquals(2, n[1]);
        Assertions.assertEquals(0, n[0]);
    }

    @Test
    public void carryShiftRight3() {
        FPNumber1 number = new FPNumber1("16");
        long[] n = new long[] {number.getMantissa(), 0};
        n = FPNumber1.shiftRight(n, 20);
        Assertions.assertEquals(2, n[1]);
        Assertions.assertEquals(0, n[0]);
    }

    @Test
    public void carryShiftLeft1() {
        FPNumber1 number = new FPNumber1("-9223372036854775808");
        long[] n = new long[] {0, number.getMantissa()};
        n = FPNumber1.shiftLeft(n, 1);
        Assertions.assertEquals(0, n[1]);
        Assertions.assertEquals(1, n[0]);
    }

    @Test
    public void carryShiftLeft2() {
        FPNumber1 number = new FPNumber1("1");
        long[] n = new long[] {0, number.getMantissa()};
        n = FPNumber1.shiftLeft(n, 63);
        Assertions.assertEquals(0, n[1]);
        Assertions.assertEquals(-9223372036854775808L, n[0]);
    }

    @Test
    public void carryShiftLeft3() {
        FPNumber1 number = new FPNumber1("16");
        long[] n = new long[] {0, number.getMantissa()};
        n = FPNumber1.shiftLeft(n, 20);
        Assertions.assertEquals(0, n[1]);
        Assertions.assertEquals(-9223372036854775808L, n[0]);
    }
}
