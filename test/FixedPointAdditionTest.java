import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointAdditionTest {
    @Test
    public void additionTestEqualPositive() {
        FixedPoint num1 = new FixedPoint("2.345");
        FixedPoint num2 = new FixedPoint("2.345");
        num1.add(num2);
        Assertions.assertEquals(4.69, num1.getProperNumber());
    }

    @Test
    public void additionTestSmallBigPositive() {
        FixedPoint num1 = new FixedPoint("1.111");
        FixedPoint num2 = new FixedPoint("2.345");
        num1.add(num2);
        Assertions.assertEquals(3.456, num1.getProperNumber());
    }

    @Test
    public void additionTestBigSmallPositive() {
        FixedPoint num1 = new FixedPoint("2.345");
        FixedPoint num2 = new FixedPoint("1.111");
        num1.add(num2);
        Assertions.assertEquals(3.456, num1.getProperNumber());
    }

    @Test
    public void additionTestScalePositive1() {
        FixedPoint num1 = new FixedPoint("2.34");
        FixedPoint num2 = new FixedPoint("1.111");
        num1.add(num2);
        Assertions.assertEquals(3.451, num1.getProperNumber());
    }

    @Test
    public void additionTestScalePositive2() {
        FixedPoint num1 = new FixedPoint("2.345");
        FixedPoint num2 = new FixedPoint("1.11");
        num1.add(num2);
        Assertions.assertEquals(3.455, num1.getProperNumber());
    }

    @Test
    public void additionTestEqualNegativePositive() {
        FixedPoint num1 = new FixedPoint("-2.345");
        FixedPoint num2 = new FixedPoint("2.345");
        num1.add(num2);
        Assertions.assertEquals(0.0, num1.getProperNumber());
    }

    @Test
    public void additionTestSmallNegativePositive() {
        FixedPoint num1 = new FixedPoint("-1.111");
        FixedPoint num2 = new FixedPoint("2.345");
        num1.add(num2);
        Assertions.assertEquals(1.234, num1.getProperNumber());
    }

    @Test
    public void additionTestNegativeSmallNegative() {
        FixedPoint num1 = new FixedPoint("-2.345");
        FixedPoint num2 = new FixedPoint("-1.111");
        num1.add(num2);
        Assertions.assertEquals(-3.456, num1.getProperNumber());
    }

    @Test
    public void additionTestScalePositiveNegative1() {
        FixedPoint num1 = new FixedPoint("-2.34");
        FixedPoint num2 = new FixedPoint("1.111");
        num1.add(num2);
        Assertions.assertEquals(-1.229, num1.getProperNumber());
    }

    @Test
    public void additionTestScalePositiveNegative2() {
        FixedPoint num1 = new FixedPoint("-2.345");
        FixedPoint num2 = new FixedPoint("1.11");
        num1.add(num2);
        Assertions.assertEquals(-1.235, num1.getProperNumber());
    }

    @Test
    public void additionTestScalePositiveNegative3() {
        FixedPoint num1 = new FixedPoint("2.34");
        FixedPoint num2 = new FixedPoint("-1.111");
        num1.add(num2);
        Assertions.assertEquals(1.229, num1.getProperNumber());
    }

    @Test
    public void additionTestScalePositiveNegative4() {
        FixedPoint num1 = new FixedPoint("2.345");
        FixedPoint num2 = new FixedPoint("-1.11");
        num1.add(num2);
        Assertions.assertEquals(1.235, num1.getProperNumber());
    }


    @Test
    public void additionTestScaleNegative1() {
        FixedPoint num1 = new FixedPoint("-2.34");
        FixedPoint num2 = new FixedPoint("-1.111");
        num1.add(num2);
        Assertions.assertEquals(-3.451, num1.getProperNumber());
    }

    @Test
    public void additionTestScaleNegative2() {
        FixedPoint num1 = new FixedPoint("-2.345");
        FixedPoint num2 = new FixedPoint("-1.11");
        num1.add(num2);
        Assertions.assertEquals(-3.455, num1.getProperNumber());
    }
}
