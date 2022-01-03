package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointAdditionTest {

    @Test
    public void additionTestEqualPositive() {
        FPDecimal1 num1 = new FPDecimal1("2.345");
        FPDecimal1 num2 = new FPDecimal1("2.345");
        num1.add(num2).multiply(100);
        Assertions.assertEquals(469, num1.getLong());
    }

    @Test
    public void additionTestSmallBigPositive() {
        FPDecimal1 num1 = new FPDecimal1("1.111");
        FPDecimal1 num2 = new FPDecimal1("2.345");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(3456, num1.getLong());
    }

    @Test
    public void additionTestBigSmallPositive() {
        FPDecimal1 num1 = new FPDecimal1("2.345");
        FPDecimal1 num2 = new FPDecimal1("1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(3456, num1.getLong());
    }

    @Test
    public void additionTestScalePositive1() {
        FPDecimal1 num1 = new FPDecimal1("2.34");
        FPDecimal1 num2 = new FPDecimal1("1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(3451, num1.getLong());
    }

    @Test
    public void additionTestScalePositive2() {
        FPDecimal1 num1 = new FPDecimal1("2.345");
        FPDecimal1 num2 = new FPDecimal1("1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(3455, num1.getLong());
    }

    @Test
    public void additionTestEqualNegativePositive() {
        FPDecimal1 num1 = new FPDecimal1("-2.345");
        FPDecimal1 num2 = new FPDecimal1("2.345");
        num1.add(num2);
        Assertions.assertEquals(0, num1.getLong());
    }

    @Test
    public void additionTestSmallNegativePositive() {
        FPDecimal1 num1 = new FPDecimal1("-1.111");
        FPDecimal1 num2 = new FPDecimal1("2.345");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(1234, num1.getLong());
    }

    @Test
    public void additionTestNegativeSmallNegative() {
        FPDecimal1 num1 = new FPDecimal1("-2.345");
        FPDecimal1 num2 = new FPDecimal1("-1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-3456, num1.getLong());
    }

    @Test
    public void additionTestScalePositiveNegative1() {
        FPDecimal1 num1 = new FPDecimal1("-2.34");
        FPDecimal1 num2 = new FPDecimal1("1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-1229, num1.getLong());
    }

    @Test
    public void additionTestScalePositiveNegative2() {
        FPDecimal1 num1 = new FPDecimal1("-2.345");
        FPDecimal1 num2 = new FPDecimal1("1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-1235, num1.getLong());
    }

    @Test
    public void additionTestScalePositiveNegative3() {
        FPDecimal1 num1 = new FPDecimal1("2.34");
        FPDecimal1 num2 = new FPDecimal1("-1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(1229, num1.getLong());
    }

    @Test
    public void additionTestScalePositiveNegative4() {
        FPDecimal1 num1 = new FPDecimal1("2.345");
        FPDecimal1 num2 = new FPDecimal1("-1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(1235, num1.getLong());
    }


    @Test
    public void additionTestScaleNegative1() {
        FPDecimal1 num1 = new FPDecimal1("-2.34");
        FPDecimal1 num2 = new FPDecimal1("-1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-3451, num1.getLong());
    }

    @Test
    public void additionTestScaleNegative2() {
        FPDecimal1 num1 = new FPDecimal1("-2.345");
        FPDecimal1 num2 = new FPDecimal1("-1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-3455, num1.getLong());
    }

    @Test
    public void additionTestScale1() {
        FPDecimal1 num1 = new FPDecimal1("-0.00030004");
        FPDecimal1 num2 = new FPDecimal1("-1.111");
        num1.add(num2).multiply(100000000);
        Assertions.assertEquals(-111130004, num1.getLong());
    }

    @Test
    public void additionTestScale2() {
        FPDecimal1 num1 = new FPDecimal1("-2.345");
        FPDecimal1 num2 = new FPDecimal1("-1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-3455, num1.getLong());
    }
}
