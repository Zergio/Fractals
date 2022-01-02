package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointAdditionTest {

    @Test
    public void additionTestEqualPositive() {
        FPNumber1 num1 = new FPNumber1("2.345");
        FPNumber1 num2 = new FPNumber1("2.345");
        num1.add(num2).multiply(100);
        Assertions.assertEquals(469, num1.getScaledInteger());
    }

    @Test
    public void additionTestSmallBigPositive() {
        FPNumber1 num1 = new FPNumber1("1.111");
        FPNumber1 num2 = new FPNumber1("2.345");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(3456, num1.getScaledInteger());
    }

    @Test
    public void additionTestBigSmallPositive() {
        FPNumber1 num1 = new FPNumber1("2.345");
        FPNumber1 num2 = new FPNumber1("1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(3456, num1.getScaledInteger());
    }

    @Test
    public void additionTestScalePositive1() {
        FPNumber1 num1 = new FPNumber1("2.34");
        FPNumber1 num2 = new FPNumber1("1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(3451, num1.getScaledInteger());
    }

    @Test
    public void additionTestScalePositive2() {
        FPNumber1 num1 = new FPNumber1("2.345");
        FPNumber1 num2 = new FPNumber1("1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(3455, num1.getScaledInteger());
    }

    @Test
    public void additionTestEqualNegativePositive() {
        FPNumber1 num1 = new FPNumber1("-2.345");
        FPNumber1 num2 = new FPNumber1("2.345");
        num1.add(num2);
        Assertions.assertEquals(0, num1.getScaledInteger());
    }

    @Test
    public void additionTestSmallNegativePositive() {
        FPNumber1 num1 = new FPNumber1("-1.111");
        FPNumber1 num2 = new FPNumber1("2.345");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(1234, num1.getScaledInteger());
    }

    @Test
    public void additionTestNegativeSmallNegative() {
        FPNumber1 num1 = new FPNumber1("-2.345");
        FPNumber1 num2 = new FPNumber1("-1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-3456, num1.getScaledInteger());
    }

    @Test
    public void additionTestScalePositiveNegative1() {
        FPNumber1 num1 = new FPNumber1("-2.34");
        FPNumber1 num2 = new FPNumber1("1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-1229, num1.getScaledInteger());
    }

    @Test
    public void additionTestScalePositiveNegative2() {
        FPNumber1 num1 = new FPNumber1("-2.345");
        FPNumber1 num2 = new FPNumber1("1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-1235, num1.getScaledInteger());
    }

    @Test
    public void additionTestScalePositiveNegative3() {
        FPNumber1 num1 = new FPNumber1("2.34");
        FPNumber1 num2 = new FPNumber1("-1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(1229, num1.getScaledInteger());
    }

    @Test
    public void additionTestScalePositiveNegative4() {
        FPNumber1 num1 = new FPNumber1("2.345");
        FPNumber1 num2 = new FPNumber1("-1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(1235, num1.getScaledInteger());
    }


    @Test
    public void additionTestScaleNegative1() {
        FPNumber1 num1 = new FPNumber1("-2.34");
        FPNumber1 num2 = new FPNumber1("-1.111");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-3451, num1.getScaledInteger());
    }

    @Test
    public void additionTestScaleNegative2() {
        FPNumber1 num1 = new FPNumber1("-2.345");
        FPNumber1 num2 = new FPNumber1("-1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-3455, num1.getScaledInteger());
    }

    @Test
    public void additionTestScale1() {
        FPNumber1 num1 = new FPNumber1("-0.00030004");
        FPNumber1 num2 = new FPNumber1("-1.111");
        num1.add(num2).multiply(100000000);
        Assertions.assertEquals(-111130004, num1.getScaledInteger());
    }

    @Test
    public void additionTestScale2() {
        FPNumber1 num1 = new FPNumber1("-2.345");
        FPNumber1 num2 = new FPNumber1("-1.11");
        num1.add(num2).multiply(1000);
        Assertions.assertEquals(-3455, num1.getScaledInteger());
    }
}
